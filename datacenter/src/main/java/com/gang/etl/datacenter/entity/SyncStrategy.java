package com.gang.etl.datacenter.entity;

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
public class SyncStrategy extends AbstractEntity {

    private static final long serialVersionUID = 1L;

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


}
