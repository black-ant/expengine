package com.gang.etl.engine.api.common;

import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;

/**
 * @Classname IEngineConversion
 * @Description TODO
 * @Date 2020/6/20 18:06
 * @Created by zengzg
 */
public interface IEngineConversion {

    void conversion(EngineProduceBean produceBean, EngineConsumerBean consumerBean);
}
