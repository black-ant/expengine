package com.gang.etl.engine.api.common;

/**
 * @Classname AbstractBaseManager
 * @Description TODO
 * @Date 2020/10/7 16:39
 * @Created by zengzg
 */
public abstract class AbstractBaseManager<T> {

    public T connectInfoManage(T t, String key) {
        return t;
    }

}
