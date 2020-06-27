package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gang.common.lib.to.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 *
 * </p>
 *
 * @author ant-black
 * @since 2020-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SyncType extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 唯一限定名
     */
    private String typeCode;

    /**
     * 加载类
     */
    private String typeClass;

    /**
     * 配置类
     */
    private String settingClass;

    /**
     * bean类
     */
    private String beanClass;

    /**
     * 策略类型
     */
    private String typePolicy;

    /**
     * 类型名
     */
    private String typeName;

    /**
     * 类型角色
     */
    private String typeApp;

    /**
     * 业务状态
     */
    private String typeStatus;

    /**
     * PUSH/PULL
     */
    private String typeOperation;

    /**
     * 类型角色
     */
    private String typePart;

    /**
     * 二级处理类型
     */
    private String typeSecondary;

    /**
     * 供应商类型
     */
    private String supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 类名称
     */
    private String typeFiledInfo;

}
