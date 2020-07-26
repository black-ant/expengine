//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.config.LdapGlobalConfig;
import para.sdk.sync.ldap.error.ErrorLogic;
import para.sdk.sync.ldap.source.common.IBaseSource;
import para.sdk.sync.ldap.to.LDAPFilterTO;
import para.sdk.sync.ldap.to.LDAPPageFilterTO;
import para.sdk.sync.ldap.type.ObjectClass;
import para.sdk.sync.ldap.utils.ADUtils;
import para.sdk.sync.ldap.utils.SearchUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SearchSource
 * @Description TODO
 * @Date 2020/2/21 13:30
 * @Created by zengzg
 */
@Component
public class SearchSource extends LDAPBaseSource implements IBaseSource<List<Attributes>, LDAPFilterTO> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 通过 ObjectGUID 查询
     *
     * @param context    容器连接
     * @param objectGUID GUID
     * @return
     */
    public Attributes executeImpl(LdapContext context, String objectGUID) {

        try {
            return SearchUtils.searchByGUID(context, objectGUID);
        } catch (NamingException e) {
            if (LdapGlobalConfig.GLOBAL_DEBUG) {
                e.printStackTrace();
            }
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 通过 Filter TO 进行搜索
     *
     * @param connect      LDAPConnect
     * @param objectClass
     * @param ldapFilterTO
     * @return
     */
    @Override
    public List<Attributes> executeImpl(LDAPConnect connect, ObjectClass objectClass,
                                        LDAPFilterTO ldapFilterTO) {

        List<Attributes> attributes = new ArrayList<>();
        LdapContext ctx = connect.getCtx();

        try {

            // Step1 : 定义 Filter 语句
            String oldFilter = ldapFilterTO.getSearchFilter();
            if (objectClass != null) {
                String searchFilter = SearchUtils.getSearchClassFilter(objectClass, ldapFilterTO.getSearchFilter());
                ldapFilterTO.setSearchFilter(searchFilter);
            }


            // Step2 : 配置搜索范围
            String searchBaseContext = StringUtils.isNotBlank(ldapFilterTO.getSearchContext())
                    ? ADUtils.isDN(ldapFilterTO.getSearchContext())
                    ? ldapFilterTO.getSearchContext()
                    : SearchUtils.getEntryDNByGUID(ctx, ldapFilterTO.getSearchContext())
                    : connect.getConfig().getBaseContxt();

            ldapFilterTO.setSearchBaseDN(searchBaseContext);

            logger.info("------> Search Before , Base :{} , filter : {} <-------", ldapFilterTO.getSearchContext(),
                    ldapFilterTO.getSearchFilter());

            // Step3 : 搜索对象
            if (ldapFilterTO instanceof LDAPPageFilterTO) {
                attributes = SearchUtils.doSearchByFilterPage(ctx, (LDAPPageFilterTO) ldapFilterTO);
            } else {
                attributes = SearchUtils.doSearch(ctx, ldapFilterTO);
            }
            ldapFilterTO.setSearchFilter(oldFilter);

        } catch (NamingException e) {
            ErrorLogic.buidSyncException(e);
        } catch (Exception e1) {
            logger.error("E----> error :{} -- content :{}", e1.getClass(), e1.getMessage());
            throw new SyncException("代码异常 : " + e1.getMessage());
        }
        return attributes;
    }

}
