package com.gang.etl.engine.template;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.container.SyncBeanFactory;
import com.gang.etl.engine.exchange.MemoryExchange;
import com.gang.etl.engine.thread.EngineThread;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname ConsumerLock
 * @Description TODO
 * @Date 2020/6/11 23:12
 * @Created by zengzg
 */
@Component
public class ConsumerTemplate extends BaseSyncLock {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EngineThread engineThread;
    @Autowired
    private MemoryExchange exchange;

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    private String lock = new String("LOCK");
    private Integer tryNum = 5;

    /**
     * @param engineBaseBean
     */
    public void excuteRest(EngineBaseBean engineBaseBean) {
        excute(engineBaseBean, null);
    }

    /**
     * @param rountingKey
     * @return
     */
    public void excute(EngineBaseBean engineBaseBean, String rountingKey) {

        logger.info("------> ConsumerTemplate excute run  :{} <-------", rountingKey);
        // Step 1 : do strategy

        // Step : do beforew
        // Step : do Thead
//        executor.submit(() -> {
        int i = 0;
        do {
            try {
                EngineBaseBean consumerBean = engineBaseBean != null ? engineBaseBean : exchange.consumerData(rountingKey);
                logger.info("------> ConsumerTemplate pop customerBean  :{} -- {} <-------", consumerBean,
                        rountingKey);
                if (consumerBean != null) {
                    engineThread.createThread(consumerBean);
                } else {
                    if (engineBaseBean != null) {
                        doLock(engineBaseBean.getLock(), engineBaseBean);
                    }

                }
            } catch (Exception e) {
                logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
            }
            i++;
        } while (i < tryNum);


//        });
    }
}
