package com.gang.etl.engine.logic;

import java.util.Map;

/**
 * @Classname AbstractParamLogic
 * @Description TODO
 * @Date 2020/1/5 23:26
 * @Created by zengzg
 */
public abstract class AbstractParamLogic<T, D> {

    public abstract void exchange(T source, D target, Map<String, Object> config);

}
