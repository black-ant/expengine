package com.gang.etl.plugin.cache.api;

import com.gang.etl.plugin.cache.type.CacheType;

import java.util.concurrent.TimeUnit;

/**
 * @Classname ICacheManager
 * @Description TODO
 * @Date 2021/2/15 20:10
 * @Created by zengzg
 */
public interface ICacheManager {

    <T> T get(CacheType typeEnum);

    <T> T get(CacheType typeEnum, String key);

    <T> T get(CacheType typeEnum, String key, Boolean isClear);

    <T> void put(CacheType typeEnum, T entity);

    <T> void put(CacheType typeEnum, String key, T entity);

    <T> void put(CacheType typeEnum, String key, T entity, int validateTime);

    <T> void put(CacheType typeEnum, String key, T entity, Long validateTime);

    <T> void put(CacheType typeEnum, String key, T entity, int validateTime, TimeUnit unit);

    void delete(CacheType typeEnum);

    void delete(CacheType typeEnum, String key);
}
