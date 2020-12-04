package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.SyncBean;
import com.gang.etl.datacenter.service.impl.SyncBeanServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncBeanController
 * @Description TODO
 * @Date 2020/12/4 12:42
 * @Created by zengzg
 */
@RestController
@RequestMapping("sync/bean")
public class SyncBeanController extends AbstratController<SyncBeanServiceImpl, SyncBean> {


}

