package com.gang.etl.engine.api.bean;

import com.gang.etl.engine.api.common.IEngineService;
import lombok.Data;

/**
 * @Classname EngineBaseBean
 * @Description TODO
 * @Date 2020/6/20 18:40
 * @Created by zengzg
 */
@Data
public class EngineBaseBean<T> {

    public final static String OP_PRODUCE = "Produce";
    public final static String OP_CONSUME = "Consume";

    private String syncType;

    private String typeOperation;

    private String typePart;

    private T data;

    private String config;

    private EngineBaseSetting baseConfig;

    private String serviceName;

    private String settingName;

    private IEngineService engineService;

    private Object lock;

    private EngineBaseResponse response;

}
