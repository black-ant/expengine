package com.gang.exp.engine.rest;


import com.gang.exp.engine.common.module.EngineOpModel;
import com.gang.exp.engine.common.module.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engineop")
public class EngineOperationController extends AbstarctController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("common")
    public ResponseModel opCommonEngine(EngineOpModel engineOpModel) {
        logger.info("------> this is in <-------");
        return createResponse("this is ok");
    }
}
