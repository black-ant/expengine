package com.gang.etl.plugin.cache.service;

import com.gang.etl.plugin.cache.api.ICacheManager;
import com.gang.etl.plugin.cache.type.CacheType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Classname RedisCacheManager
 * @Description TODO
 * @Date 2021/3/8 21:41
 * @Created by zengzg
 */
public class RedisCacheManager implements ICacheManager {

    public static ICacheManager build() {
        return new RedisCacheManager();
    }


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public <T> T get(CacheType typeEnum) {
        return null;
    }

    @Override
    public <T> T get(CacheType typeEnum, String key) {
        return null;
    }

    @Override
    public <T> T get(CacheType typeEnum, String key, Boolean isClear) {
        return null;
    }

    @Override
    public <T> void put(CacheType typeEnum, T entity) {

    }

    @Override
    public <T> void put(CacheType typeEnum, String key, T entity) {

    }

    @Override
    public <T> void put(CacheType typeEnum, String key, T entity, int validateTime) {

    }

    @Override
    public <T> void put(CacheType typeEnum, String key, T entity, Long validateTime) {

    }

    @Override
    public <T> void put(CacheType typeEnum, String key, T entity, int validateTime, TimeUnit unit) {

    }

    @Override
    public void delete(CacheType typeEnum) {

    }

    @Override
    public void delete(CacheType typeEnum, String key) {

    }
}
