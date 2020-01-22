package com.gang.etl.server.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.dao.SyncTypeDAO;
import com.gang.etl.datacenter.entity.SyncType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncTypeController
 * @Description TODO
 * @Date 2020/1/20 22:43
 * @Created by zengzg
 */
@RestController
@RequestMapping("/synctype")
public class SyncTypeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncTypeDAO syncTypeDAO;

    @GetMapping("get/{code}")
    public ResponseModel<String> get(@PathVariable("code") String code) {
        logger.info("------> this is in get SyncType <-------");

        return ResponseModel.commonResponse("this is response");
    }

    @PostMapping("insert")
    public ResponseModel<SyncType> insert(@RequestBody SyncType syncType) {
        logger.info("------> insert SyncType <-------");
        syncType = syncTypeDAO.insert(syncType);

        logger.info("------> this is SyncType :{} <-------", syncType);
        return ResponseModel.commonResponse(syncType);
    }

    @PostMapping("update")
    public ResponseModel<SyncType> update(@RequestBody SyncType syncType) {
        logger.info("------> SyncType SyncType <-------");
        return ResponseModel.commonResponse(syncType);
    }

    @PostMapping("delete/{code}")
    public ResponseModel<String> delete(@PathVariable("code") String code) {
        logger.info("------> delete <-------");
        return ResponseModel.commonResponse(code);
    }
}
