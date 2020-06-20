package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.ExpTemplate;
import com.gang.etl.datacenter.service.impl.ExpTemplateServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncTemplateController
 * @Description TODO
 * @Date 2020/2/1 17:17
 * @Created by zengzg
 */

@RestController
@RequestMapping("/template")
public class SyncTemplateController extends AbstratController<ExpTemplateServiceImpl, ExpTemplate> {

}
