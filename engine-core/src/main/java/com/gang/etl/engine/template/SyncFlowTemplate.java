package com.gang.etl.engine.template;

import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.api.to.SyncStatusTO;
import com.gang.etl.engine.config.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Classname SyncFlowTemplate
 * @Description TODO
 * @Date 2020/10/7 21:17
 * @Created by zengzg
 */
public class SyncFlowTemplate {

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

        SyncStatusTO syncStatusTO;
        switch (systemProperties.getCacheType()) {
            case "DEFAULT":
                syncStatusTO = defaultFlow(syncBusiness);
                break;


        }
        return null;
    }

    public SyncStatusTO defaultFlow(SyncBusiness syncBusiness) {

        SyncStatusTO syncStatusTO = new SyncStatusTO();

        // Build Engine Bean
        EngineBaseBean engineProduceBean = new EngineBaseBean();
        engineProduceBean.setServiceName(syncBusiness.getSyncProduce());

        do {
            try {

                // Sync Produce
                produceTemplate.excute(engineProduceBean);

                // Sync Consumer
                consumerTemplate.excute(engineProduceBean);

            } catch (Exception e) {
                logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());

            }
        } while (engineProduceBean.getSyncContinue());

        return syncStatusTO;
    }
}
