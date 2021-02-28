package com.gang.etl.engine.database.service;

import com.alibaba.fastjson.JSONObject;
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
public class DatabaseConsumerService implements IComsumerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineConsumerBean execute(EngineConsumerBean consumerBean) {
        logger.info("------> [数据库消费数据] :{} <-------", JSONObject.toJSONString(consumerBean));
        logger.info("------> Database consumer originType:{} <-------", consumerBean.getOriginType());
        logger.info("------> Database consumer targetType:{} <-------", consumerBean.getTargetType());
        if (null != consumerBean.getData()) {
            consumerBean.getData().forEach(item -> {
                logger.info("------> Database consumer :{} <-------", item);
            });
        }
        return null;
    }
}
