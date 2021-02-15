package com.gang.etl.plugin.cache.api;

/**
 * @Classname ICacheManager
 * @Description TODO
 * @Date 2021/2/15 20:10
 * @Created by zengzg
 */
public interface ICacheManager {

    /**
     * 进栈
     */
    void push();

    /**
     * 出栈
     */
    void pop();
}
