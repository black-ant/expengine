package com.gang.etl.engine.template;

import com.gang.etl.engine.api.bean.EngineBaseBean;
import com.gang.etl.engine.api.bean.EngineProduceBean;
import com.gang.etl.engine.thread.EngineThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname ConsumerTemplate
 * @Description TODO
 * @Date 2020/6/11 23:12
 * @Created by zengzg
 */
@Component
public class ConsumerTemplate {

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
