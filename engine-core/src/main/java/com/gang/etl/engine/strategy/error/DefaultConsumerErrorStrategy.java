package com.gang.etl.engine.strategy.error;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.strategy.IErrorStrategy;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname ConsumerErrorStrategy
 * @Description 消费异常策略 : 消费数据时出现运行异常
 * @Date 2021/3/14 16:52
 * @Created by zengzg
 */
@Component
public class DefaultConsumerErrorStrategy implements IErrorStrategy {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 默认消费策略 : 消费失败 打 log
     *
     * @return
     */
    @Override
    public Pair<Boolean, String> excute(EngineBaseBean engineBaseBean) {


        return null;
    }
}
