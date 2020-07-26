package com.gang.etl.ldap.config;

import org.springframework.stereotype.Component;
import para.sdk.common.sync.SyncBaseConfig;
import para.sdk.sync.ldap.type.ADAnnotation;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.LDAPVersion;
import para.sdk.sync.ldap.type.LdapAuthType;

/**
 * @Classname LDAPConfig
 * @Description TODO
 * @Date 2020/2/25 15:14
 * @Created by zengzg
 */
@Component
public class LDAPConfig {


    @ADAnnotation(notNull = true, description = "服务器地址")
    private String host;

    @ADAnnotation(notNull = true, description = "服务器端口")
    private String port = "389";

    @ADAnnotation(notNull = true, description = "账户名")
    private String account;

    @ADAnnotation(notNull = true, description = "密码")
    private String password;

    /**
     * 是否开启SSL
     */
    private Boolean openSSL = Boolean.FALSE;

    /**
     * 认证方式
     */
    private LdapAuthType authType;

    /**
     * 根组织
     */
    @ADAnnotation(notNull = false, description = "根域名")
    private String baseContxt;

    /**
     * 超时时间
     */
    private Integer connectTimeout = 1000 * 60;
    private Integer readTimeout = 1000 * 60;

    /**
     * 默认主键类型
     */
    private String defaultUUID = DefaultProperties.OBJECTGUID;

    /**
     * LDAP 版本
     */
    private LDAPVersion ldapVersion = LDAPVersion.WINAD2012;


    public LDAPConfig openLdapSSL() {
        this.setOpenSSL(Boolean.TRUE);
        return this;
    }

    public LDAPConfig closeLdapSSL() {
        this.setOpenSSL(Boolean.FALSE);
        return this;
    }

    public LDAPConfig noneAuth() {
        this.setAuthType(LdapAuthType.NONE);
        return this;
    }

    public LDAPConfig baseContext(String baseContxt) {
        this.setBaseContxt(baseContxt);
        return this;
    }

    public LDAPConfig defaultUUID(String properties) {
        this.defaultUUID = properties;
        return this;
    }

    public LDAPConfig ldapVersion(LDAPVersion ldapVersion) {
        this.ldapVersion = ldapVersion;
        return this;
    }

    public LDAPConfig() {
        super();
    }

    public LDAPConfig(String host, String port, String account, String password, Boolean openSSL) {
        this.host = host;
        this.port = port;
        this.account = account;
        this.password = password;
        this.openSSL = openSSL;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getOpenSSL() {
        return openSSL;
    }

    public void setOpenSSL(Boolean openSSL) {
        this.openSSL = openSSL;
    }

    public String getBaseContxt() {
        return baseContxt;
    }

    public void setBaseContxt(String baseContxt) {
        this.baseContxt = baseContxt;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getDefaultUUID() {
        return defaultUUID;
    }

    public void setDefaultUUID(String defaultUUID) {
        this.defaultUUID = defaultUUID;
    }

    public LDAPVersion getLdapVersion() {
        return ldapVersion;
    }

    public void setLdapVersion(LDAPVersion ldapVersion) {
        this.ldapVersion = ldapVersion;
    }

    public LdapAuthType getAuthType() {
        return authType;
    }

    public void setAuthType(LdapAuthType authType) {
        this.authType = authType;
    }
}
