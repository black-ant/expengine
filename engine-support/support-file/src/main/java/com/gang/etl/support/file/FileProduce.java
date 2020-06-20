package com.gang.etl.support.file;

import com.gang.etl.engine.api.bean.EngineProduceBean;
import com.gang.etl.engine.api.common.IProduceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname FileProduce
 * @Description TODO
 * @Date 2020/6/20 12:52
 * @Created by zengzg
 */
@Component
public class FileProduce implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineProduceBean input(EngineProduceBean produceBean) {
        logger.info("------> this is in input <-------");
        return null;
    }
}
