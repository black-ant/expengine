package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.SyncAppRef;
import com.gang.etl.datacenter.service.impl.SyncAppRefServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncAppRefController
 * @Description TODO
 * @Date 2020/2/2 15:53
 * @Created by zengzg
 */
@RestController
@RequestMapping("/appref")
public class SyncAppRefController extends AbstratController<SyncAppRefServiceImpl, SyncAppRef> {
}
