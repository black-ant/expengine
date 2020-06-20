package com.gang.etl.engine.web.logic;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @Classname SyncFieldInfoLogic
 * @Description TODO
 * @Date 2019/12/30 17:01
 * @Created by zengzg
 */
@Component
public class FieldLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    @Autowired
    private SyncFieldInfoServiceImpl syncFieldInfoService;

    @Autowired
    private SyncSettingServiceImpl syncSettingService;

    /**
     * @param code
     * @return
     */
    public SyncFieldInfo getFieldInfo(String code) {

        SyncType syncType = getSyncType(code);

        JSONObject jsonObject = new JSONObject();
        SyncFieldInfo fieldInfo = null;

        if (null != syncType) {
            jsonObject = JSONObject.parseObject(syncType.getTypeFiledInfo());

            QueryWrapper<SyncFieldInfo> filedQuery = new QueryWrapper();
            filedQuery.and(item -> item.eq("type_part", syncType.getTypePart() + "_" + syncType.getTypeCode()).or().eq("app_type",
                    syncType.getSupplierId()));

            logger.info("------> this is search  SyncFieldInfo :{}<-------", filedQuery.toString());
            fieldInfo = syncFieldInfoService.getOne(filedQuery);
            if (fieldInfo != null) {
                jsonObject = exchangeJSON(jsonObject, JSONObject.parseObject(fieldInfo.getFieldBody()));
            }
            fieldInfo = fieldInfo == null ? createInfo(syncType) : fieldInfo;
            fieldInfo.setFieldBody(jsonObject.toJSONString());

        }

        return fieldInfo;
    }

    /**
     * @param code
     * @return
     */
    public SyncSetting getConfig(String code) {

        SyncType syncType = getSyncType(code);

        JSONObject jsonObject = new JSONObject();
        SyncSetting syncSetting = null;

        if (null != syncType) {
            jsonObject = JSONObject.parseObject(syncType.getTypeFiledInfo());

            QueryWrapper<SyncSetting> filedQuery = new QueryWrapper();
            filedQuery.eq("setting_type_code", syncType.getTypeCode());

            logger.info("------> this is search  SyncFieldInfo :{}<-------", filedQuery.toString());
            syncSetting = syncSettingService.getOne(filedQuery);
            if (syncSetting != null) {
                jsonObject = exchangeJSON(jsonObject, JSONObject.parseObject(syncSetting.getSettingBody()));
            }
            syncSetting = syncSetting == null ? createSetting(syncType) : syncSetting;
            syncSetting.setSettingBody(jsonObject.toJSONString());

        }

        return syncSetting;
    }


    /**
     * 获取SyncType
     *
     * @param code
     * @return
     */
    public SyncType getSyncType(String code) {
        QueryWrapper<SyncType> typeQuery = new QueryWrapper();
        typeQuery.and(item -> item.eq("id", code).or().eq("type_code", code));
        return syncTypeService.getOne(typeQuery);
    }

    /**
     * @param source
     * @return
     */
    public JSONObject exchangeJSON(JSONObject target, JSONObject source) {

        for (String item : source.keySet()) {
            if (target.containsKey(item)) {
                target.put(item, source.get(item));
            } else {
                logger.info("------> this is not list :{} <-------", item);
            }
        }
        return target;
    }

    /**
     * @param syncType
     * @return
     */
    public SyncFieldInfo createInfo(SyncType syncType) {
        SyncFieldInfo syncFieldInfo = new SyncFieldInfo();
        syncFieldInfo.setVersion("1");
        syncFieldInfo.setAppType(syncType.getSupplierId());
        syncFieldInfo.setTypePart(syncType.getTypePart() + "_" + syncType.getTypeCode());

        syncFieldInfo.setCreateUser("gang");
        syncFieldInfo.setSyncType(syncType.getTypeCode());
        syncFieldInfo.setStatus("ACTIVE");

        return syncFieldInfo;
    }

    /**
     * @param syncType
     * @return
     */
    public SyncSetting createSetting(SyncType syncType) {
        SyncSetting syncSetting = new SyncSetting();

        syncSetting.setSettingName(syncType.getSupplierName());
        syncSetting.setSettingType(syncType.getId());
        syncSetting.setSettingTypeCode(syncType.getSupplierId());
        syncSetting.setCreateDate(new Date());

        return syncSetting;
    }


}
