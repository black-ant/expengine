package com.gang.etl.engine.logic;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.entity.SyncBusiness;
import com.gang.etl.datacenter.service.ISyncBusinessService;
import com.gang.etl.datacenter.service.impl.SyncBusinessServiceImpl;
import com.gang.etl.engine.api.bean.EngineBaseBean;
import com.gang.etl.engine.template.ConsumerTemplate;
import com.gang.etl.engine.template.ProduceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname RunBusinessLogin
 * @Description TODO
 * @Date 2020/9/10 16:31
 * @Created by zengzg
 */
@Service
public class RunBusinessLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncBusinessServiceImpl businessService;

    @Autowired
    private ProduceTemplate produceTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    /**
     * 执行单个同步业务
     *
     * @param businessId
     * @return
     */
    public EngineBaseBean doSingleBusiness(String businessId) {

        logger.info("------> run single business :{} <-------", businessId);
        SyncBusiness business = businessService.getById(businessId);
        logger.info("------> get One business :{} <-------", JSONObject.toJSONString(business));


        return null;

    }
}
