package com.gang.etl.engine.strategy;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.common.BaseSyncLock;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Classname MemoryQueueStrategy
 * @Description TODO
 * @Date 2020/10/8 16:59
 * @Created by zengzg
 */
@Component
public class MemoryQueueStrategy extends BaseSyncLock implements ISyncStrategy {

    LinkedBlockingQueue<EngineBaseBean> linkedBlockingQueue = new LinkedBlockingQueue();

    @Override
    public EngineBaseBean getBaseBean(String code, EngineBaseBean baseBean) {
        if (linkedBlockingQueue.isEmpty()) {

        } else {

        }
        return linkedBlockingQueue.poll();
    }

    @Override
    public void setBaseBean(EngineBaseBean baseBean) {
        linkedBlockingQueue.add(baseBean);

    }
}
