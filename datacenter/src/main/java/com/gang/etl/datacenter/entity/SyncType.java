package com.gang.etl.datacenter.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * sync_type
 *
 * @author
 */
@Data
public class SyncType implements Serializable {
    private String id;

    /**
     * 加载类
     */
    private String typeClass;

    /**
     * 策略类型
     */
    private String typePolicy;

    /**
     * 类型代码
     */
    private String typeCode;

    /**
     * 类型名
     */
    private String typeName;

    /**
     * 供应商类型
     */
    private String supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 处理类型
     */
    private String dataType;

    private static final long serialVersionUID = 1L;
}