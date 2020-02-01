package com.gang.etl.server.controller;

import com.gang.etl.datacenter.entity.ExpTemplate;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.service.impl.ExpTemplateServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
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
