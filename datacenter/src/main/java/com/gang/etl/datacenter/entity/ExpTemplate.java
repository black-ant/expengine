package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class ExpTemplate extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 模板名称
     */
    private String templateTitle;

    /**
     * 模板主体
     */
    private String templateBody;

    /**
     * 模板描述
     */
    private String templateDesc;

    private String templateModule;

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 模板创建人
     */
    private Integer createId;

    /**
     * 最后更新人
     */
    private Integer updateId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @TableField("update_Date")
    private LocalDateTime updateDate;


}
