package com.gang.etl.server.logic;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.common.lib.utils.CommonStringUtils;
import com.gang.etl.common.lib.to.SyncModuleTO;
import com.gang.etl.datacenter.entity.SyncAppRef;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncAppRefServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.out.common.logic.SyncInvoke;
import com.gang.sdk.api.type.SyncOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MainLogic
 * @Description TODO
 * @Date 2020/2/2 19:08
 * @Created by zengzg
 */
@Component
public class MainLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncAppRefServiceImpl syncAppRefService;

    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    @Autowired
    private SyncSettingServiceImpl syncSettingService;

    @Autowired
    private SyncInvoke syncInvoke;

    /**
     * @param domain
     * @param syncModuleTO
     */
    public void mainLogic(String domain, SyncModuleTO syncModuleTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("domain", domain);
        List<SyncAppRef> list = syncAppRefService.list(queryWrapper);

        list.stream().forEach(item -> {

            SyncType type = syncTypeService.getById(item.getAppId());
            logger.info("------> this is type :{} <-------", JSONObject.toJSONString(type));

            Map<String, JSONObject> paramMap = new HashMap<>();


            QueryWrapper syncTypeQuery = new QueryWrapper();
            syncTypeQuery.eq("data_type", "TO");
            syncTypeQuery.eq("supplier_id", type.getSupplierId());

            List<SyncType> syncTypes = syncTypeService.list(syncTypeQuery);
            syncTypes.forEach(syncTypeItem -> {
                if ("CFG".equals(syncTypeItem.getTypePart())) {
                    // GET SETTING
                    SyncSetting syncSetting = syncSettingService.getById(item.getSettingId());
                    paramMap.put(CommonStringUtils.lastVarchar(syncTypeItem.getTypeClass(), '.'),
                            JSONObject.parseObject(syncSetting.getSettingBody()));

                } else if (syncModuleTO.getPartType().toString().equals(syncTypeItem.getTypePart())) {
                    // Get TO
                    paramMap.put(CommonStringUtils.lastVarchar(syncTypeItem.getTypeClass(), '.'),
                            JSONObject.parseObject(syncModuleTO.getSyncBody()));

                } else {
                    logger.info("------> not do syncType :{} <-------", syncTypeItem.toString());
                }
            });


            syncInvoke.invoke(type.getTypeClass(), SyncOperationType.CREATE, paramMap);
        });

    }


}
