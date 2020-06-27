package com.gang.etl.engine.web.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.engine.api.bean.EngineBaseBean;
import com.gang.etl.engine.api.bean.EngineBaseResponse;
import com.gang.etl.engine.logic.SimpleEngineLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncPushController
 * @Description TODO
 * @Date 2020/6/27 21:31
 * @Created by zengzg
 */
@RequestMapping("/push")
@RestController
public class SyncPushController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleEngineLogic engineLogic;

    @PostMapping("single")
    public ResponseModel pushSingle(EngineBaseBean engineBaseBean) {
        logger.info("------> this is in single <-------");
        engineBaseBean = engineLogic.excute(engineBaseBean);
        EngineBaseResponse baseResponse = engineBaseBean.getResponse();
        return ResponseModel.commonResponse(engineBaseBean);
    }

}
