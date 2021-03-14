package com.gang.etl.engine.template;

import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.engine.api.common.IEngineService;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.container.SyncBeanFactory;
import com.gang.etl.engine.conversion.DictionaryConversion;
import com.gang.etl.engine.exchange.MemoryExchange;
import com.gang.etl.engine.logic.SyncLogService;
import com.gang.etl.engine.strategy.operation.PageStrategy;
import com.gang.etl.engine.thread.EngineThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname ProduceLock
 * @Description TODO
 * @Date 2020/6/11 23:12
 * @Created by zengzg
 */
@Component
public class ProduceTemplate extends BaseSyncLock {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    @Autowired
    private EngineThread engineThread;
    @Autowired
    private DictionaryConversion dictionaryConversion;
    @Autowired
    private MemoryExchange exchange;
    @Autowired
    private SyncBeanFactory syncBeanFactory;
    @Autowired
    private SyncLogService syncLogService;
    @Autowired
    private PageStrategy pageStrategy;


    /**
     * @param engineProduceBean
     * @return
     */
    public void excute(EngineProduceBean engineProduceBean, SyncSetting syncSetting) throws InterruptedException {

        // Step 1 : do strategy

        // Step : do beforew

        // Step : invoke after

        // Step : do Thead
        executor.submit(() -> {
            logger.info("------> createThread submit :{} -- type :{} <-------", engineProduceBean.getServiceName(),
                    engineProduceBean.getSyncType());
            IEngineService service = syncBeanFactory.getSyncBean(engineProduceBean.getServiceName());
            service.execute(engineProduceBean);

            // Step end :
            logger.info("------> this is produce data :{} <-------", engineProduceBean.getData());

            EngineConsumerBean consumerBean = new EngineConsumerBean(engineProduceBean);

            consumerBean.setSetting(syncSetting.getSettingBody());
            consumerBean.setSyncType(engineProduceBean.getTargetType());
            consumerBean.setServiceName(engineProduceBean.getConsumerService());
            consumerBean.setBusiness(engineProduceBean.getBusiness());

            dictionaryConversion.conversion(engineProduceBean, consumerBean);

            // TODO : 根据情况切分数据
            pageStrategy.splitBean(consumerBean).forEach(item -> {
                logger.info("------> [切分数据] : {} <-------", item.getData());
                try {
                    // 将数据入栈
                    exchange.produceData(item, engineProduceBean.getBusinessCode());

                    //  备份记录 , 用于出现异常后的补救操作
                    syncLogService.addLog(item);
                } catch (InterruptedException e) {
                    logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
                }
                logger.info("------> this is produce data :{} <-------", engineProduceBean.getData());
            });


        });
    }
}
