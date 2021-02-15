package com.gang.etl.support.excel.service;

import com.gang.etl.engine.api.common.IComsumerService;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Classname DatabaseConsumerService
 * @Description TODO
 * @Date 2021/2/14 13:42
 * @Created by zengzg
 */
@Service
public class ExcelConsumerService implements IComsumerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineConsumerBean execute(EngineConsumerBean consumerBean) {
        return null;
    }
}
