package com.gang.etl.engine.api.to;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.engine.api.common.IEngineService;
import com.gang.etl.engine.api.query.BaseQuery;
import com.gang.etl.engine.api.request.SyncBusinessRequest;
import lombok.Data;

import java.util.List;

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
    public final static String DEFAULT_DATA = "DATA";

    /**
     * 同步类型 : User / Org
     */
    private String syncType;

    private String originType;

    private String targetType;

    /**
     * 业务代码
     */
    private String businessCode;

    /**
     * Produce / Consume
     */
    private String typeOperation;

    /**
     * 类型组
     */
    private String typePart;

    /**
     * 查询语句
     */
    private BaseQuery query;

    /**
     * 数据主体
     */
    private List<T> data;

    /**
     * 配置信息
     */
    private String setting;

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

    /**
     * 是否继续
     */
    private Boolean syncContinue = Boolean.FALSE;


}
