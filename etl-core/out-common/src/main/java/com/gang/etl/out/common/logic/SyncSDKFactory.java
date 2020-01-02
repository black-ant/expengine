package com.gang.etl.out.common.logic;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.common.exception.SyncErrorEnum;
import com.gang.etl.common.exception.SyncException;
import com.gang.etl.datacenter.dao.SyncTypeDAO;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.sdk.api.service.AnyOperation;
import com.gang.sdk.api.to.SyncBaseBean;
import com.gang.sdk.api.type.SyncOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname SyncSDKFactory
 * @Description TODO
 * @Date 2019/12/28 18:18
 * @Created by zengzg
 */
@Component
public class SyncSDKFactory {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext context;

    @Autowired
    private SyncTypeDAO syncTypeDAO;

    @Autowired
    private SyncMethodInfo syncMethodInfo;

    private ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> methodMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, Method>>();


    /**
     * 获取操作类类型
     *
     * @param appName
     * @return
     */
    public AnyOperation getOperationClassName(String appName) {
        SyncType syncType = syncTypeDAO.getByNameAndData(appName, null);
        logger.info("------> this is :{} <-------", syncType);

        String className = syncType == null ? "" : syncType.getTypeClass();
        logger.info("------> this name is :{} <-------", className);

        AnyOperation opClass = (AnyOperation) springClassLoad(className);
        logger.info("------> this class :{} <-------", JSONObject.toJSONString(opClass));

        return opClass;

    }

    public Method getOperationMethod(Class clazz, String operationType) {
        if (null == methodMap.get(clazz.getName()) || methodMap.get(clazz.getName()).isEmpty()) {
            ConcurrentHashMap<String, Method> backMap = syncMethodInfo.searchMethod(clazz);
            if (backMap.isEmpty()) {
                throw new SyncException(SyncErrorEnum.NO_METHOD);
            }
            methodMap.put(clazz.getName(), backMap);
        }

        return methodMap.get(clazz.getName()).get(operationType);
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

    /**
     * 生成返回TO对象
     *
     * @param
     */
    public SyncBaseBean classLoadTO(Method method) {
        SyncBaseBean toBean = null;
        try {
            Class className = method.getReturnType();
            toBean = (SyncBaseBean) springClassLoad(className.getName());
        } catch (Exception e) {
            logger.error("E----> not found send class :{} --- content :{} ", e.getClass(), e);
        }
        return toBean;
    }

    /**
     * classLoader 加载对应得类得信息
     *
     * @param className
     * @return
     */
    public AnyOperation classLoadSyncSDK(String className) {
        return (AnyOperation) classLoader(className);
    }


    public Object classLoader(String className) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class<?> cls;
            cls = classLoader.loadClass(className);
            return cls.newInstance();
        } catch (Exception e) {
            logger.error("E----> not found send class :{} --- content :{} ", e.getClass(), e);
        }
        return null;
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

    public Class classLoadTOClass(Class clazz, SyncOperationType type) {
        return null;
    }

}
