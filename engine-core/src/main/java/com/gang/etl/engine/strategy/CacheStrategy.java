package com.gang.etl.engine.strategy;

import com.gang.etl.engine.api.to.EngineBaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname RecursionStrategy
 * @Description 递归策略
 * @Date 2020/10/8 16:25
 * @Created by zengzg
 */
@Component
public class CacheStrategy implements ISyncStrategy {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String cachePrefix = "sync:";

    @Override
    public EngineBaseBean getBaseBean(String code, EngineBaseBean baseBean) {
        return null;
    }

    @Override
    public void setBaseBean(EngineBaseBean baseBean) {

    }
}
