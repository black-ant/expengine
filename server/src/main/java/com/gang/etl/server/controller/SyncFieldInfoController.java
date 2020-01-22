package com.gang.etl.server.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.server.logic.SyncFieldInfoLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname SyncFieldInfoController
 * @Description TODO
 * @Date 2019/12/30 16:58
 * @Created by zengzg
 */
@Controller
@RequestMapping("/syncfield")
public class SyncFieldInfoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncFieldInfoLogic syncFieldInfoLogic;

    @PostMapping("/insert")
    public ResponseModel<SyncFieldInfo> insert(SyncFieldInfo syncFieldInfo) {
        return ResponseModel.commonResponse(syncFieldInfoLogic.insert(syncFieldInfo));
    }

    @PostMapping("/gettos")
    public ResponseModel<String> getTOSField(@RequestParam("syncType") String syncType, @RequestParam(
            "syncType") String operation) {

        logger.info("------> syncType :{} + sync operation :{} <-------", syncType, operation);
        return ResponseModel.commonResponse("");
    }

    @GetMapping("/get/{key}")
    public ResponseModel<SyncFieldInfo> get(@PathVariable("key") Integer key) {
        return ResponseModel.commonResponse(syncFieldInfoLogic.selectByPrimaryKey(key));
    }

}
