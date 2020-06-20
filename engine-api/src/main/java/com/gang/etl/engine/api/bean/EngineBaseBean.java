package com.gang.etl.engine.api.bean;

import com.gang.etl.engine.api.common.IEngineService;

/**
 * @Classname EngineBaseBean
 * @Description TODO
 * @Date 2020/6/20 18:40
 * @Created by zengzg
 */
public class EngineBaseBean<T> {

    private String syncType;

    private T data;

    private String config;

    private IEngineService engineService;

    private String serviceName;

    private Object lock;

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public IEngineService getEngineService() {
        return engineService;
    }

    public void setEngineService(IEngineService engineService) {
        this.engineService = engineService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }
}
