package com.gang.etl.engine.web.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.engine.web.logic.FieldLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncSettingController
 * @Description TODO
 * @Date 2020/1/15 23:06
 * @Created by zengzg
 */
@RestController
@RequestMapping("setting")
public class SyncSettingController extends AbstratController<SyncSettingServiceImpl, SyncSetting> {

    @Autowired
    private FieldLogic fieldLogic;

    @GetMapping("getcfgto")
    public ResponseModel<SyncSetting> getTO(@RequestParam("code") String code) {
        return ResponseModel.commonResponse(fieldLogic.getConfig(code));
    }

}