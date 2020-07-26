package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gang.common.lib.to.AbstractEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ant-black
 * @since 2020-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_business")
public class SyncBusiness extends AbstractEntity {

    private static final long serialVersionUID=1L;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 业务唯一编码
     */
    private String businessCode;

    /**
     * 业务描述
     */
    private String businessDesc;

    /**
     * 生产者
     */
    private String syncProduce;

    /**
     * 消费者
     */
    private String syncConsumer;

    /**
     * 定时任务
     */
    private String syncSchedule;

    /**
     * info信息
     */
    private String syncInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 业务状态
     */
    private String status;


}