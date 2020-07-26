//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import com.para.commons.core.error.CommonErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.common.trust.TrustAllSocketFactory;
import para.sdk.sync.ldap.config.LdapGlobalConfig;
import para.sdk.sync.ldap.error.ErrorLogic;
import para.sdk.sync.ldap.to.LDAPFilterTO;
import para.sdk.sync.ldap.to.LDAPPageFilterTO;
import para.sdk.sync.ldap.to.LDAPUpdateTO;
import para.sdk.sync.ldap.to.config.LDAPConfig;
import para.sdk.sync.ldap.to.config.LdapBaseConfig;
import para.sdk.sync.ldap.type.ADAnnotation;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.LdapAuthType;
import para.sdk.sync.ldap.type.ObjectClass;
import para.sdk.sync.ldap.utils.ADUtils;
import para.sdk.sync.ldap.utils.SearchWrapper;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Classname LDAPBaseSource
 * @Description TODO
 * @Date 2020/2/23 22:24
 * @Created by zengzg
 */
@Component
public class LDAPConnect {

    private static Logger LOG = LoggerFactory.getLogger(LDAPConnect.class);

    private LdapContext ctx;
    private LDAPConfig config;

    private final static Integer MAX_POOL_CONNECT = 5;
    private static Map<Integer, LDAPConnect> connectMap = new HashMap<>();

    // 单个连接最多保留时间 (时间过长socket 会断开)
    private final static Integer MAX_TIME = 1000 * 60 * 30;
    private Date lastDate;

    @Autowired
    private CreateSource createSource;
    @Autowired
    private UpdateSource updateSource;
    @Autowired
    private DeleteSource deleteSource;
    @Autowired
    private SearchSource searchSource;

    public LDAPConnect() {
    }

    public LDAPConnect(LDAPConfig config) {
        this.config = config;
    }


    /**
     * 构建 Ldap Connect 连接器 , 该途径会通过连接池
     *
     * @param baseConfig
     * @return
     */
    public static LDAPConnect initBuild(LdapBaseConfig baseConfig) {

        if (null == baseConfig || null == baseConfig.getLdapConfig()) {
            throw new SyncException(CommonErrorEnum.INIT_ERROR, "Config is empty");
        }

        LDAPConfig config = baseConfig.getLdapConfig();
        LDAPConnect ldapConnect;
        Integer hashKey = getKeyHash(config);
        if (!connectMap.isEmpty() && connectMap.containsKey(hashKey) && isNeedRefush(connectMap.get(hashKey))) {
            ldapConnect = connectMap.get(hashKey);
            if (!ldapConnect.checkAlive()) {
                LOG.warn("------> this Socket is close , add new Context <-------");
                ldapConnect.setCtx(createLdapContext(config));
            } else {
                LOG.info("W----> user cache connect : {} -- {} ", hashKey, baseConfig.getLdapConfig().getHost());
            }
        } else {
            LOG.warn("W----> Create New Connect ");
            ldapConnect = new LDAPConnect(config);
            ldapConnect.setCtx(createLdapContext(config));
            connectMap.put(hashKey, ldapConnect);
        }

        // 保证策略性配置正确
        ldapConnect.setLastDate(new Date());
        ldapConnect.setConfig(config);

        return ldapConnect;
    }


    /**
     * 基于 LdapContext 直接构建连接器 , 单例非连接池
     *
     * @param baseConfig
     * @return
     */
    public static LDAPConnect initBuildSingle(LdapBaseConfig baseConfig) {
        LDAPConnect ldapConnect = new LDAPConnect();
        LDAPConfig config = baseConfig.getLdapConfig();
        ldapConnect.setConfig(config);
        ldapConnect.setLastDate(new Date());
        ldapConnect.setCtx(createLdapContext(config));
        ldapConnect.setLastDate(new Date());
        return ldapConnect;
    }

    /**
     * ============================== Connect 暴露接口 ==================================
     **/

    /**
     * 创建用户
     *
     * @param objectClass {@link ObjectClass}
     * @param attrs
     * @return Attributes
     */
    public String doCreate(ObjectClass objectClass, Attributes attrs) {
        CreateSource source = createSource == null ? new CreateSource() : createSource;
        return source.executeImpl(this, objectClass, attrs);
    }

    /**
     * 更新 LDAP 用户
     *
     * @param objectClass  {@link ObjectClass}
     * @param ldapUpdateTO
     * @return Attributes
     */
    public String doUpdate(ObjectClass objectClass, LDAPUpdateTO ldapUpdateTO) {
        UpdateSource source = updateSource == null ? new UpdateSource() : updateSource;
        return source.executeImpl(this, objectClass, ldapUpdateTO);
    }

