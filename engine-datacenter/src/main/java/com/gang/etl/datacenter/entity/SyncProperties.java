package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gang.common.lib.to.AbstractEntity;
import java.sql.Blob;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ant-black
 * @since 2021-02-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_properties")
public class SyncProperties extends AbstractEntity {

    private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一代码
     */
    private String code;

    /**
     * 别名
     */
    private String alias;

    private String type;

    private String value;

    private String status;

    private Blob attachment;


}
