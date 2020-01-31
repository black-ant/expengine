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
public class ExpUser extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    private String userPower;

    private String userPassword;

    private String userType;


}
