package com.gang.etl.engine.strategy;

import com.gang.etl.engine.api.to.EngineBaseBean;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @Classname IErrorStrategy
 * @Description 异常处理策略接口
 * @Date 2021/3/14 16:57
 * @Created by zengzg
 */
public interface IErrorStrategy {


    Pair<Boolean, String> excute(EngineBaseBean engineBaseBean);
}
