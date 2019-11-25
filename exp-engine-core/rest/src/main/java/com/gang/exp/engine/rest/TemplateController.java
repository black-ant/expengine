package com.gang.exp.engine.rest;

import com.gang.exp.engine.common.module.ResponseModel;
import com.gang.exp.engine.common.to.TemplateTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class TemplateController extends AbstarctController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "create", produces = "application/json")
    public ResponseModel<TemplateTO> ceateTeamplate() {

        logger.info("------> this is in ceate <-------");
        return createResponse("this is ok");
    }

}
