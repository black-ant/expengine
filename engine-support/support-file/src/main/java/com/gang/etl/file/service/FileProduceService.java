package com.gang.etl.file.service;

import com.gang.etl.engine.api.to.EngineProduceBean;
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
public class FileProduceService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * read file from path
     *
     * @param consumerBean
     * @return
     */
    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {

        logger.info("------> this is in file produce <-------");
        return null;
    }
}
