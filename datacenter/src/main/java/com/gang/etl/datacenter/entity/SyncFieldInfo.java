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
    private String filedOrigin;

    private String fieldSource;

    private String fieldType;

    private String createUser;

    private String version;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 数据;类型
     */
    private String dataType;

    private String status;

    /**
     * 用户id
     */
    private String domainId;

    /**
     * 同步策略
     */
    private String policyType;

    /**
     * 同步类型
     */
    private String syncType;


}
