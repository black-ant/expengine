package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.SyncStrategy;
import com.gang.etl.datacenter.service.impl.SyncStrategyServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncStrategyController
 * @Description TODO
 * @Date 2020/2/1 17:18
 * @Created by zengzg
 */
@RestController
@RequestMapping("/strategy")
public class SyncStrategyController extends AbstratController<SyncStrategyServiceImpl, SyncStrategy> {

}
