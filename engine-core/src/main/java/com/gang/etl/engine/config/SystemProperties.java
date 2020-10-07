package com.gang.etl.engine.config;

import org.springframework.stereotype.Component;

/**
 * @Classname SystemProperties
 * @Description 全局属性
 * @Date 2020/10/7 21:18
 * @Created by zengzg
 */
@Component
public class SystemProperties {

    /**
     * 是否需要数据换成 :
     * DEFAULT / REDIS / SQL
     */
    private String cacheType = "DEFAULT";


    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }
}
