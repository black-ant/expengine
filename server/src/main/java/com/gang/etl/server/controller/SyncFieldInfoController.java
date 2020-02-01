package com.gang.etl.server.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.out.common.logic.SyncClassInfo;
import com.gang.etl.server.logic.SyncFieldInfoLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Classname SyncFieldInfoController
 * @Description TODO
 * @Date 2019/12/30 16:58
 * @Created by zengzg
 */
@Controller
@RequestMapping("/syncfield")
public class SyncFieldInfoController extends AbstratController<SyncFieldInfoServiceImpl, SyncFieldInfo> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SyncClassInfo classInfo;

    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    @GetMapping("getInfo")
    public ResponseModel<List<String>> getFieldInfo(@RequestParam("key") String key) {
        SyncType syncType = syncTypeService.getById(key);
        return ResponseModel.commonResponse(classInfo.getTosParams(syncType.getTypeClass()));
    }


}
