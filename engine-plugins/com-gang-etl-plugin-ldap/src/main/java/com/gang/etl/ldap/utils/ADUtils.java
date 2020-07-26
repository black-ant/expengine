//CHECKSTYLE:OFF
package com.gang.etl.ldap.utils;

import com.gang.etl.ldap.type.DefaultProperties;
import com.gang.etl.ldap.type.ObjectClass;
import com.para.commons.core.error.CommonErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.config.LdapGlobalConfig;
import para.sdk.sync.ldap.source.LDAPConnect;
import para.sdk.sync.ldap.to.config.LDAPConfig;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.ObjectClass;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Classname ADUtils
 * @Description TODO
 * @Date 2020/2/19 14:13
 * @Created by zengzg
 */
public final class ADUtils {

    private static Logger LOG = LoggerFactory.getLogger(ADUtils.class);

    public static final int UF_ACCOUNTDISABLE = 0x0002;

    public static final int UF_PASSWD_NOTREQD = 0x0020;

    public static final int UF_PASSWD_CANT_CHANGE = 0x0040;

    public static final int UF_NORMAL_ACCOUNT = 0x0200;

    public static final int UF_DONT_EXPIRE_PASSWD = 0x10000;

    public static final int UF_PASSWORD_EXPIRED = 0x800000;

    public static final String UACCONTROL_ATTR = "userAccountControl";

    public static final String PASSWORD_ATTR = "userPassword";

    private ADUtils() {
        super();
    }

    /**
     * 获取 父类 路径
     *
     * @param attributes
     * @return
     */
    public static String getParentEntryDN(LdapContext ctx, Attributes attributes) {

        // 父组织 EntryDN
        String orgEntryDN = null;

        Attribute orgEntryDNAttr = attributes.get(DefaultProperties.OUT_PARENT);
        if (orgEntryDNAttr != null) {
            if (isDN(AttributeUtils.getAttrValueStr(orgEntryDNAttr))) {
                orgEntryDN = AttributeUtils.getAttrValueStr(orgEntryDNAttr);
            } else {
                orgEntryDN = SearchUtils.getEntryDNByGUID(ctx, AttributeUtils.getAttrValueStr(orgEntryDNAttr));
            }
        }
        return orgEntryDN;
    }

