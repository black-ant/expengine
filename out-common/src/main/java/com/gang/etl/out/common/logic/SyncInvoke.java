package com.gang.etl.out.common.logic;

import com.alibaba.fastjson.JSONObject;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.engine.api.type.SyncOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Classname SyncInvoke
 * @Description TODO
 * @Date 2019/12/28 19:23
 * @Created by zengzg
 */
@Component
public class SyncInvoke {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncSDKFactory syncSDKFactory;

    /**
     * @param clazzName         处理类名
     * @param syncOperationType 操作类型
     * @param params            参数值
     * @return
     */
    public ResponseModel invoke(String clazzName, SyncOperationType syncOperationType, Map<String, JSONObject> params) {

        logger.info("------> this is in invoke <-------");

        ResponseModel syncBaseBean = null;
        try {
            Class<?> handler = Class.forName(clazzName);
            syncBaseBean = invokeMain(handler, syncOperationType, params);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return syncBaseBean;
    }


    /**
     * invoke 核心逻辑
     *
     * @param params
     * @return
     */
    public ResponseModel invokeMain(Class clazz, SyncOperationType syncOperationType, Map<String, JSONObject> params) {

        logger.info("------> in invokeMain <-------");

        ResponseModel baseBean = null;
        try {
            Method method = doMethod(clazz, syncOperationType);
            baseBean = syncSDKFactory.classLoadTO(method);

            baseBean = (ResponseModel) method.invoke(syncSDKFactory.springClassLoad(clazz.getName()),
                    getParamObject(method, params));

        } catch (IllegalAccessException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);

        } catch (InvocationTargetException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        return baseBean;
    }

    /**
     * 属性转
     */
    public Object[] getParamObject(Method method, Map<String, JSONObject> params) {

        Object[] paramArray = new Object[method.getParameterTypes().length];
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            Class clazz = method.getParameterTypes()[i];
            JSONObject paramInfo = params.get(clazz.getSimpleName());
            paramArray[i] = JSONObject.toJavaObject(paramInfo, clazz);
        }
        return paramArray;
    }

    public Method doMethod(Class clazz, SyncOperationType syncOperationType) {
        return syncSDKFactory.getOperationMethod(clazz, syncOperationType.getOperationType());
    }
}
