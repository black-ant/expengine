package com.gang.etl.server.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncAppController
 * @Description TODO
 * @Date 2020/1/15 21:50
 * @Created by zengzg
 */
@RestController
@RequestMapping("/app")
public class SyncAppController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("get/{code}")
    public ResponseModel<String> get(@PathVariable("code") String code) {
        logger.info("------> this is in get SyncType <-------");

        return ResponseModel.commonResponse("this is response");
    }

    @PostMapping("insert")
    public ResponseModel<SyncType> insert(SyncType syncType) {
        logger.info("------> insert SyncType <-------");
        return ResponseModel.commonResponse(syncType);
    }

    @PostMapping("update")
    public ResponseModel<SyncType> update(SyncType syncType) {
        logger.info("------> SyncType SyncType <-------");
        return ResponseModel.commonResponse(syncType);
    }

    @PostMapping("delete/{code}")
    public ResponseModel<String> delete(@PathVariable("code") String code) {
        logger.info("------> delete <-------");
        return ResponseModel.commonResponse(code);
    }


}