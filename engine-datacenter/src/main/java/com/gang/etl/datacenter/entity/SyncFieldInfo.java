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
public class SyncFieldInfo extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 资源字段
     */
    private String fieldBody;

    /**
     * 来源模板
     */
    private String fieldOriginFormat;

    /**
     * 去处模板
     */
    private String fieldSourceFormat;


    /**
     * 字段类型唯一限定Code
     */
    private String fieldTypeCode;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 版本
     */
    private String version;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 数据;类型
     */
    private String typePart;

    /**
     * 状态
     */
    private String status;

    /**
     * 同步策略
     */
    private String policyType;

    /**
     * 同步类型
     */
    private String syncType;


}
