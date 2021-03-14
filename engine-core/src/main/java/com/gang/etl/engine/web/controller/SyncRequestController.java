package com.gang.etl.engine.web.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.engine.api.request.SyncCustomRequest;
import com.gang.etl.engine.template.RestFlowTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @Classname SyncRequestController
 * @Description 手动同步数据
 * @Date 2021/3/14 19:22
 * @Created by zengzg
 */
@RestController
@RequestMapping("/syncRequest")
public class SyncRequestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestFlowTemplate flowTemplate;

    /**
     * 手动推送
     */
    @PostMapping("/push")
    public ResponseModel doPush(@RequestBody SyncCustomRequest syncCustomRequest) {
        logger.info("------> doPush [{}] -- [{}] <-------", syncCustomRequest.getBusinessCode(), syncCustomRequest.getSyncBeanCode());
        return ResponseModel.commonResponse(flowTemplate.doPush(syncCustomRequest));
    }


}
