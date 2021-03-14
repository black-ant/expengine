package com.gang.etl.plugin.cache.service;

import com.gang.etl.plugin.cache.api.ICacheManager;
import com.gang.etl.plugin.cache.type.CacheType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Classname MemoryCacheManger
 * @Description TODO
 * @Date 2021/2/15 20:06
 * @Created by zengzg
 */
public class MemoryCacheManger implements ICacheManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 无边界阻塞队列
     */
    private LinkedBlockingQueue<Object> linkedBlockingQeque = new LinkedBlockingQueue<Object>();

    public static ICacheManager build() {
        return new MemoryCacheManger();
    }

    /**
     * 通过枚举获取缓存对象
     *
     * @param typeEnum
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(CacheType typeEnum) {
//        T searchCache = redisTemplate == null
//                ? null
//                : (T) redisTemplate.get(typeEnum.getKey(), typeEnum.getReadRemove());
//
//        return searchCache;
        return null;
    }

    /**
     * 通过业务枚举获取缓存对象 , 动态拼接Key
     *
     * @param typeEnum
     * @param key
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(CacheType typeEnum, String key) {
        String cacheKey = String.format(typeEnum.getKey(), key);
        return null;
    }

    /**
     * 允许定义删除记录
     *
     * @param typeEnum
     * @param key
     * @param isClear
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(CacheType typeEnum, String key, Boolean isClear) {
        String cacheKey = String.format(typeEnum.getKey(), key);
        return null;
    }

    /**
     * 业务枚举保存
     *
     * @param typeEnum
     * @param entity
     * @param <T>
     */
    @Override
    public <T> void put(CacheType typeEnum, T entity) {
        putFlow(typeEnum.getKey(), entity, typeEnum.getTimeout(), typeEnum.getUnit());
    }

    /**
     * 业务枚举保存 , 使用枚举类过期单位
     *
     * @param typeEnum
     * @param key
     * @param entity
     * @param <T>
     */
    @Override
    public <T> void put(CacheType typeEnum, String key, T entity) {
        String cacheKey = String.format(typeEnum.getKey(), key);
        putFlow(cacheKey, entity, typeEnum.getTimeout(), typeEnum.getUnit());
    }

    /**
     * 允许传入过期时间
     *
     * @param typeEnum 业务枚举
     * @param key key
     * @param entity 实体类
     * @param validateTime 过期时间
     * @param <T>
     */
    @Override
    public <T> void put(CacheType typeEnum, String key, T entity, int validateTime) {
        String cacheKey = String.format(typeEnum.getKey(), key);
        putFlow(cacheKey, entity, validateTime, typeEnum.getUnit());
    }

    /**
     *
     * @param typeEnum
     * @param key
     * @param entity
     * @param validateTime
     * @param <T>
     */
    @Override
    public <T> void put(CacheType typeEnum, String key, T entity, Long validateTime) {
        String cacheKey = String.format(typeEnum.getKey(), key);
        putFlow(cacheKey, entity, validateTime, typeEnum.getUnit());
    }

    /**
     * 允许传入过期时间/单位
     *
     * @param typeEnum 业务枚举
     * @param key key
     * @param entity 实体类
     * @param validateTime 过期时间
     * @param unit 过期单位
     * @param <T>
     */
    @Override
    public <T> void put(CacheType typeEnum, String key, T entity, int validateTime, TimeUnit unit) {
        String cacheKey = String.format(typeEnum.getKey(), key);
        putFlow(cacheKey, entity, validateTime, unit);
    }

    /**
     * 统一流程类
     *
     * @param key
     * @param entity
     * @param validateTime
     * @param unit
     * @param <T>
     */
    private <T> void putFlow(String key, T entity, int validateTime, TimeUnit unit) {
//        if (validateTime < 0) {
//            redisTemplate.opsForValue().set(key, entity);
//        } else {
//            redisTemplate.opsForValue().set(key, entity, validateTime, unit);
//        }
    }

    private <T> void putFlow(String key, T entity, Long validateTime, TimeUnit unit) {
//        if (validateTime < 0) {
//            redisTemplate.opsForValue().set(key, entity);
//        } else {
//            redisTemplate.opsForValue().set(key, entity, validateTime, unit);
//        }
    }

    /**
     * 业务删除
     *
     * @param typeEnum
     */
    @Override
    public void delete(CacheType typeEnum){

    }

    /**
     * 业务删除 , 动态 key
     *
     * @param typeEnum
     * @param key
     */
    @Override
    public void delete(CacheType typeEnum, String key) {
        String cacheKey = String.format(typeEnum.getKey(), key);
//        redisTemplate.delete(cacheKey);
    }



}
