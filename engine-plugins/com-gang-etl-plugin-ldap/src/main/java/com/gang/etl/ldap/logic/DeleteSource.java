package com.gang.etl.ldap.logic;

import com.para.commons.core.error.CommonErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.error.ErrorLogic;
import para.sdk.sync.ldap.source.common.IBaseSource;
import para.sdk.sync.ldap.to.LDAPFilterTO;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.ObjectClass;
import para.sdk.sync.ldap.utils.ADUtils;
import para.sdk.sync.ldap.utils.AttributeUtils;
import para.sdk.sync.ldap.utils.SearchUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DeleteSource
 * @Description TODO
 * @Date 2020/2/21 13:30
 * @Created by zengzg
 */
@Component
public class DeleteSource extends LDAPBaseSource implements IBaseSource<List<String>, LDAPFilterTO> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据GUID删除 , 删除单个及其子单元
     *
     * @param connect
     * @param objectGUID
     * @return
     */
    public String executeImpl(LDAPConnect connect, String objectGUID) {

        logger.info("------> delete object :{} <-------", objectGUID);

        try {
            String entryDN = SearchUtils.doSearchEntryDNByObjectKey(connect, objectGUID, null);

            if (StringUtils.isBlank(entryDN)) {
                throw new SyncException(CommonErrorEnum.UPDATE_ERR_EXT, "The object has been deleted and the object "
                        + "was not found");
            }

            entryDN = ADUtils.exchangeIllegalEntryDN(entryDN);
            connect.getCtx().destroySubcontext(entryDN);
            return entryDN;
        } catch (NamingException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
            ErrorLogic.buidSyncException(e);
        }
        return null;
    }

    /**
     * 传入 BaseContext 进行删除 通过 ldapFilterTO 进行批量删除 暂时不用
     *
     * @param connect      LDAPConnect
     * @param objectClass
     * @param ldapFilterTO
     * @return
     * @throws SyncException
     */
    @Override
    public List<String> executeImpl(LDAPConnect connect, ObjectClass objectClass,
                                    LDAPFilterTO ldapFilterTO) throws SyncException {

        LdapContext ctx = connect.getCtx();
        try {
            // Step1 : 定义 Filter 语句
            String searchFilter = SearchUtils.getSearchClassFilter(objectClass, ldapFilterTO.getSearchFilter());
            ldapFilterTO.setSearchFilter(searchFilter);

            // Step2 : 配置搜索范围
            if (StringUtils.isNotBlank(ldapFilterTO.getSearchContext())) {
                throw new SyncException("Delete Operation Info : Delete BaseContext is empty , This Setting is "
                        + "Dangerous ! Please Set BaseContext");
            }

            String searchBaseContext = ADUtils.isDN(ldapFilterTO.getSearchContext()) ? ldapFilterTO.getSearchContext()
                    : SearchUtils.getEntryDNByGUID(ctx, ldapFilterTO.getSearchContext());

            ldapFilterTO.setSearchBaseDN(searchBaseContext);

            logger.info("------> Search Before , Base :{} , filter : {} <-------", ldapFilterTO.getSearchContext(),
                    ldapFilterTO.getSearchFilter());

            // step3 : 进行批量删除
            return deleteAct(ctx, ldapFilterTO);

        } catch (Exception e) {
            throw new SyncException("Delete Object Error :" + objectClass.getObjectClassLdapPrefix());
        }
    }

    /**
     * 删除主逻辑
     *
     * @param context
     * @param ldapFilterTO
     * @return
     */
    public List<String> deleteAct(LdapContext context, LDAPFilterTO ldapFilterTO) {

        List<String> deleteInfo = new ArrayList<>();
        try {
            List<Attributes> attrsList = SearchUtils.doSearchByFilter(context, ldapFilterTO.getSearchContext(),
                    ldapFilterTO.getScope(), ldapFilterTO.getSearchFilter());

            attrsList.forEach(item -> {
                if (ldapFilterTO.getDeleteStrict()) {
                    // TODO : 严格删除逻辑
                }
                String entryDN = AttributeUtils.getAttributorValue(item, DefaultProperties.DNPARAMNAME);

                logger.info("------> this is delete dn :{} <-------", entryDN);
                try {

                    entryDN = ADUtils.exchangeIllegalEntryDN(entryDN);
                    context.destroySubcontext(entryDN);
                    deleteInfo.add(entryDN);
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            });
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return deleteInfo;
    }
}
