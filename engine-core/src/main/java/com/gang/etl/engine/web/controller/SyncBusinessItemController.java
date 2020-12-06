package com.gang.etl.engine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncBusinessItem;
import com.gang.etl.datacenter.entity.SyncBusinessRate;
import com.gang.etl.datacenter.service.impl.SyncBusinessItemServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncBusinessRateServiceImpl;
import com.gang.etl.engine.web.logic.SyncBusinessItemLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncBusinessRateController
 * @Description TODO
 * @Date 2020/9/10 10:30
 * @Created by zengzg
 */
@RestController
@RequestMapping("/sync/business/item")
public class SyncBusinessItemController extends AbstratController<SyncBusinessItemServiceImpl, SyncBusinessItem> {

    @Autowired
    private SyncBusinessItemLogic itemLogic;

    @GetMapping("listById/{businessId}")
    public ResponseModel businessType(@PathVariable("businessId") String businessId) {
        return ResponseModel.commonResponse(itemLogic.getItem(businessId));
    }
}
