package com.gang.etl.engine.database.service;

import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.engine.api.to.EngineProduceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Classname ProduceService
 * @Description 基于数据库的配置方式
 * @Date 2021/2/14 13:41
 * @Created by zengzg
 */
@Service
public class DatabaseProduceService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {
        return null;
    }
}
