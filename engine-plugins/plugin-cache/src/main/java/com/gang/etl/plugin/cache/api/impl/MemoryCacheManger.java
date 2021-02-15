package com.gang.etl.plugin.cache.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Classname MemoryCacheManger
 * @Description TODO
 * @Date 2021/2/15 20:06
 * @Created by zengzg
 */
public class MemoryCacheManger {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public static MemoryCacheManger build() {
        return new MemoryCacheManger();
    }

    /**
     * 无边界阻塞队列
     */
    private LinkedBlockingQueue<Object> linkedBlockingQeque = new LinkedBlockingQueue<Object>();


    /**
     * 进栈
     */
    public void push(Object data) throws InterruptedException {
        linkedBlockingQeque.put(data);
    }

    /**
     * 出栈
     */
    public <T> T pop() throws InterruptedException {
        return (T) linkedBlockingQeque.take();
    }


}
