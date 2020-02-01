package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gang.common.lib.to.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


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
public class SyncSetting extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 配置体
     */
    private String settingBody;

    /**
     * 配置名称
     */
    private String settingName;

    /**
     * 配置类型
     */
    private String settingType;

    private String settingTypeCode;

    /**
     * 配置控制
     */
    private String settingControl;

    /**
     * 配置策略
     */
    private String settingPolicy;

    private LocalDateTime createDate;


}
