package com.gang.etl.dingtalk.service;

import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.engine.api.to.EngineProduceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname DingTalkConsumerService
 * @Description TODO
 * @Date 2020/9/11 14:50
 * @Created by zengzg
 */
@Component
public class DingTalkConsumerService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {
        return null;
    }
}