    /**
     * 获取OUName 全路径 : create 时 addressOld 为 null
     *
     * @param context
     * @param config
     * @param addressOld
     * @param attrs
     * @param objectClass
     * @return
     */
    public static String buildNewAddress(LdapContext context, LDAPConfig config, String addressOld, Attributes attrs,
                                         ObjectClass objectClass) {

        String newAddress = "";
        String cnName = "";
        if (!objectClass.is(objectClass.ALL_NAME)) {
            if (objectClass.is(objectClass.ORGANIZATION_NAME)) {
                cnName = AttributeUtils.getAttrValueStr(attrs.get(DefaultProperties.LDAP_OU));
            } else {
                cnName = AttributeUtils.getAttrValueStr(attrs.get(DefaultProperties.LDAP_OU_NAME));
            }
        } else {
            cnName = AttributeUtils.getAttrValueStr(attrs.get(getCnOrOu(attrs.get("objectClass")).toLowerCase()));
        }

        String parentGuid = AttributeUtils.getAttrValueStr(attrs.get(DefaultProperties.OUT_PARENT));

        // 创建操作 ,Address 为null
        String parentAddress = "";
        if (addressOld == null) {
            // 获取父组织路径
            parentAddress = ADUtils.getParentEntryDN(context, attrs);

            if (StringUtils.isBlank(parentAddress)) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "No parent organization found , Please check" +
                        " org attribute");
            }

        } else {

            // STEP 1 : 是否需要生成 CNName
            cnName = StringUtils.isBlank(cnName) ? ADUtils.entryDNName(addressOld) : cnName;

            // STEP 2 : 获取旧父组织及新父组织
            String parentAddressOld = ADUtils.entryDNParent(addressOld);

            // STEP 3 : 获取新组织OU
            String parentOU = ADUtils.getParentEntryDN(context, attrs);

            if (StringUtils.isNotBlank(parentGuid) && StringUtils.isBlank(parentOU)) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "No parent organization found , Please check" +
                        " org attribute");
            }

            // STEP 4 : 生成新组织OU
            String parentAddressNew = StringUtils.isBlank(parentOU) ? parentAddressOld : parentOU;

            // 如果名称不同 或者 父组织不同 , 则生成新名称
            if (ADUtils.entryDNName(addressOld).equals(cnName) && parentAddressNew.equals(parentAddressOld)) {
                parentAddress = parentAddressOld;
            } else {
                parentAddress = parentAddressNew;
            }
        }

        if (attrs.get("objectClass") != null) {
            newAddress = getCnOrOu(attrs.get("objectClass")) + "=" + cnName + "," + parentAddress;
        } else {
            if (!objectClass.is(ObjectClass.ALL_NAME)) {
                if (objectClass.is(ObjectClass.ORGANIZATION_NAME)) {
                    newAddress = "OU" + "=" + cnName + "," + parentAddress;
                } else {
                    newAddress = "CN" + "=" + cnName + "," + parentAddress;
                }
            } else {
                throw new SyncE(CommonErrorEnum.PARAMETER_ERROR, "objectClass is null");
            }
        }


        LOG.info("------> this new Address is :{} <-------", newAddress);
        return newAddress;

    }

    public static String getCnOrOu(Attribute attribute) {
        if (attribute == null) {
            throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "objcetClass is null");
        }
        try {
            NamingEnumeration e = attribute.getAll();
            while (e.hasMore()) {
                if ("organizationalUnit".equals(e.next().toString())) {
                    return "OU";
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return "CN";
    }


    /**
     * 获取  Attributes 中 OU Name(LDAP_OU_NAME) {@link DefaultProperties }
     *
     * @param attributes
     * @return Attribute
     */
    public static Attribute getOuNameAttr(Attributes attributes) {
        // 获取OUName
        Attribute ouNameAttr = attributes.get(DefaultProperties.LDAP_OU_NAME);
        ouNameAttr = ouNameAttr == null ? attributes.get(DefaultProperties.LDAP_SHOW_NAME) : ouNameAttr;
        return ouNameAttr;
    }

    /**
     * 判断是否为DN
     *
     * @param entryDN
     * @return
     */
    public static Boolean isDN(String entryDN) {
        if (entryDN.contains("DC=")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断是否为objectGUID
     *
     * @param key
     * @return
     */
    public static Boolean isGUID(String key) {
        if (key.length() > 30) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * @param entryDN
     * @return
     */
    public static String entryDNName(String entryDN) {
        String indexSign = entryDN.substring(2).indexOf(DefaultProperties.LDAP_OU_UP) > 0
                ? DefaultProperties.LDAP_OU_UP
                : DefaultProperties.LDAP_DC_UP;

        return entryDN.substring(3, entryDN.substring(2).indexOf(indexSign + "=") + 1);  //4月19日调整 避免名称中包含OU
    }

    /**
     * 获取entryDN 父组织路径
     *
     * @param entryDN
     * @return
     */
    public static String entryDNParent(String entryDN) {

        // example 1 : OU=武汉研发,DC=eicdevdomain,DC=in
        // example 2 : OU=Org254,OU=武汉研发,DC=eicdevdomain,DC=in

        String indexSign = entryDN.substring(2).indexOf(DefaultProperties.LDAP_OU_UP) > 0
                ? DefaultProperties.LDAP_OU_UP
                : DefaultProperties.LDAP_DC_UP;

        return entryDN.substring(entryDN.substring(2).indexOf(indexSign + "=") + 2); //4月19日调整 避免名称中包含OU
    }


    /**
     * 判断类型(GUID / EntryDN)并且返回EntryDN
     *
     * @param context
     * @param str
     * @return
     */
    public static String checkTypeReturnEntryDN(LdapContext context, String str) {
        if (ADUtils.isDN(str)) {
            return str;
        } else {
            return SearchUtils.getEntryDNByGUID(context, str);
        }
    }

    /**
     * 返回GUIDS
     *
     * @param ouAddress
     * @return
     */
    public static String backGUIDStr(LDAPConnect connect, String ouAddress) {

        String defaultUUID = connect.getConfig().getDefaultUUID();
        try {
            Attributes profile = connect.getCtx().getAttributes(ouAddress, new String[]{defaultUUID});

            if (profile.size() == 0) {
                defaultUUID = !defaultUUID.equals(DefaultProperties.ENTRYUUID) ? DefaultProperties.ENTRYUUID :
                        DefaultProperties.OBJECTGUID;
                profile = connect.getCtx().getAttributes(ouAddress, new String[]{defaultUUID});
            }

            return defaultUUID.equals(DefaultProperties.ENTRYUUID) ? String.valueOf(profile.get(defaultUUID).get()) :
                    GUIDUtils.getGuidAsString((byte[]) profile.get(defaultUUID).get());
        } catch (NamingException e) {
            LOG.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
            throw new SyncException("Create GUID Error :" + ouAddress);
        }

    }

    /**
     * 获取 ParentDN
     *
     * @param dn
     * @return
     */
    public static String getParentDN(String dn) {
        return dn.replaceFirst("([^=]+)=([^,]+),", "");
    }

    /**
     * 获取 DN 对应的 UID
     *
     * @param entryDN
     * @return
     */
    public static Attribute getEntryID(final LdapContext ctx, String entryDN) {
        return SearchUtils.getAttributesByEntryDN(ctx, entryDN, DefaultProperties.OBJECTGUID)
                .get(DefaultProperties.OBJECTGUID);
    }

    /**
     * 获取指定的路径名称
     *
     * @param entryDN
     * @param index   : 获取路径中的第几位
     * @return
     */
    public static String getPathNameByIndex(String entryDN, Integer index) {
        String[] backStr = entryDN.split("(DC=|CN=|OU=)");
        return index == backStr.length ? backStr[index + 1] : backStr[index + 1];
    }
    //
    //    public static void main(String[] args) {
    //        LOG.info("------> this is :{} <-------", ADUtils.getPathNameByIndex("OU=武汉研发,DC=eicdevdomain,DC=in", 1));
    //    }


    /**
     * 向 Group 中添加 User
     *
     * @param entryDN
     * @param ldapGroups
     * @param ctx
     */
    public static void addUserToMemberGroup(String entryDN, Attribute ldapGroups, LdapContext ctx) {
        LOG.info("------> this is ldapgroupds :{} <-------", AttributeUtils.getAttrValueArray(ldapGroups));

        if (entryDN != null) {
            String[] groupArray = AttributeUtils.getAttrValueArray(ldapGroups);
            if (groupArray != null && groupArray.length > 0) {
                for (String groupItem : groupArray) {
                    if (StringUtils.isNotBlank(groupItem)) {
                        try {
                            String groupDN = ADUtils.isDN(groupItem) ? groupItem
                                    : SearchUtils.getEntryDNByGUID(ctx, groupItem);
                            ADUtils.addGroup(ctx, groupDN, entryDN);
                        } catch (Exception e) {
                            LOG.error("E----> add group error :{} -- content :{}", e.getClass(), e.getMessage());
                        }
                    }
                }
            }
        }
        // 异常无关属性
        // ldapGroups.remove(ldapGroups);
    }


    /**
     * 将人员添加到组中
     *
     * @param connect
     * @param member
     */
    public static void addGroupMember(LDAPConnect connect, String entryDN, Attribute member) {
        String[] groupArray = AttributeUtils.getAttrValueArray(member);
        if (groupArray != null && groupArray.length > 0) {
            for (String memberKey : groupArray) {
                if (StringUtils.isNotBlank(memberKey)) {
                    try {

                        String memberDN = SearchUtils.doSearchEntryDNByObjectKey(connect, memberKey, null);

                        ADUtils.operationAttribute(connect.getCtx(), DirContext.ADD_ATTRIBUTE, entryDN,
                                DefaultProperties.LDAP_MEMBER, memberDN);

                    } catch (Exception e) {
                        LOG.error("E----> add group error :{} -- content :{}", e.getClass(), e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * @Title: addGroupe
     * @Description: 通过修改member 修改组
     * @param: @param groupDN
     * @param: @param entryDN
     * @return: void
     */
    public static void addGroup(LdapContext ctx, String groupDN, String entryDN) {
        operationAttribute(ctx, DirContext.ADD_ATTRIBUTE, groupDN, "member", entryDN);
    }

    /**
     * 批量加组操作
     *
     * @param ctx
     * @param groupDN
     * @param entryDN
     */
    public static void addGroup(LdapContext ctx, String groupDN, List<String> entryDN) {
        try {
            ModificationItem[] mods = new ModificationItem[entryDN.size()];
            // 修改属性
            for (int i = 0; i < entryDN.size(); i++) {
                BasicAttribute attr0 = new BasicAttribute("member", entryDN.get(i));
                mods[i] = new ModificationItem(DirContext.ADD_ATTRIBUTE, attr0);
            }
            ctx.modifyAttributes(groupDN, mods);
        } catch (Exception e) {
            LOG.error("批量加组失败 : dn -> {0} , type -> {1} ,value -> {2}", groupDN, DirContext.ADD_ATTRIBUTE, entryDN);
            e.printStackTrace();

        }
    }

    /**
     * 移除组
     *
     * @param ctx
     * @param groupDN
     * @param entryDN
     */
    public static void removeGroup(LdapContext ctx, String groupDN, String entryDN) {
        operationAttribute(ctx, DirContext.REMOVE_ATTRIBUTE, groupDN, "member", entryDN);
    }

    /**
     * -> 修改属性 DirContext.ADD_ATTRIBUTE : 添加属性--1<br>
     * DirContext.REMOVE_ATTRIBUTE : 删除属性--3<br>
     * DirContext.REPLACE_ATTRIBUTE :替换属性--2<br>
     *
     * @param type       : 操作类型
     * @param sourceDN   : 被操作对象
     * @param filedname  : 操作属性
     * @param fieldValue : 修改的值
     */
    public static void operationAttribute(LdapContext ctx, int type, String sourceDN, String filedname,
                                          String fieldValue) {
        LOG.info("---> 修改属性 :sourceDN :{} -- name :{} -- value :{}", sourceDN, filedname, fieldValue);
        try {
            ModificationItem[] mods = new ModificationItem[1];
            // 修改属性
            BasicAttribute attr0 = new BasicAttribute(filedname, fieldValue);
            mods[0] = new ModificationItem(type, attr0);
            ctx.modifyAttributes(sourceDN, mods);
        } catch (NameAlreadyBoundException e) {
            LOG.info("------> this User is already in this Group <-------");
        } catch (Exception e) {
        }
    }

    /**
     * 特殊字符处理(支持 ? : )
     * 默认转义(+ > ) : 此类图标转义后 , CN 中正常 ,但是 OU 中会默认加入 \ , LDAP 规则强制要求
     * 部分字符会默认转义
     * <p>
     * 注意 : 强烈建议不要用等号 , OU 中大量使用了等号 , 等号过多后续会极难控制
     *
     * @param entryDN
     * @return
     */
    public static String exchangeIllegalEntryDN(String entryDN) {

        entryDN = entryDN.replaceAll("/", "\\\\2F");
        entryDN = entryDN.replaceAll("\\?", "\\\\3F");
        entryDN = entryDN.replaceAll("\\+", "\\\\2B");

        // TODO : = 号无法处理
        //        entryDN = entryDN.replaceAll("\\=", "\\\\3D");
        entryDN = entryDN.replaceAll("\\:", "\\\\3A");
        entryDN = entryDN.replaceAll("\\>", "\\\\3E");

        // DOTO : 后续新特殊字符可以继续添加...

        return entryDN;
    }

    /**
     * 校验必传
     *
     * @return
     */
    public static void checkAttrInfo(Attributes attrs, ObjectClass objectClass) {

        String sAMAccountName = AttributeUtils.getAttributorValue(attrs, "sAMAccountName");
        if (StringUtils.isNotBlank(sAMAccountName) && Pattern.matches(".*[?+:><=].*", sAMAccountName)) {
            throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "SAMAccountName Invalid : Can't include ?+:><=");
        }

        String userPrincipalName = AttributeUtils.getAttributorValue(attrs, DefaultProperties.USER_PRINCIPAL_NAME);
        if (StringUtils.isNotBlank(userPrincipalName) && !userPrincipalName.contains("@")) {
            throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "userPrincipalName Invalid : need include " +
                    "@[domain.com]");
        }
        if (objectClass.is(ObjectClass.ALL_NAME)) {
            String className = getObjectClass(attrs);
            // 判断CN 是否存在
            if (null == attrs.get(DefaultProperties.LDAP_CN) && !"ORG".equals(className) && attrs.size() != 1) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add cn ,cn is empty", "cn", "user/group "
                        + "cn");
            }

            if (null == attrs.get(DefaultProperties.SAM_ACCOUNT_NAME) && "USER".equals(className) && attrs.size() != 1) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add sAMAccountName",
                        "sAMAccountName", "is null");
            }

            if (null == attrs.get(DefaultProperties.LDAP_OU) && "ORG".equals(className)) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add ou ,ou is empty", "ou", "object ou");
            }

            if (null == attrs.get(DefaultProperties.LDAP_USER_PASSWORD) && "USER".equals(className)) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add Password ,Password is empty",
                        "Password", "userPassword");
            }

        } else {
            // 判断CN 是否存在
            if (null == attrs.get(DefaultProperties.LDAP_CN) && !objectClass.is(ObjectClass.ORGANIZATION_NAME) && attrs.size() != 1) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add cn ,cn is empty", "cn", "user/group "
                        + "cn");
            }

            if (null == attrs.get(DefaultProperties.SAM_ACCOUNT_NAME) && objectClass.is(ObjectClass.ACCOUNT_NAME) && attrs.size() != 1) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add sAMAccountName",
                        "sAMAccountName", "is null");
            }

            if (null == attrs.get(DefaultProperties.LDAP_OU) && objectClass.is(ObjectClass.ORGANIZATION_NAME)) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add ou ,ou is empty", "ou", "object ou");
            }

            if (null == attrs.get(DefaultProperties.LDAP_USER_PASSWORD) && objectClass.is(ObjectClass.ACCOUNT_NAME)) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add Password ,Password is empty",
                        "Password", "userPassword");
            }

        }

        // 获取父组织名
        if (null == attrs.get(DefaultProperties.OUT_PARENT)) {
            throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "Please add Org ,Org is empty", "Org", "object " +
                    "Org");
        }
    }

    /**
     * 获取关键值，校验
     *
     * @param attrs
     * @return
     */
    public static String getObjectClass(Attributes attrs) {

        NamingEnumeration e = null;
        try {
            if (attrs.get("objectClass") == null) {
                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR, "object is null");
            }
            e = attrs.get("objectClass").getAll();
            while (e.hasMore()) {
                if ("organizationalUnit".equals(e.next().toString())) {
                    return "ORG";
                }
            }
            while (e.hasMore()) {
                if ("user".equals(e.next().toString())) {
                    return "USER";
                }
            }
            while (e.hasMore()) {
                if ("group".equals(e.next().toString())) {
                    return "GROUP";
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return "NO";


    }

    /**
     * 逆向转换
     *
     * @param entryDN
     * @return
     */
    public static String checkEntryDNTurn(String entryDN) {
        entryDN = entryDN.replaceAll("\\\\2F", "/");
        return entryDN;
    }


    /**
     * 转换为 GUID , 进制处理
     *
     * @param displayGUID
     * @return
     */
    public static String guidHexExchange(String displayGUID) {
        return HexUtils.getEscaped(GUIDUtils.getGuidAsByteArray(displayGUID));
    }

    public static String getDnToAttributes(Attributes attrs) {
        String dn = "";
        try {
            dn = String.valueOf(attrs.get("distinguishedName").get(0));
            if (StringUtils.isBlank(dn)) {
                dn = String.valueOf(attrs.get("entryDN").get(0));
            }
        } catch (NamingException e) {
            LOG.error("E----> getDnToAttributes error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return dn;
    }

    public static String getObjectGUIDToAttributes(Attributes attrs) {
        String objectGUID = "";
        try {
            objectGUID = String.valueOf(attrs.get("objectGUID").get(0));
        } catch (NamingException e) {
            if (LdapGlobalConfig.GLOBAL_DEBUG) {
                e.printStackTrace();
                LOG.error("E----> getObjectGUIDToAttributes error :{} -- content :{}", e.getClass(), e.getMessage());
            }
        }
        return objectGUID;
    }

    public static String getValueByField(Attributes attrs, String field) {
        String value = "";
        try {
            Attribute attribute = attrs.get(field);
            if (attribute != null) {
                value = String.valueOf(attribute.get(0));
            }
        } catch (NamingException e) {
            LOG.error("get" + field + "fail");
            e.printStackTrace();
        }
        return value;
    }


    /**
     * 获取用户状态
     *
     * @param attrs
     * @param status
     * @return
     */
    public static Attributes processUserStatus(Attributes attrs, Boolean status) {
        long uacValue = Long.parseLong(getValueByField(attrs, UACCONTROL_ATTR));

        Boolean checkUserEnableStatus = checkUserDisableStatus(uacValue);
        Attributes attributes = new BasicAttributes();
        //        attributes.put(attrs.get(DefaultProperties.LDAP_CN));

        // 状态一致
        if (status == !checkUserEnableStatus) {
            return null;
        } else {
            if (status) {
                // if not enabled yet --> enable removing 0x00002
                if (uacValue % 16 == UF_ACCOUNTDISABLE) {
                    uacValue -= UF_ACCOUNTDISABLE;
                }
            } else {
                // if not disabled yet --> disable adding 0x00002
                if (uacValue % 16 != UF_ACCOUNTDISABLE) {
                    uacValue += UF_ACCOUNTDISABLE;
                }
            }
            // attributes.put(new BasicAttribute(PASSWORD_ATTR, password));
            attributes.put(new BasicAttribute(UACCONTROL_ATTR, String.valueOf(uacValue)));
        }
        return attributes;
    }

    // 检测用户是否禁用 禁用返回true,启用返回false
    public static Boolean checkUserDisableStatus(long userAccountControl) {
        if (userAccountControl >= 16777216) // TRUSTED_TO_AUTH_FOR_DELEGATION - 允许该帐户进行委派
        {
            userAccountControl = userAccountControl - 16777216;
        }
        if (userAccountControl >= 8388608) // PASSWORD_EXPIRED - (Windows 2000/Windows Server 2003) 用户的密码已过期
        {
            userAccountControl = userAccountControl - 8388608;
        }
        if (userAccountControl >= 4194304) // DONT_REQ_PREAUTH
        {
            userAccountControl = userAccountControl - 4194304;
        }
        if (userAccountControl >= 2097152) // USE_DES_KEY_ONLY - (Windows 2000/Windows Server 2003) 将此用户限制为仅使用数据加密标准
        // (DES) 加密类型的密钥
        {
            userAccountControl = userAccountControl - 2097152;
        }
        if (userAccountControl >= 1048576) // NOT_DELEGATED - 设置此标志后，即使将服务帐户设置为信任其进行 Kerberos 委派，也不会将用户的安全上下文委派给该服务
        {
            userAccountControl = userAccountControl - 1048576;
        }
        if (userAccountControl >= 524288) // TRUSTED_FOR_DELEGATION - 设置此标志后，将信任运行服务的服务帐户（用户或计算机帐户）进行 Kerberos
        // 委派。任何此类服务都可模拟请求该服务的客户端。若要允许服务进行 Kerberos 委派，必须在服务帐户的 userAccountControl
        // 属性上设置此标志
        {
            userAccountControl = userAccountControl - 524288;
        }
        if (userAccountControl >= 262144) // SMARTCARD_REQUIRED - 设置此标志后，将强制用户使用智能卡登录
        {
            userAccountControl = userAccountControl - 262144;
        }
        if (userAccountControl >= 131072) // MNS_LOGON_ACCOUNT - 这是 MNS 登录帐户
        {
            userAccountControl = userAccountControl - 131072;
        }
        if (userAccountControl >= 65536) // DONT_EXPIRE_PASSWORD-密码永不过期
        {
            userAccountControl = userAccountControl - 65536;
        }
        if (userAccountControl >= 2097152) // MNS_LOGON_ACCOUNT - 这是 MNS 登录帐户
        {
            userAccountControl = userAccountControl - 2097152;
        }
        if (userAccountControl >= 8192) // SERVER_TRUST_ACCOUNT - 这是属于该域的域控制器的计算机帐户
        {
            userAccountControl = userAccountControl - 8192;
        }
        if (userAccountControl >= 4096) // WORKSTATION_TRUST_ACCOUNT - 这是运行 Microsoft Windows NT 4.0
        // Workstation、Microsoft Windows NT 4.0 Server、Microsoft Windows 2000
        // Professional 或 Windows 2000 Server 并且属于该域的计算机的计算机帐户
        {
            userAccountControl = userAccountControl - 4096;
        }
        if (userAccountControl >= 2048) // INTERDOMAIN_TRUST_ACCOUNT - 对于信任其他域的系统域，此属性允许信任该系统域的帐户
        {
            userAccountControl = userAccountControl - 2048;
        }
        if (userAccountControl >= 512) // NORMAL_ACCOUNT - 这是表示典型用户的默认帐户类型
        {
            userAccountControl = userAccountControl - 512;
        }

        if (userAccountControl >= 256) // TEMP_DUPLICATE_ACCOUNT -
        // 此帐户属于其主帐户位于另一个域中的用户。此帐户为用户提供访问该域的权限，但不提供访问信任该域的任何域的权限。有时将这种帐户称为“本地用户帐户”
        {
            userAccountControl = userAccountControl - 256;
        }
        if (userAccountControl >= 128) // ENCRYPTED_TEXT_PASSWORD_ALLOWED - 用户可以发送加密的密码
        {
            userAccountControl = userAccountControl - 128;
        }
        if (userAccountControl >= 64) // PASSWD_CANT_CHANGE - 用户不能更改密码。可以读取此标志，但不能直接设置它
        {
            userAccountControl = userAccountControl - 64;
        }
        if (userAccountControl >= 32) // PASSWD_NOTREQD - 不需要密码
        {
            userAccountControl = userAccountControl - 32;
        }
        if (userAccountControl >= 16) // LOCKOUT
        {
            userAccountControl = userAccountControl - 16;
        }
        if (userAccountControl >= 8) // HOMEDIR_REQUIRED - 需要主文件夹
        {
            userAccountControl = userAccountControl - 8;
        }

        if (userAccountControl >= 2) {
            return true;
        }
        return false;

    }

    public static byte[] encodePassword(String password) {
        String newQuotedPassword = "\"" + password + "\"";
        byte[] newUnicodePassword = null;
        try {
            newUnicodePassword = newQuotedPassword.getBytes("UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            LOG.error("encodePassword " + password + " fail");
            e.printStackTrace();
        }
        return newUnicodePassword;
    }

    public static String decodePassword(byte[] encodePassword) {
        String password = "";
        try {
            password = new String(encodePassword, "UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            LOG.error("decodePassword " + encodePassword + " fail");
            e.printStackTrace();
        }
        return password;
    }

}
