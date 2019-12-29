package com.gang.etl.common;

import com.alibaba.fastjson.JSONObject;
import com.gang.sdk.api.annotation.SyncParam;
import com.gang.sdk.api.to.SyncBaseBean;
import com.gang.sdk.api.type.SyncOperationType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname SyncParamsInject
 * @Description TODO
 * @Date 2019/12/28 19:26
 * @Created by zengzg
 */
@Component
public class SyncMethodInfo {

    /**
     * desc : 获取method 类型
     *
     * @param clazz
     * @return
     */
    public ConcurrentHashMap<String, Method> searchMethod(Class clazz) {
        ConcurrentHashMap<String, Method> hashMap = new ConcurrentHashMap<String, Method>();
        Arrays.asList(clazz.getMethods()).forEach(method -> {

            if (method.getAnnotation(SyncOperationType.CREATE.getClazzName()) != null) {
                hashMap.put("CREATE", method);
            } else if (method.getAnnotation(SyncOperationType.UPDATE.getClazzName()) != null) {
                hashMap.put("UPDATE", method);
            } else if (method.getAnnotation(SyncOperationType.SEARCH.getClazzName()) != null) {
                hashMap.put("SEARCH", method);
            } else if (method.getAnnotation(SyncOperationType.DELETE.getClazzName()) != null) {
                hashMap.put("DELETE", method);
            } else if (method.getAnnotation(SyncOperationType.INIT.getClazzName()) != null) {
                hashMap.put("INIT", method);
            }
        });

        return hashMap;
    }


    public List<Object> getMethodParam(Method method, JSONObject jsonObject) {
        List<Object> lists = new LinkedList<>();
        Arrays.asList(method.getParameters()).forEach(parameter -> {
            String paramName = parameter.getAnnotation(SyncParam.class).name();
            if (!StringUtils.isEmpty(paramName)) {
                lists.add(jsonObject.get(paramName));
            }
            //            if (parameter instanceof SyncBaseBean) {
            //
            //            }


        });
    }

}
