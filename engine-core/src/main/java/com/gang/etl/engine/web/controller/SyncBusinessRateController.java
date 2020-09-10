package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.SyncBusinessRate;
import com.gang.etl.datacenter.service.impl.SyncBusinessRateServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncBusinessRateController
 * @Description TODO
 * @Date 2020/9/10 10:30
 * @Created by zengzg
 */
@RestController
@RequestMapping("/sync/rate")
public class SyncBusinessRateController extends AbstratController<SyncBusinessRateServiceImpl, SyncBusinessRate> {
}