    /**
     * 删除 Ldap 用户
     *
     * @param objectClass {@link ObjectClass}
     * @param filterStr
     * @return Attributes
     */
    public List<String> doDelete(ObjectClass objectClass, LDAPFilterTO filterStr) {
        DeleteSource source = deleteSource == null ? new DeleteSource() : deleteSource;
        return source.executeImpl(this, objectClass, filterStr);
    }

    /**
     * 通过 ID 删除 对象
     *
     * @param objectGUID {@link ObjectClass}
     * @return Attributes
     */
    public String doDeleteId(String objectGUID) {
        DeleteSource source = deleteSource == null ? new DeleteSource() : deleteSource;
        return source.executeImpl(this, objectGUID);
    }

    /**
     * LDAPFilterTO 进行搜索
     *
     * @param objectClass {@link ObjectClass}
     * @param filterTO
     * @return Attributes
     */
    public List<Attributes> doSearch(ObjectClass objectClass, LDAPFilterTO filterTO) {
        SearchSource source = searchSource == null ? new SearchSource() : searchSource;
        return source.executeImpl(this, objectClass, filterTO);
    }

    /**
     * 根据objectClass属性查询
     *
     * @param objectClass
     * @param filterTO
     * @return
     */
    public List<Attributes> doSearchByObjectClass(String objectClass, LDAPFilterTO filterTO) {
        SearchSource source = searchSource == null ? new SearchSource() : searchSource;
        SearchWrapper wrapperFilter = SearchWrapper
                .buidAndString(wrapper -> wrapper.eq("objectClass", objectClass), filterTO.getSearchFilter());
        filterTO.setSearchFilter(wrapperFilter.getFilterStr());
        return source.executeImpl(this, null, filterTO);
    }

    public List<Attributes> doSearchByObjectClassType(LDAPFilterTO filterTO) {
        SearchSource source = searchSource == null ? new SearchSource() : searchSource;

        return source.executeImpl(this, null, filterTO);
    }

    public List<Attributes> doSearchByName(String name, LDAPFilterTO filterTO) {
        SearchSource source = searchSource == null ? new SearchSource() : searchSource;
        SearchWrapper wrapperFilter = SearchWrapper
                .buidAndString(wrapper -> wrapper.eq("name", name), filterTO.getSearchFilter());
        filterTO.setSearchFilter(wrapperFilter.getFilterStr());
        return source.executeImpl(this, null, filterTO);
    }


    /**
     * 分页搜索
     *
     * @param objectClass {@link ObjectClass}
     * @param filterTO
     * @return Attributes
     */
    public List<Attributes> doSearchPage(ObjectClass objectClass, LDAPPageFilterTO filterTO) {
        SearchSource source = searchSource == null ? new SearchSource() : searchSource;
        return source.executeImpl(this, objectClass, filterTO);
    }

    /**
     * 通过 GUID 进行搜索
     *
     * @param objectGUID {@link ObjectClass}
     * @return Attributes
     */
    public Attributes doSearchId(String objectGUID) {
        SearchSource source = searchSource == null ? new SearchSource() : searchSource;
        return source.executeImpl(ctx, objectGUID);
    }

    /**
     * ============================== Connect 暴露接口结束 ==================================
     **/


    /**
     * TODO : 长时间未连接 , 清空连接 ,此时Socket 已断开
     */
    private static boolean isNeedRefush(LDAPConnect connect) {
        return connect == null ? Boolean.FALSE : (new Date().getTime() - connect.getLastDate().getTime()) < MAX_TIME;
    }

    /**
     * 获取函数Hash值
     *
     * @return
     */
    private static int getKeyHash(LDAPConfig config) {
        String hashKey = config.getHost() + config.getPort() + String.valueOf(config.getOpenSSL());
        int hash = 0;

        for (int i = 0; i < hashKey.length(); i++) {
            hash ^= (hash << 5) + (int) hashKey.charAt(i) + (hash >> 2);
        }
        hash += new Random().nextInt(MAX_POOL_CONNECT);
        return hash & 0x7FFFFFFF;
    }

