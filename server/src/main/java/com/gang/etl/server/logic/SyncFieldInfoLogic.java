package com.gang.etl.server.logic;

import com.alibaba.fastjson.JSONObject;
import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.datacenter.dao.SyncFiledInfoDAO;
import com.gang.etl.datacenter.dao.SyncTypeDAO;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.sdk.api.annotation.SyncClass;
import com.gang.sdk.api.annotation.SyncTO;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Classname SyncFieldInfoLogic
 * @Description TODO
 * @Date 2019/12/30 17:01
 * @Created by zengzg
 */
@Component
public class SyncFieldInfoLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String OP_TYPE = "OP";
    private static final String TO_TYPE = "TO";

    @Autowired
    private SyncFiledInfoDAO syncFiledInfoDAO;

    @Autowired
    private SyncTypeDAO syncTypeDAO;

    public SyncFieldInfo insert(SyncFieldInfo syncFieldInfo) {
        return syncFiledInfoDAO.insert(syncFieldInfo);
    }

    public SyncFieldInfo selectByPrimaryKey(Integer key) {
        return syncFiledInfoDAO.selectByPrimaryKey(key);
    }

    /**
     * desc : 获取 TOS 属性
     *
     * @param typeCode
     * @return
     */
    public List<String> getTOSMap(String typeCode) {

        List<String> filedMap = new LinkedList<>();

        SyncType syncType = syncTypeDAO.getByKeyOrCode(typeCode);
        String className = syncType.getSupplierId() + "_" + syncType.getDataType();
        Class clazz = CacheLogic.getCache(className);

        if (clazz == null) {
            getPackageMap(syncType.getSupplierId());
        }
        clazz = CacheLogic.getCache(syncType.getSupplierId() + "_" + syncType.getDataType());

        Field[] fields = ReflectionUtils.getFields(clazz);
        for (Field field : fields) {
            filedMap.add(field.getName());
        }
        return filedMap;
    }


    public Map<String, Class> getPackageMap(String typeCode) {

        Map<String, Class> map = new HashMap<>();

        SyncType syncType = syncTypeDAO.getByKeyOrCode(typeCode);

        logger.info("------> this is SyncType :{} <-------", JSONObject.toJSONString(syncType));

        List<String> classList = getFieldList(syncType);
        classList.forEach(item -> {

            Class clazz = ReflectionUtils.getClass(item);

            if (!ObjectUtils.isEmpty(clazz.getAnnotation(SyncTO.class))) {
                logger.info("------> this is Annotation SyncTO <-------");

                SyncTO syncTO = (SyncTO) clazz.getDeclaredAnnotation(SyncTO.class);
                String cacheName = syncType.getTypeCode() + "_" + TO_TYPE + "_" + syncTO.type();
                map.put(cacheName, clazz);
            } else if (!ObjectUtils.isEmpty(clazz.getAnnotation(SyncClass.class))) {
                logger.info("------> this is Annotation SyncClass <-------");
                SyncClass syncClass = (SyncClass) clazz.getDeclaredAnnotation(SyncClass.class);
                String cacheName = syncType.getTypeCode() + "_" + OP_TYPE + "_" + syncClass.type();
                map.put(cacheName, clazz);
            } else {
                logger.info("------> no Annotation Class <-------");
            }
        });
        CacheLogic.setAllCacheClass(map);
        return map;
    }

    public List<String> getFieldList(SyncType syncType) {

        List<String> classList = new ArrayList<>();
        String packageName = syncType.getTypeClass();

        classList = ReflectionUtils.scanClazzName(packageName, Boolean.TRUE);

        return classList;
    }


}
