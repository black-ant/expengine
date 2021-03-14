package com.gang.etl.plugin.cache.type;

import java.util.concurrent.TimeUnit;

/**
 * @Classname CacheType
 * @Description TODO
 * @Date 2021/3/8 21:42
 * @Created by zengzg
 */
public enum CacheType {

    COMMON("", "共用Cache", 60, TimeUnit.SECONDS),
    DATA("DATA_", "数据同步", 60, TimeUnit.SECONDS);

    /**
     * Redis key
     */
    private String key;

    /**
     * Redis 过期时间
     */
    private Integer timeout;

    /**
     * Redis 过期单位
     */
    private TimeUnit unit;

    /**
     * 使用后是否删除
     */
    private Boolean readOnce;

    /**
     * 业务描述
     */
    private String description;

    /**
     * 补充字段
     */
    private String remarks;


    /**
     * 默认一天过期
     *
     * @param key
     */
    CacheType(String key) {
        this.key = key;
        this.timeout = 86400;
        this.unit = TimeUnit.SECONDS;
        this.readOnce = Boolean.FALSE;
        this.description = "";
        this.remarks = "";
    }

    CacheType(String key, String description) {
        this.key = key;
        this.timeout = 86400;
        this.unit = TimeUnit.SECONDS;
        this.readOnce = Boolean.FALSE;
        this.description = description;
        this.remarks = "";
    }

    CacheType(String key, String description, Integer timeout, TimeUnit unit) {
        this.key = key;
        this.timeout = timeout;
        this.unit = unit;
        this.readOnce = Boolean.FALSE;
        this.description = description;
        this.remarks = "";
    }

    CacheType(String key, String description, Integer timeout, TimeUnit unit, Boolean readRemove) {
        this.key = key;
        this.timeout = timeout;
        this.unit = unit;
        this.readOnce = readRemove;
        this.description = description;
        this.remarks = remarks;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public Boolean getReadOnce() {
        return readOnce;
    }

    public void setReadOnce(Boolean readOnce) {
        this.readOnce = readOnce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
