package com.gang.etl.engine.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 同步 Handler 管理
 */
@Component
public class SyncContextUtils implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        SyncContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 获取对象
     *
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(Class<?> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static boolean removeBean(String name) {
        try {
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
                    .getAutowireCapableBeanFactory();
            // 创建bean信息.
            // 动态删除
            defaultListableBeanFactory.removeBeanDefinition(name);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean registerBean(Class<? extends Object> beanClass, String name, String propertyName,
                                       Object propertyValue) {
        try {
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
                    .getAutowireCapableBeanFactory();
            // 创建bean信息.
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
            BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            beanDefinition.getPropertyValues().addPropertyValue(propertyName, propertyValue);
            // 动态注册bean.
            defaultListableBeanFactory.registerBeanDefinition(name, beanDefinition);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
