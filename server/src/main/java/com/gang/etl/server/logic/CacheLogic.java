package com.gang.etl.server.logic;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname CacheLogic
 * @Description TODO
 * @Date 2020/1/23 17:04
 * @Created by zengzg
 */
@Component
public class CacheLogic {


    private static final Map<String, Class> cacheMap = new HashMap<>();


    public static Class getCache(String classCode) {
        return cacheMap.get(classCode);
    }

    public static void setCacheClass(String cacheName, Class syncClass) {
        cacheMap.put(cacheName, syncClass);
    }

    public static void setAllCacheClass(Map<String, Class> classMap) {
        cacheMap.putAll(classMap);
    }


}
