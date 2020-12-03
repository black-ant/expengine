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
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_bean")
public class SyncBean extends AbstractEntity {

    private static final long serialVersionUID=1L;

    /**
     * 配置类代码
     */
    private String beanCode;

    /**
     * 配置体
     */
    private String beanBody;

    /**
     * 配置名称
     */
    private String beanName;

    /**
     * 配置类型 ( TO / SETTING)
     */
    private String beanType;

    /**
     * 配置所属应用
     */
    private String beanAppCode;

    /**
     * 配置控制
     */
    private String beanControl;

    /**
     * 配置策略
     */
    private String beanPolicy;

    private LocalDateTime createDate;


}
