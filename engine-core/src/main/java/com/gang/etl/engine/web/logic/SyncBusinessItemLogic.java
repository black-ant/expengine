package com.gang.etl.engine.web.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.entity.SyncBusinessItem;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncStrategy;
import com.gang.etl.datacenter.service.impl.SyncBusinessItemServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncBusinessServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncStrategyServiceImpl;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname SyncBusinessItemService
 * @Description TODO
 * @Date 2020/12/6 16:52
 * @Created by zengzg
 */
@Component
public class SyncBusinessItemLogic {

    @Autowired
    private SyncBusinessItemServiceImpl itemService;
    @Autowired
    private SyncBusinessServiceImpl businessService;
    @Autowired
    private SyncFieldInfoServiceImpl fieldInfoService;
    @Autowired
    private SyncStrategyServiceImpl syncStrategyService;

    public List<SyncBusinessItem> getItem(String businessId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("sync_business_id", businessId);

        List<SyncBusinessItem> list = itemService.list(wrapper);
        list.forEach(item -> {
            SyncBusiness business = businessService.getById(item.getSyncBusinessId());
            if (business != null) {
                item.setSyncBusinessName(business.getBusinessName());
            }
            SyncFieldInfo fieldInfo = fieldInfoService.getById(item.getSyncFieldId());
            if (fieldInfo != null) {
                item.setSyncFieldName(fieldInfo.getSyncTypeCode());
            }
            SyncStrategy syncStrategy = syncStrategyService.getById(item.getSyncStrategyId());
            if (syncStrategy != null) {
                item.setSyncStrategyName(syncStrategy.getStrategyName());
            }

        });
        return list;
    }

}
