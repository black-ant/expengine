package com.gang.etl.engine.logic;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.service.ISyncBusinessService;
import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.template.ConsumerTemplate;
import com.gang.etl.engine.template.ProduceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncBusinessLogic
 * @Description do one business
 * @Date 2020/10/7 19:26
 * @Created by zengzg
 */
@Component
public class SyncBusinessLogic extends BaseSyncLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISyncBusinessService syncBusinessService;

    @Autowired
    private ProduceTemplate produceTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    /**
     * do Single Business by businessId
     *
     * @param businessId
     * @return
     */
    public EngineBaseBean doSingleBusinessCode(String businessId) {

        logger.info("------> run single business :{} <-------", businessId);
        SyncBusiness business = syncBusinessService.findByCode(businessId);
        logger.info("------> get One business :{} <-------", JSONObject.toJSONString(business));


        return null;

    }

    /**
     * do Single Business by SyncBusiness bean
     *
     * @param syncBusiness
     * @return
     */
    public EngineBaseBean doSingleBusiness(SyncBusiness syncBusiness) {
        return null;
    }
}
