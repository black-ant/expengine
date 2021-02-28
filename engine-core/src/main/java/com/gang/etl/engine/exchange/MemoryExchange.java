package com.gang.etl.engine.exchange;

import com.gang.etl.engine.api.annotation.SyncAudit;
import com.gang.etl.engine.api.service.IExchangeService;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.plugin.cache.api.impl.MemoryCacheManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TransferQueue;

/**
 * @Classname MemoryExchange
 * @Description TODO
 * @Date 2021/2/17 11:42
 * @Created by zengzg
 */
@Service
public class MemoryExchange implements IExchangeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ConcurrentHashMap<String, MemoryCacheManger> syncMap = new ConcurrentHashMap();

    /**
     * 数据入栈
     *
     * @param consumerBean
     * @param rountingKey
     */
    @SyncAudit
    public void produceData(EngineConsumerBean consumerBean, String rountingKey) throws InterruptedException {
        MemoryCacheManger memoryCacheManger = syncMap.get(rountingKey);
        if (memoryCacheManger == null) {
            memoryCacheManger = new MemoryCacheManger();
            syncMap.put(rountingKey, memoryCacheManger);
        }
        memoryCacheManger.push(consumerBean);
    }

    /**
     * 数据出栈
     *
     * @param rountingKey
     * @return
     * @throws InterruptedException
     */
    @SyncAudit
    public synchronized EngineConsumerBean consumerData(String rountingKey) throws InterruptedException {
        MemoryCacheManger memoryCacheManger = syncMap.get(rountingKey);
        return memoryCacheManger == null ? null : memoryCacheManger.pop();
    }

}
