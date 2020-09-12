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

    /**
     * 同步类型 : User / Org
     */
    private String syncType;

    /**
     * 操作类型 : PULL /PUSH
     */
    private String typeOperation;

    /**
     * 类型组
     */
    private String typePart;

    /**
     * 数据主体
     */
    private T data;

    /**
     * 配置信息
     */
    private String config;

    /**
     * 基础 Setting
     */
    private EngineBaseSetting baseConfig;

    /**
     * 处理类
     */
    private String serviceName;

    /**
     * 配置Name
     */
    private String settingName;

    /**
     * 处理类
     */
    private IEngineService engineService;

    /**
     * 同步锁
     */
    private Object lock;

    /**
     * 返回结果
     */
    private EngineBaseResponse response;

}
