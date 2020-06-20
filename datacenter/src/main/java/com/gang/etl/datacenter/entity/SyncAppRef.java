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
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SyncAppRef extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属组
     */
    private String domain;

    /**
     * 同步App
     */
    private String appId;

    /**
     * 冗余Code
     */
    private String appCode;

    /**
     * 同步策略ID
     */
    private String syncStrategyId;

    private String settingId;


}
