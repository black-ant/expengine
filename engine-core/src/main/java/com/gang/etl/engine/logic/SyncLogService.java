package com.gang.etl.engine.logic;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.entity.SyncLog;
import com.gang.etl.datacenter.service.impl.SyncLogServiceImpl;
import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.api.to.SyncBaseBean;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname SyncLogService
 * @Description TODO
 * @Date 2021/2/17 21:04
 * @Created by zengzg
 */
@Component
public class SyncLogService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncLogServiceImpl logService;

    public String addLog(EngineBaseBean syncBaseBean) {

        logger.info("------> 数据生产 : 写入 Log <-------");
        SyncLog log = new SyncLog();
        log.setLogDomain(syncBaseBean.getSyncType());
        log.setLogStatus("0");
        log.setSyncBusiness(syncBaseBean.getBusinessCode());
        log.setLogRequest("");
        log.setLogResponse(JSONObject.toJSONString(syncBaseBean.getResponse()));
        log.setCreateDate(new Date());
        logService.save(log);
        logger.info("------> 数据生产 : log 插入完成 :{} <-------", JSONObject.toJSONString(log));
        return log.getId();
    }


}
