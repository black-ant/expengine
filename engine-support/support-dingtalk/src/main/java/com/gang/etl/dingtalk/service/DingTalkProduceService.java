package com.gang.etl.dingtalk.service;

import com.gang.etl.engine.api.bean.EngineProduceBean;
import com.gang.etl.engine.api.common.IProduceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname DingTalkProduceService
 * @Description TODO
 * @Date 2020/7/26 21:42
 * @Created by zengzg
 */
@Component
public class DingTalkProduceService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {
        return null;
    }
}
