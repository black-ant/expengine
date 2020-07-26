//CHECKSTYLE:OFF
package com.gang.etl.ldap.utils;

import com.para.commons.core.error.CommonErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.config.LdapGlobalConfig;
import para.sdk.sync.ldap.source.LDAPConnect;
import para.sdk.sync.ldap.to.LDAPFilterTO;
import para.sdk.sync.ldap.to.LDAPPageFilterTO;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.ObjectClass;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Classname SearchUtils
 * @Description TODO
 * @Date 2020/2/21 13:28
 * @Created by zengzg
 */
public final class SearchUtils {

    private static Logger LOG = LoggerFactory.getLogger(SearchUtils.class);

    public static final String COOKIE = "cookie";

    public static List<Attributes> doSearch(LdapContext ctx, LDAPFilterTO ldapFilterTO) throws NamingException {

        if (StringUtils.isBlank(ldapFilterTO.getSearchContext())) {
            throw new SyncException(
                    "Search BaseContext Empty : Please Add Config BaseContext or Add Search " + "BaseContext");
        }

        LOG.info("------>Base :{} -> search Filter :{} <-------", ldapFilterTO.getSearchContext(),
                ldapFilterTO.getSearchFilter());

        try {
            return doSearchByFilter(ctx, ldapFilterTO.getSearchContext(), ldapFilterTO.getScope(),
                    ldapFilterTO.getSearchFilter());
        } catch (NamingException e) {
            LOG.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
            throw e;
        }

    }

    /**
     * 通过 searchFilter 查询语句搜索
     *
     * @param ctx
     * @param BaseContext
     * @param scope        : OBJECT_SCOPE(搜索指定的命名对象),
     *                     ONELEVEL_SCOPE(只搜索指定命名对象的一个级别),
     *                     SUBTREE_SCOPE(搜索以指定命名对象为根结点的整棵树)
     * @param searchFilter
     * @return
     * @throws NamingException
     */
    public static List<Attributes> doSearchByFilter(LdapContext ctx, String BaseContext, Integer scope,
                                                    String searchFilter) throws NamingException {

        LOG.info("------> Search Filter :{} <-------", searchFilter);

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(scope);

        // 额外操作 : 定义查询属性 , 此处可以一定程度优化查询效率,  可以定制
        //        String[] attrPersonArray = {"uid", "userPassword", "displayName", "cn", "sn", "mail", "description"};
        //        searchCtls.setReturningAttributes(attrPersonArray);

        List<Attributes> attrList = new ArrayList<>();
        final NamingEnumeration<SearchResult> ne = ctx.search(BaseContext, searchFilter, searchCtls);
        while (ne.hasMoreElements()) {
            SearchResult sr = ne.next();
            attrList.add(buildSearchAttr(sr.getAttributes()));
        }
        return attrList;
    }

    /**
     * 分页查询
     *
     * @param ctx              容器
     * @param ldapPageFilterTO LDAPPageFilterTO 对象
     * @return
     * @throws NamingException
     */
    public static List<Attributes> doSearchByFilterPage(LdapContext ctx, LDAPPageFilterTO ldapPageFilterTO) throws NamingException {

        LOG.info("------> Search Filter :{} <-------", ldapPageFilterTO.getSearchFilter());

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(ldapPageFilterTO.getScope());

        // 额外操作 : 定义查询属性 , 此处可以一定程度优化查询效率,  可以定制
        //        String[] attrPersonArray = {"uid", "userPassword", "displayName", "cn", "sn", "mail", "description"};
        //        searchCtls.setReturningAttributes(attrPersonArray);

        byte[] cookie = ldapPageFilterTO.getCookie();
        Control[] ctls = new Control[0];

        try {
            ctls = new Control[]{new PagedResultsControl(ldapPageFilterTO.getSize(), cookie, Boolean.TRUE)};
        } catch (IOException e) {
            if (LdapGlobalConfig.GLOBAL_DEBUG) {
                e.printStackTrace();
            }
            LOG.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
        }

        ctx.setRequestControls(ctls);
        List<Attributes> attrList = new ArrayList<>();
        final NamingEnumeration<SearchResult> ne = ctx.search(ldapPageFilterTO.getSearchContext(),
                ldapPageFilterTO.getSearchFilter(), searchCtls);

        // 返回结果
        while (ne.hasMoreElements()) {
            SearchResult sr = ne.next();
            attrList.add(buildSearchAttr(sr.getAttributes()));

        }

        // 新 Cookie 生成
        cookie = parseControls(ctx.getResponseControls());
        ldapPageFilterTO.setCookie(cookie);

        return attrList;
    }

