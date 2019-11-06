package com.gang.expengine.core.controller;

import com.gang.expengine.core.common.model.ResponseModel;
import com.gang.expengine.core.dao.entity.ExpTemplate;
import com.gang.expengine.core.logic.TemplateLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template")
public class TemplateController extends AbstarctController {

    @Autowired
    private TemplateLogic templateLogic;


    @PostMapping("save")
    public ResponseModel createTemplate(@RequestBody ExpTemplate expTemplate) {
        return createResponse(templateLogic.insert(expTemplate));
    }
}
