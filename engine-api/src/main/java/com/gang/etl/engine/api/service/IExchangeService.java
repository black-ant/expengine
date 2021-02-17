package com.gang.etl.engine.api.service;

import com.gang.etl.engine.api.to.EngineConsumerBean;

/**
 * @Classname IExchangeService
 * @Description TODO
 * @Date 2021/2/17 12:07
 * @Created by zengzg
 */
public interface IExchangeService {

    void produceData(EngineConsumerBean consumerBean, String rountingKey) throws InterruptedException;

    EngineConsumerBean consumerData(String rountingKey) throws InterruptedException;
}