    // 增量查询 cookie处理
    public static byte[] parseControls(Control[] controls) throws NamingException {
        byte[] cookie = null;
        if (controls != null) {
            for (int i = 0; i < controls.length; i++) {
                if (controls[i] instanceof PagedResultsResponseControl) {
                    PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
                    cookie = prrc.getCookie();
                }
            }
        }
        return (cookie == null) ? new byte[0] : cookie;
    }

    public static Attributes buildSearchAttr(Attributes attrs) {
        attrs.remove(DefaultProperties.LDAP_USER_PASSWORD);
        return attrs;
    }

    /**
     * 通过主键(ObjectGUID , distinguishedName ,Sa) 获取 EntryDN
     *
     * @param connect
     * @param key
     * @return
     */
    public static String doSearchEntryDNByObjectKey(LDAPConnect connect, String key, ObjectClass objectClass) {
        Attributes attrs = doSarchByObjectKey(connect, key, objectClass);
        return attrs == null ? null : AttributeUtils.getAttributorValue(attrs,
                DefaultProperties.DNPARAMNAME);
    }

    /**
     * 通过主键(ObjectGUID , distinguishedName ,Sa)
     *
     * @param connect
     * @param key
     * @return
     */
    public static Attributes doSarchByObjectKey(LDAPConnect connect, String key, ObjectClass objectClass) {
        List<Attributes> list = new ArrayList<>();

        if (StringUtils.isBlank(key)) {
            throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Object key is empty ");
        }

        try {
            SearchWrapper wrapper = SearchWrapper.buid();
            if (ADUtils.isDN(key)) {
                wrapper.eq(DefaultProperties.DNPARAMNAME, key);
            } else if (ADUtils.isGUID(key)) {
                // LOOK : 此处直接Return 了
                return buildSearchAttr(SearchUtils.searchByGUID(connect.getCtx(), key));

            } else {
                wrapper.eq(DefaultProperties.SAM_ACCOUNT_NAME, key);
            }

            // 针对性查询 , 可以提高效率
            String searchFilter = null == objectClass ? wrapper.getFilterStr() :
                    SearchUtils.getSearchClassFilter(objectClass, wrapper.getFilterStr());

            list = SearchUtils.doSearchByFilter(connect.getCtx(), connect.getConfig().getBaseContxt(),
                    SearchControls.SUBTREE_SCOPE, searchFilter);
        } catch (NamingException e) {
            LOG.error("E---->searchByObjectKey  error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * Search : 获取 DN 对应的属性
     *
     * @param ctx
     * @param entryDN
     * @param attributes
     * @return
     */
    public static Attributes getAttributesByEntryDN(final LdapContext ctx, final String entryDN,
                                                    final String... attributes) {
        try {
            return ctx.getAttributes(entryDN, attributes);
        } catch (NamingException e) {
            LOG.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        return null;
    }

    /**
     * 通过GUID 获取 DN
     *
     * @param ctx
     * @param guid
     * @return
     * @throws NamingException
     */
    public static String searchDNByGUID(LdapContext ctx, String guid) throws NamingException {
        Attributes attrs = ctx.getAttributes("<GUID=" + guid + ">");
        return AttributeUtils.getAttributorValue(attrs, "distinguishedName");
    }

    /**
     * 通过 Guid 获取所有属性
     *
     * @param ctx
     * @param guid
     * @return
     * @throws NamingException
     */
    public static Attributes searchByGUID(LdapContext ctx, String guid) throws NamingException {
        return ctx.getAttributes("<GUID=" + guid + ">");
    }

    /**
     * @Title: getParentDn
     * @Description: 通过上级组织的objectGUID，然后进行AD域查询，返回刚objectGUID对应的组织DN
     * @param: @param refParent
     * @param: @return
     * @return: String
     */
    public static String getEntryDNByGUID(LdapContext ctx, String guid) {
        return AttributeUtils.getAttrValueStr(getAttributedByGUID(ctx, guid, "distinguishedName"));
    }

    public static Attribute getAttributedByGUID(LdapContext ctx, String guid, String fieldKey) {
        try {
            Attributes attributes = searchByGUID(ctx, guid);
            return attributes.get(fieldKey);
        } catch (NamingException e) {
            LOG.error("E----> getAttributedByGUID Error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return null;
    }

    /**
     * 通过EntryDN 获取所有属性
     *
     * @param ctx
     * @param entryDN
     * @return
     */
    public static Attributes getAllAttrByEntryDN(final LdapContext ctx, String entryDN) {
        try {
            return getAttributesDByEntryDN(ctx, entryDN, null);
        } catch (NamingException e) {
            LOG.error("E----> getEntryGUIDByEntryDN Error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return null;
    }

    /**
     * 通过entrydn 获取指定属性
     *
     * @param ctx
     * @param entryDN
     * @param attrKey
     * @return
     */
    public static Attribute getAttrByEntryDBN(final LdapContext ctx, String entryDN, String attrKey) {
        try {
            return getAttributesDByEntryDN(ctx, entryDN, attrKey).get(attrKey);
        } catch (NamingException e) {
            LOG.error("E----> getEntryGUIDByEntryDN Error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return null;
    }

    public static String getGuidStrByEntryDN(final LdapContext ctx, String entryDN) {
        try {
            return GUIDUtils.getGuidAsString((byte[]) getGuidByEntryDN(ctx, entryDN).get());
        } catch (NamingException e) {
            LOG.error("E----> getEntryGUIDByEntryDN Error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return null;
    }

    public static Attribute getGuidByEntryDN(final LdapContext ctx, String entryDN) throws NamingException {
        try {
            return getAttributesDByEntryDN(ctx, entryDN, DefaultProperties.OBJECTGUID)
                    .get(DefaultProperties.OBJECTGUID);
        } catch (NamingException e) {
            LOG.error("E----> getEntryGUIDByEntryDN Error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return null;
    }

    public static Attributes getAttributesDByEntryDN(final LdapContext ctx, final String entryDN,
                                                     final String... attributes) throws NamingException {
        ctx.setRequestControls(null); //4月19日调整，分页查询 后使用会报ldap error 12，所以移除分页对象
        return ctx.getAttributes(entryDN, attributes);
    }

    public static void main(String[] args) {
        String filter = SearchUtils.getSearchClassFilter(ObjectClass.ACCOUNT, wrapper -> wrapper.eq("name", "111"));
        LOG.info("------> this is wrapper :{} <-------", filter);
    }

    /**
     * consumer 用法
     *
     * @param objectClass
     * @param consumer
     * @return
     */
    public static String getSearchClassFilter(ObjectClass objectClass, Consumer<SearchWrapper> consumer) {

        SearchWrapper filter = SearchWrapper.buid();
        consumer.accept(filter);
        return getSearchClassFilter(objectClass, filter.getFilterStr());
    }

    /**
     * 获取searchFilter
     *
     * @param objectClass
     * @param filterStr
     * @return
     */
    public static String getSearchClassFilter(ObjectClass objectClass, String filterStr) {
        SearchWrapper wrapperFilter = SearchWrapper
                .buidAndString(wrapper -> wrapper.eq("objectClass", objectClass.getObjectClassSearchInfo()), filterStr);
        LOG.info("------> wrapperFilter is :{} <-------", wrapperFilter.getFilterStr());
        return wrapperFilter.getFilterStr();
    }


    public static String getSearchClassFilter(ObjectClass objectClass) {
        return "(objectClass=" + getObjectClassSearchPrefix(objectClass) + ")";
    }

    public static String getEqualsFilter(String key, String value) {
        if ("objectGUID".equals(key)) {
            return "(" + key + "=" + ADUtils.guidHexExchange(value) + ")";
        }
        return "(" + key + "=" + value + ")";
    }

    private SearchUtils() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * -> 编辑搜索字段
     *
     * @param searchType
     * @return
     */
    public static String editsearchType(String searchType) {
        String searchFiled = "cn";
        if ("CN".equals(searchType) || "cn".equals(searchType)) {
            searchFiled = "cn";
        } else if ("dn".equals(searchType) || "distinguishedName".equals(searchType)) {
            searchFiled = "distinguishedName";
        } else if ("ou".equals(searchType)) {
            searchFiled = "OU";
        } else if ("usercode".equals(searchType)) {
            searchFiled = "sAMAccountName";
        }
        return searchFiled;
    }

    /**
     * 获取 Object Class Search 条件
     *
     * @param objclass
     * @return
     */
    public static String getObjectClassSearchPrefix(ObjectClass objclass) {
        String oclass = "*";
        if (!objclass.is(ObjectClass.ALL_NAME)) {
            oclass = objclass.getObjectClassLdapPrefix();
        }
        return oclass;
    }

}
