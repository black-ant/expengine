package com.gang.etl.engine.web.logic;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname SettingLogic
 * @Description TODO
 * @Date 2020/12/4 12:48
 * @Created by zengzg
 */
@Component
public class SyncSettingLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    @Autowired
    private SyncSettingServiceImpl syncSettingService;

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
