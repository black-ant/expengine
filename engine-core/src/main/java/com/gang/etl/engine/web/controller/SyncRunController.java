package com.gang.etl.engine.web.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.api.to.EngineBaseResponse;
import com.gang.etl.engine.api.exception.SyncException;
import com.gang.etl.engine.api.request.SyncBusinessRequest;
import com.gang.etl.engine.logic.SimpleEngineLogic;
import com.gang.etl.engine.logic.SyncBusinessLogic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @Classname SyncPushController
 * @Description TODO
 * @Date 2020/6/27 21:31
 * @Created by zengzg
 */
@RequestMapping("/excute")
@RestController
public class SyncRunController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleEngineLogic engineLogic;


    @Autowired
    private SyncBusinessLogic syncBusinessLogic;


    @GetMapping("business/type/{typeId}")
    public ResponseModel businessType(
            @RequestParam("typeId") String typeId) {
        logger.info("------> conversion :{} -- {}  <-------", typeId);


        return ResponseModel.commonResponse("success");
    }


    /**
     * 有配置的单个业务处理
     *
     * @param business
     * @return
     */
    @PostMapping("business/single/{business}")
    public ResponseModel businessHasSetting(@RequestParam(name = "business") String business) {
        logger.info("------> conversion :{} -- {}  <-------", business);
        return ResponseModel.commonResponse("success");
    }

    /**
     * 单个业务处理
     *
     * @param syncBusinessRequest
     * @return
     */
    @PostMapping("business/single")
    public ResponseModel business(@RequestBody SyncBusinessRequest syncBusinessRequest) {
        logger.info("------> business single  :{} <-------", syncBusinessRequest);

        if (StringUtils.isNotEmpty(syncBusinessRequest.getBusinessCode())) {
            syncBusinessLogic.doSingleBusinessCode(syncBusinessRequest.getBusinessCode());
        } else {
            throw new SyncException("No Busniess Code");
        }
        return ResponseModel.commonResponse("success");
    }

    /**
     * 仅生产
     *
     * @param syncBusinessRequest
     * @return
     */
    @PostMapping("produce/all")
    public ResponseModel produceAll(SyncBusinessRequest syncBusinessRequest) {
        logger.info("------> conversion :{} -- {}  <-------", syncBusinessRequest);
        return ResponseModel.commonResponse("success");
    }


    /**
     * 仅消费
     *
     * @param syncBusinessRequest
     * @return
     */
    @PostMapping("consumer/all")
    public ResponseModel consumerAll(SyncBusinessRequest syncBusinessRequest) {
        logger.info("------> conversion :{} -- {}  <-------", syncBusinessRequest);
        return ResponseModel.commonResponse("success");
    }

    @PostMapping("single")
    public ResponseModel pushSingle(EngineBaseBean engineBaseBean) {
        logger.info("------> this is in single <-------");
        engineBaseBean = engineLogic.excuteByType(engineBaseBean);
        EngineBaseResponse baseResponse = engineBaseBean.getResponse();
        return ResponseModel.commonResponse(engineBaseBean);
    }

    @PostMapping("list")
    public ResponseModel pushList(List<EngineBaseBean> engineBaseBeans) {
        logger.info("------> this is in pushList <-------");
        List<EngineBaseResponse> baseResponses = new LinkedList<>();
        engineBaseBeans.forEach(item -> {
            engineLogic.excuteByType(item);
            baseResponses.add(item.getResponse());
        });
        return ResponseModel.commonResponse(baseResponses);
    }

}