    /**
     * 生成连接器环境信息
     *
     * @param config
     * @return
     */
    public static LdapContext createLdapContext(LDAPConfig config) {

        // Config 必填校验
        checkConfig(config);

        final java.util.Hashtable<Object, Object> env = new java.util.Hashtable<Object, Object>();

        env.put(Context.INITIAL_CONTEXT_FACTORY, DefaultProperties.LDAP_CTX_FACTORY);

        // 开启SSL / 信任密钥
        if (config.getOpenSSL()) {
            env.put(Context.SECURITY_PROTOCOL, "ssl");
            env.put(DefaultProperties.LDAP_CTX_SOCKET_FACTORY, TrustAllSocketFactory.class.getName());
        }

        // 构建LDAP 访问地址
        env.put(Context.PROVIDER_URL, getLdapUrls(config));
        env.put(Context.REFERRAL, "follow");


        env.put(DefaultProperties.LDAP_BINARY_ATTRIBUTE,
                DefaultProperties.SDDL_ATTR + " " + DefaultProperties.OBJECTGUID + " " + DefaultProperties.OBJECTSID);

        // 访问当时 账号 / 密码
        if (LdapAuthType.NONE.equals(config.getAuthType())) {
            env.put(Context.SECURITY_AUTHENTICATION, LdapAuthType.NONE.getCode());
            env.put(Context.PROVIDER_URL, env.get(Context.PROVIDER_URL) + "/" + config.getAccount());
        } else if (LdapAuthType.STRONG.equals(config.getAuthType())) {
            throw new SyncException(CommonErrorEnum.SDK_SYNC_INIT, "System Error , not support LdapAuthType : Strong");
        } else {
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, config.getAccount());
            env.put(Context.SECURITY_CREDENTIALS, config.getPassword());
        }

        // 超时时间
        env.put("com.sun.jndi.ldap.connect.timeout", Long.toString(config.getConnectTimeout()));
        env.put("com.sun.jndi.ldap.read.timeout", Long.toString(config.getReadTimeout()));

        LdapContext context = null;
        try {
            context = new InitialLdapContext(env, null);
        } catch (NamingException e) {
            if (LdapGlobalConfig.GLOBAL_DEBUG) {
                e.printStackTrace();
            }
            LOG.error("------> Init Context Error ,Please Check You Config <-------");
            ErrorLogic.buidSyncException(e);
        } catch (Exception e) {
            LOG.error("------> Init Context Error ,Please Check You Config  :{}<-------",
                    e.getClass() + e.getMessage());
            throw new SyncException(CommonErrorEnum.SDK_SYNC_INIT, "System Error , Obvious error in Config");
        }
        if (context == null) {
            throw new SyncException(CommonErrorEnum.SDK_SYNC_INIT, "Create LdapContext failed, Obvious error in Config");
        }
        return context;
    }

    /**
     * 对必填属性进行校验
     *
     * @param config
     */
    public static void checkConfig(LDAPConfig config) {

        List<Field> fields = FieldUtils.getAllFieldsList(config.getClass());
        fields.stream().forEach(field -> {
            try {
                field.setAccessible(Boolean.TRUE);
                if (field.getAnnotation(ADAnnotation.class) != null && field.getAnnotation(ADAnnotation.class).notNull()
                        && null == field.get(config)) {
                    if (LdapAuthType.NONE.equals(config.getAuthType()) && field.getName().equals("account") || field.getName().equals("password")) {
                        LOG.debug("------> LdapAuthType.NONE not need account and password <-------");
                    } else {
                        throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, field.getName() + " Param can't be null"
                                , field.getName(), field.getAnnotation(ADAnnotation.class).description());
                    }
                }
            } catch (IllegalAccessException e) {
                LOG.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
            }
        });

    }

    /**
     * 测试是否可以连接
     *
     * @return
     */
    public Boolean checkAlive() {
        try {
            final Attributes attrs = ctx.getAttributes("", new String[]{"subschemaSubentry"});
            attrs.get("subschemaSubentry");
        } catch (Exception e) {
            LOG.error("E----> checkAlive error :{} -- content :{}", e.getClass(), e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 获取LDAP 连接地址
     *
     * @param config
     * @return
     */
    private static String getLdapUrls(LDAPConfig config) {
        final StringBuilder builder = new StringBuilder();
        if (LdapAuthType.NONE.equals(config.getAuthType())) {
            if (StringUtils.isEmpty(config.getBaseContxt())) {
                throw new SyncException("LDAP No BaseContext");
            }
            //            String baseOu = ADUtils.getParentDN(config.getBaseContxt());
            String baseOu = ADUtils.entryDNName(config.getBaseContxt());
            LOG.debug("------> baseOu :{} <-------", baseOu);
            builder.append("ldap://").append(config.getHost()).append(':').append(config.getPort()).append("/").append("ou=").append(baseOu);
        } else {
            builder.append("ldap://").append(config.getHost()).append(':').append(config.getPort());
        }
        return builder.toString();
    }


    public LdapContext getCtx() {
        return ctx;
    }

    public void setCtx(LdapContext ctx) {
        this.ctx = ctx;
    }

    public LDAPConfig getConfig() {
        return config;
    }

    public void setConfig(LDAPConfig config) {
        this.config = config;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
