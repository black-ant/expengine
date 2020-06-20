package com.gang.etl.datacenter.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @Classname AbstractDAO
 * @Description TODO
 * @Date 2020/1/2 23:39
 * @Created by zengzg
 */
public class AbstractEngineDAO {


    /**
     * 构建 QueryWrapper Equals
     *
     * @param key
     * @param value
     * @return
     */
    public QueryWrapper getQueryWrapper(String key, String value) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(key, value);
        return wrapper;
    }
}
