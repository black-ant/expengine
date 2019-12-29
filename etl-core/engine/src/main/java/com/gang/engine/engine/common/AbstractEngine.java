package com.gang.engine.engine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * @Classname AbstractEngine
 * @Description TODO
 * @Date 2019/12/14 22:00
 * @Created by zengzg
 */
public class AbstractEngine {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext context;

    public void doEngine() {

    }

    public Class getClassInvoke(String classPath) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            return classLoader.loadClass(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过 Spring  模块加载对象
     *
     * @return
     */
    public Object springClassLoad(String className) {
        try {
            Class<?> handler = Class.forName(className);
            Object loadback = context.getAutowireCapableBeanFactory()
                    .createBean(handler, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
            return loadback;
        } catch (ClassNotFoundException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        return null;
    }


}
