//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import para.sdk.sync.ldap.to.LDAPUpdateTO;
import para.sdk.sync.ldap.to.config.LDAPConfig;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.LDAPVersion;
import para.sdk.sync.ldap.type.ObjectClass;
import para.sdk.sync.ldap.utils.ADUtils;
import para.sdk.sync.ldap.utils.AttributeUtils;
import para.sdk.sync.ldap.utils.SearchUtils;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname CommonSourceOperation
 * @Description TODO
 * @Date 2020/2/27 11:31
 * @Created by zengzg
 */
public class CommonSourceOperation {


    /**
     * 根据 updateTO 获取
     *
     * @param ldapUpdateTO
     * @param objectClass
     * @return
     * @throws NamingException
     */
    protected ModificationItem[] getAttriutes(LDAPUpdateTO ldapUpdateTO, ObjectClass objectClass)
            throws NamingException {

        Attributes replaceAttrs = ldapUpdateTO.getReplaceAttrs();
        Attributes removeAttrs = ldapUpdateTO.getRemoveAttrs();
        ModificationItem[] replaceModificationItem = null;
        ModificationItem[] removeModificationItem = null;
        ModificationItem[] addModificationItem = null;
        if (replaceAttrs != null) {
            replaceModificationItem = getModificationItem(replaceAttrs, DirContext.REPLACE_ATTRIBUTE);
        }
        if (removeAttrs != null) {
            removeModificationItem = getModificationItem(removeAttrs, DirContext.REMOVE_ATTRIBUTE);
        }
        // if (addAttrs != null) {
        // addModificationItem = getModificationItem(addAttrs,
        // DirContext.ADD_ATTRIBUTE);
        // }
        ModificationItem[] addAll = (ModificationItem[]) ArrayUtils.addAll(replaceModificationItem,
                removeModificationItem);
        return (ModificationItem[]) ArrayUtils.addAll(addAll, addModificationItem);
    }

    /**
     * 获取修改的属性
     *
     * @param atts
     * @param option
     * @return
     * @throws NamingException
     */
    protected ModificationItem[] getModificationItem(Attributes atts, int option) throws NamingException {
        ModificationItem[] mods = new ModificationItem[atts.size()];
        final NamingEnumeration ne = atts.getAll();
        List<Attribute> list = new ArrayList<>();
        int count = 0;
        while (ne.hasMoreElements()) {
            Attribute sr = (Attribute) ne.next();
            mods[count] = new ModificationItem(option, sr);
            count++;
        }
        return mods;
    }

    /**
     * 校验属性并且预存
     *
     * @param attrs
     * @return
     */
    protected Map<String, Attribute> checkAttrs(LDAPConnect connect, Attributes attrs, Map<String, Attribute> attrMap,
                                                ObjectClass objectClass) {

        // 校验属性是否合法
        // ADUtils.checkEntryDN(AttributeUtils.getAttributorValue(attrs,
        // "sAMAccountName"));
        // ADUtils.checkEntryDN(attrs,objectClass);

        if (attrs == null) {
            return attrMap;
        }

        // 获取组
        if (null != attrs.get(DefaultProperties.LDAP_GROUPS_NAME)) {
            attrMap.put(DefaultProperties.LDAP_GROUPS_NAME, attrs.get(DefaultProperties.LDAP_GROUPS_NAME));
            attrs.remove(DefaultProperties.LDAP_GROUPS_NAME);
        }

        // 获取组
        if (null != attrs.get(DefaultProperties.LDAP_MEMBER)) {
            attrMap.put(DefaultProperties.LDAP_MEMBER, attrs.get(DefaultProperties.LDAP_MEMBER));
            attrs.remove(DefaultProperties.LDAP_MEMBER);
        }

        // 获取父组织名
        if (null != attrs.get(DefaultProperties.OUT_PARENT)) {
            attrMap.put(DefaultProperties.OUT_PARENT, attrs.get(DefaultProperties.OUT_PARENT));
            attrs.remove(DefaultProperties.OUT_PARENT);
        }

        // 获取经理Enrty DN 同时判断是否存在
        if (null != attrs.get(DefaultProperties.LDAP_MANAGER)) {

            String entryDN = SearchUtils.doSearchEntryDNByObjectKey(connect, AttributeUtils.getAttributorValue(attrs,
                    DefaultProperties.LDAP_MANAGER), ObjectClass.ACCOUNT);

            if (StringUtils.isBlank(entryDN)) {
                attrs.remove(DefaultProperties.LDAP_MANAGER);
            } else {
                attrs.put(DefaultProperties.LDAP_MANAGER, entryDN);
            }
        }

        // 移除 distinguishedName 属性
        if (null != attrs.get(DefaultProperties.DNPARAMNAME)) {
            attrs.remove(DefaultProperties.DNPARAMNAME);
        }

        // 异常ObjectGuid
        if (null != attrs.get(DefaultProperties.OBJECTGUID)) {
            attrs.remove(DefaultProperties.OBJECTGUID);
        }

        // 移除ou属性
        if (null != attrs.get(DefaultProperties.LDAP_OU) && objectClass.is(ObjectClass.ORGANIZATION_NAME)) {
            attrs.remove(DefaultProperties.LDAP_OU);
        }

        // 移除ou属性或者cn属性
        if (objectClass.is(ObjectClass.ALL_NAME)) {
            String cn = ADUtils.getCnOrOu(attrs.get("objectClass"));
            if ("OU".equals(cn) && null != attrs.get(DefaultProperties.LDAP_OU)) {
                attrs.remove(DefaultProperties.LDAP_OU);
            }
        }

        // 移除cn属性
        if (null != attrs.get(DefaultProperties.LDAP_CN) && !objectClass.is(ObjectClass.ORGANIZATION_NAME)) {
            attrs.remove(DefaultProperties.LDAP_CN);
        }

        // 根据环境剔除不允许值
        if (LDAPVersion.OPENLDAP.equals(connect.getConfig().getLdapVersion())) {
            attrs.remove(DefaultProperties.EXCHANGE_PHOTO);
            attrs.remove(DefaultProperties.USER_PRINCIPAL_NAME);
            attrs.remove(DefaultProperties.SAM_ACCOUNT_NAME);
            attrs.remove(DefaultProperties.LDAP_SHOW_NAME);
            attrs.remove(DefaultProperties.DISPLAY_NAME);
        }

        return attrMap;
    }


    /**
     * 修改
     *
     * @param config
     * @param ctx
     * @return
     */
    public String renameAddress(LDAPConfig config, LdapContext ctx) {
        return null;
    }
}
