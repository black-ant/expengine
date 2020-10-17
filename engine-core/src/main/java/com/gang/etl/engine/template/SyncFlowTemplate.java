package com.gang.etl.engine.template;

import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.api.to.SyncStatusTO;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.config.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncFlowLock
 * @Description TODO
 * @Date 2020/10/7 21:17
 * @Created by zengzg
 */
@Component
public class SyncFlowTemplate extends BaseSyncLock {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private ProduceTemplate produceTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;


    /**
     * 执行流程
     *
     * @return
     */
    public SyncStatusTO doFlow(SyncBusiness syncBusiness) {

        SyncStatusTO syncStatusTO = null;
        switch (systemProperties.getCacheType()) {
            case "DEFAULT":
                syncStatusTO = defaultFlow(syncBusiness);
                break;


        }
        return syncStatusTO;
    }

    public SyncStatusTO defaultFlow(SyncBusiness syncBusiness) {

        SyncStatusTO syncStatusTO = new SyncStatusTO();

        // Build Engine Bean
        EngineProduceBean engineProduceBean = new EngineProduceBean();
        engineProduceBean.setServiceName(syncBusiness.getSyncProduce());
        engineProduceBean.setConfig(syncBusiness.getSyncProduceSetting());

        do {
            try {
                engineProduceBean.setLock(new String(""));

                // Sync Produce
                produceTemplate.excute(engineProduceBean);
                logger.info("------> this is produce data :{} <-------", engineProduceBean.getData());

                // 线程等待
                doLock(engineProduceBean.getLock(), engineProduceBean);

                // Sync Consumer
                EngineConsumerBean consumerBean = new EngineConsumerBean(engineProduceBean, syncBusiness.getSyncConsumerSetting());
                consumerBean.setServiceName(syncBusiness.getSyncConsumer());
                consumerTemplate.excute(consumerBean);

            } catch (Exception e) {
                logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());

            }
        } while (engineProduceBean.getSyncContinue());

        return syncStatusTO;
    }
}
