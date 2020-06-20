package com.gang.etl.engine.container;

import com.gang.common.lib.utils.ReflectionUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Classname SyncBeanFactory
 * @Description TODO
 * @Date 2020/6/20 17:34
 * @Created by zengzg
 */
@Component
public class SyncBeanFactory {

    @Autowired
    private ReflectionUtils reflectionUtils;

    @Autowired
    private SyncContainer syncContainer;

    /**
     * @param beanName
     * @param <T>
     * @return
     */
    public <T> T getSyncBean(String beanName) {
        //TODO : Bean 反射待优化
        //        Map<String, Object> beanMap = syncContainer.getProducerMap();
        return reflectionUtils.springClassLoad(beanName);
    }

    /**
     * Get Bean
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public <T> T getSyncProducerBean(String beanName) {
        //TODO : Bean 反射待优化
        //        Map<String, Object> beanMap = syncContainer.getProducerMap();
        return reflectionUtils.springClassLoad(beanName);
    }

    /**
     * Get Bean
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public <T> T getSyncComsumerBean(String beanName) {
        //TODO : Bean 反射待优化
        //        Map<String, Object> beanMap = syncContainer.getProducerMap();
        return reflectionUtils.springClassLoad(beanName);
    }
}
