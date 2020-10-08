package com.gang.etl.engine.template;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.thread.EngineThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname ConsumerLock
 * @Description TODO
 * @Date 2020/6/11 23:12
 * @Created by zengzg
 */
@Component
public class ConsumerLock extends BaseSyncLock {

    @Autowired
    private EngineThread engineThread;

    /**
     * @param engineProduceBean
     * @return
     */
    public EngineBaseBean excute(EngineBaseBean engineProduceBean) {

        // Step 1 : do strategy

        // Step : do beforew

        // Step : do Thead
        engineThread.createThread(engineProduceBean);

        return engineProduceBean;
    }
}
