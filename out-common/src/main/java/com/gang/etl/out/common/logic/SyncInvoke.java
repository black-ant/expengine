package com.gang.etl.out.common.logic;

import com.gang.sdk.api.type.SyncOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
     * invoke 核心逻辑
     *
     * @param paras
     * @return
     */
    public Object invokeMain(Class clazz, SyncOperationType syncOperationType, Object... paras) {

        Object baseBean = null;
        try {
            Method method = doMethod(clazz, syncOperationType);
            baseBean = syncSDKFactory.classLoadTO(method);
            baseBean = method.invoke(syncSDKFactory.classLoader(clazz.getName()), paras);
        } catch (IllegalAccessException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);

        } catch (InvocationTargetException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        return baseBean;
    }

    public Method doMethod(Class clazz, SyncOperationType syncOperationType) {
        return syncSDKFactory.getOperationMethod(clazz, syncOperationType.getOperationType());
    }
}
