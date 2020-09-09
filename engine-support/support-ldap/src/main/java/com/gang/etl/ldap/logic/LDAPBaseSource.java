//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import org.springframework.beans.factory.annotation.Autowired;
import para.sdk.common.sync.SyncBaseBean;

/**
 * @Classname LDAPBaseSource
 * @Description TODO
 * @Date 2020/2/23 23:09
 * @Created by zengzg
 */
public abstract class LDAPBaseSource<T extends SyncBaseBean, E extends SyncBaseBean> {

    @Autowired
    private LDAPConnect connect;

}
