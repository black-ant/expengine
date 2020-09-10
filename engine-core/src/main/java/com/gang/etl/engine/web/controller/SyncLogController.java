package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.SyncLog;
import com.gang.etl.datacenter.service.impl.SyncLogServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncLogController
 * @Description TODO
 * @Date 2020/2/2 15:53
 * @Created by zengzg
 */
@RestController
@RequestMapping("/sync/log")
public class SyncLogController extends AbstratController<SyncLogServiceImpl, SyncLog> {
}
