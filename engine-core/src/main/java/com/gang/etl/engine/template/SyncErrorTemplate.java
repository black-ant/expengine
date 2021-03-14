package com.gang.etl.engine.template;

import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.strategy.error.DefaultConsumerErrorStrategy;
import com.gang.etl.engine.strategy.error.DefaultProduceErrorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncErrorTemplate
 * @Description TODO
 * @Date 2020/10/7 23:00
 * @Created by zengzg
 */
@Component
public class SyncErrorTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DefaultConsumerErrorStrategy consumerErrorStrategy;


    @Autowired
    private DefaultProduceErrorStrategy produceErrorStrategy;

    /**
     * 处理 Produce 异常
     *
     * @param engineProduceBean
     * @return
     */
    public void excute(EngineProduceBean engineProduceBean) {
        logger.info("------> this is EngineProduceBean <-------");
    }


    /**
     * 处理 Consum
     */
    public void excute(EngineConsumerBean engineConsumerBean) {
        logger.info("------> this is engineConsumerBean <-------");
    }

}
