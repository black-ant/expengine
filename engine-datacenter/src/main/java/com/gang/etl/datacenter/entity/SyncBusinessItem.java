package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-11-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_business_item")
public class SyncBusinessItem extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 业务子体名
     */
    private String itemName;

    private String itemCode;

    /**
     * 同步属性映射
     */
    private String syncFieldId;

    @TableField(exist = false)
    private String syncFieldName;

    /**
     * 业务名
     */
    private String syncBusinessId;

    @TableField(exist = false)
    private String syncBusinessName;

    /**
     * 策略id
     */
    private String syncStrategyId;

    @TableField(exist = false)
    private String syncStrategyName;


}
