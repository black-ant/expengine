package com.gang.etl.engine.strategy.error;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.strategy.IErrorStrategy;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname ProduceErrorStrategy
 * @Description 生产异常策略 : 当生产数据时出现运行错误
 * @Date 2021/3/14 16:52
 * @Created by zengzg
 */
@Component
public class DefaultProduceErrorStrategy implements IErrorStrategy {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 默认生产策略
     *
     * @return
     */
    @Override
    public Pair<Boolean, String> excute(EngineBaseBean engineBaseBean) {
        return null;
    }
}
