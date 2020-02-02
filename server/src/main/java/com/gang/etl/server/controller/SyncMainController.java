package com.gang.etl.server.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.common.lib.to.SyncModuleTO;
import com.gang.etl.server.logic.MainLogic;
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
 * @Classname SyncMainController
 * @Description TODO
 * @Date 2020/2/2 16:50
 * @Created by zengzg
 */
@RestController
@RequestMapping("/main")
public class SyncMainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MainLogic mainLogic;

    @PostMapping("push/{domain}")
    public ResponseModel pushApp(@PathVariable("domain") String domain, @RequestBody SyncModuleTO paramJSON) {
        logger.info("------> this is pushApp <-------");
        mainLogic.mainLogic(domain, paramJSON);
        return ResponseModel.commonResponse("ok");
    }
}
