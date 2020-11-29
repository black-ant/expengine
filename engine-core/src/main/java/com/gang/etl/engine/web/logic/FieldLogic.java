package com.gang.etl.engine.web.logic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.engine.api.annotation.SyncField;
import com.gang.etl.engine.api.annotation.SyncTO;
import com.gang.etl.engine.api.bean.ISyncBaseTO;
import com.gang.etl.engine.api.dto.FieldItemDTO;
import com.gang.etl.engine.api.exception.SyncErrorEnum;
import com.gang.etl.engine.api.exception.SyncException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private ReflectionUtils reflectionUtils;

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
     * @return
     */
    public String scanPackage() {
        List<Class> classes = ReflectionUtils.getAllInterfaceAchieveClass(ISyncBaseTO.class, "com.gang.etl");
        classes.forEach(item -> {
            logger.info("------> this is item :{} <-------", item.getSimpleName());
            try {
                SyncTO syncTO = (SyncTO) item.getAnnotation(SyncTO.class);
                SyncSetting syncSetting = getSetting(syncTO, item);
                UpdateWrapper wrapper = new UpdateWrapper();
                wrapper.eq("setting_code", syncSetting.getSettingCode());
                syncSettingService.saveOrUpdate(syncSetting, wrapper);
            } catch (Exception e) {
                logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
            }

        });
        return "success";
    }

    public SyncSetting getSetting(SyncTO syncTO, Class clazz) {
        SyncSetting setting = new SyncSetting();
        if (syncTO == null) {
            throw new SyncException(SyncErrorEnum.SYNC_SCAN_TO_ERROR, clazz.getSimpleName());
        }
        setting.setSettingCode(StringUtils.isNotEmpty(syncTO.name()) ? syncTO.name() : clazz.getSimpleName());
        setting.setSettingName(StringUtils.isNotEmpty(syncTO.name()) ? syncTO.name() : clazz.getSimpleName());
        setting.setSettingType(StringUtils.isNotEmpty(syncTO.type()) ? syncTO.type() :
                reflectionUtils.getParentFolder(clazz).contains("TO") ? "TO" : "SETTING");
        setting.setSettingAppCode(StringUtils.isNotEmpty(syncTO.app()) ? syncTO.app() :
                reflectionUtils.getParentFolder(clazz));
        setting.setSettingBody(JSONArray.toJSONString(getFiledList(clazz)));
        setting.setCreateDate(new Date());
        return setting;
    }

    public List<FieldItemDTO> getFiledList(Class clazz) {
        List<Field> fields = FieldUtils.getAllFieldsList(clazz);
        List<FieldItemDTO> array = new ArrayList<>();
        fields.forEach(item -> {
            FieldItemDTO objectItem = new FieldItemDTO();
            objectItem.setKey(item.getName());
            SyncField field = item.getAnnotation(SyncField.class);
            if (field != null) {
                objectItem.setDesc(field.description());
                objectItem.setValue(field.defaultValue());
                objectItem.setLength(field.length());
            }
            array.add(objectItem);
        });
        return array;
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
        syncFieldInfo.setSyncTypeCode(syncType.getTypeCode());
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
        syncSetting.setSettingAppCode(syncType.getSupplierId());
        syncSetting.setCreateDate(new Date());

        return syncSetting;
    }


}
