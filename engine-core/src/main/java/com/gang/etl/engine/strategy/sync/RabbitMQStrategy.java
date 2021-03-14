package com.gang.etl.engine.strategy.sync;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.strategy.ISyncStrategy;

/**
 * @Classname RabbitMQStrategy
 * @Description 基于 RabbitMQ 同步和消费
 * @Date 2021/3/14 16:57
 * @Created by zengzg
 */
public class RabbitMQStrategy extends BaseSyncLock implements ISyncStrategy {

    @Override
    public EngineBaseBean getBaseBean(String code, EngineBaseBean baseBean) {
        return null;
    }

    @Override
    public void setBaseBean(EngineBaseBean baseBean) {

    }
}
