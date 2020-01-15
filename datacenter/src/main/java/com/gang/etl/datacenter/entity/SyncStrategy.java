package com.gang.etl.datacenter.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * sync_strategy
 * @author 
 */
@Data
public class SyncStrategy implements Serializable {
    private String id;

    private String strategyType;

    private String strategyName;

    /**
     * 发送频率
     */
    private String frequency;

    /**
     * 重试次数
     */
    private Integer retryNum;

    /**
     * 分发规则
     */
    private String rules;

    /**
     * 定时方式
     */
    private String strategyJob;

    private static final long serialVersionUID = 1L;
}