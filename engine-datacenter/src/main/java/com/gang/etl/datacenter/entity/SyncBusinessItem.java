package com.gang.etl.datacenter.entity;

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

    private static final long serialVersionUID=1L;

    /**
     * 业务子体名
     */
    private String itemName;

    /**
     * 同步属性映射
     */
    private String syncFieldId;

    /**
     * 业务名
     */
    private String syncBusinessId;

    /**
     * 策略id
     */
    private String synStrategyId;


}
