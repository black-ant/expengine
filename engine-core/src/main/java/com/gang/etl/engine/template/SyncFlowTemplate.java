package com.gang.etl.engine.template;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.entity.SyncBusinessItem;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.service.impl.SyncBusinessItemServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.engine.api.query.BaseQuery;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.api.to.SyncStatusTO;
import com.gang.etl.engine.common.BaseSyncLock;
import com.gang.etl.engine.config.SystemProperties;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Autowired
    private SyncFieldInfoServiceImpl syncFieldInfoService;

    @Autowired
    private SyncBusinessItemServiceImpl businessItemService;


    /**
     * 执行流程
     *
     * @return
     */
    public SyncStatusTO doFlow(SyncBusiness syncBusiness) {

        SyncStatusTO syncStatusTO = null;
        switch (systemProperties.getCacheType()) {
            case "DEFAULT":
                syncBusinessFlow(syncBusiness);
                break;


        }
        return syncStatusTO;
    }

    /**
     * 主流程 Sync Business Item
     *
     * @param syncBusiness
     * @return
     */
    public Boolean syncBusinessFlow(SyncBusiness syncBusiness) {

        List<SyncBusinessItem> itemList = getBusinessItemList(syncBusiness);
        itemList.forEach(item -> {
            defaultFlow(syncBusiness, item);
        });
        return Boolean.TRUE;
    }


    public SyncStatusTO defaultFlow(SyncBusiness syncBusiness, SyncBusinessItem item) {

        SyncStatusTO syncStatusTO = new SyncStatusTO();

        // Build Engine Bean
        EngineProduceBean engineProduceBean = new EngineProduceBean();
        engineProduceBean.setServiceName(syncBusiness.getSyncProduce());
        engineProduceBean.setConfig(syncBusiness.getSyncProduceSetting());

        SyncFieldInfo fieldInfo = syncFieldInfoService.getById(item.getSyncFieldId());
        engineProduceBean.setOriginType(fieldInfo.getOriginCode());
        engineProduceBean.setQuery(new BaseQuery(fieldInfo.getProduceQuery()));
        do {
            try {
                engineProduceBean.setLock(new String(""));

                // Sync Produce
                produceTemplate.excute(engineProduceBean);
                logger.info("------> this is produce data :{} <-------", engineProduceBean.getData());

                // 线程等待
                doLock(engineProduceBean.getLock(), engineProduceBean);

                // Sync Consumer
                EngineConsumerBean consumerBean = new EngineConsumerBean(engineProduceBean,
                        syncBusiness.getSyncConsumerSetting());
                consumerBean.setServiceName(syncBusiness.getSyncConsumer());
//                consumerTemplate.excute(consumerBean);

            } catch (Exception e) {
                logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());

            }
        } while (engineProduceBean.getSyncContinue());

        return syncStatusTO;
    }

    /**
     * 获取BusinessLiST
     *
     * @param syncBusiness
     * @return
     */
    public List<SyncBusinessItem> getBusinessItemList(SyncBusiness syncBusiness) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("sync_business_id", syncBusiness.getId());
        return businessItemService.list(wrapper);
    }
}
