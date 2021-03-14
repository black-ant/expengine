package com.gang.etl.engine.strategy.sync;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.entity.SyncLog;
import com.gang.etl.datacenter.service.ISyncLogService;
import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.strategy.ISyncStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname BlockStrategy
 * @Description 基于数据库同步和消费
 * @Date 2020/10/8 16:33
 * @Created by zengzg
 */
@Component
public class DatabaseStrategy extends BaseSyncLock implements ISyncStrategy {

    @Autowired
    private ISyncLogService syncLogService;

    @Override
    public EngineBaseBean getBaseBean(String code, EngineBaseBean baseBean) {
        List<SyncLog> syncLogs = syncLogService.findByCodeAndStatus(code, "1");
        baseBean.setData(syncLogs);
        return baseBean;
    }

    @Override
    public void setBaseBean(EngineBaseBean baseBean) {
        syncLogService.save(buildSyncLog(baseBean));
    }

    /**
     * 构建 Log
     *
     * @param baseBean
     * @return
     */
    public SyncLog buildSyncLog(EngineBaseBean baseBean) {

        SyncLog syncLog = new SyncLog();

        syncLog.setLogBody(String.valueOf(baseBean.getData()));
        syncLog.setLogChannel(baseBean.getBusinessCode());
        syncLog.setLogResponse(String.valueOf(baseBean.getResponse()));
        syncLog.setLogStatus("1");
        return syncLog;
    }
}
