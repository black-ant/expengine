package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.service.impl.SyncBusinessServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SynsBusinessController
 * @Description TODO
 * @Date 2020/11/26 21:31
 * @Created by zengzg
 */
@RestController
@RequestMapping("/sync/business")
public class SynsBusinessController extends AbstratController<SyncBusinessServiceImpl, SyncBusiness> {
}

