package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_business_rate")
public class SyncBusinessRate extends AbstractEntity {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属组
     */
    private String domain;

    /**
     * 同步App
     */
    private String businessId;

    /**
     * 冗余Code
     */
    private String rateCode;

    /**
     * 状态 : 0-未开始 , 1-开始 , 2-完成,9-失败
     */
    private String rateStatus;

    /**
     * 最后同步logId
     */
    private String rateLastId;

    /**
     * 速度 : 数量/分钟
     */
    private Double rateSpeed;

    /**
     * 成功总计
     */
    private String rateSuccess;

    /**
     * 异常总计
     */
    private String rateError;

    /**
     * 全部总计
     */
    private String rateAmount;


}
