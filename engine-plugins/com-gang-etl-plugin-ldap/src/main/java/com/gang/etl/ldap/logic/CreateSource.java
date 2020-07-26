//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import com.alibaba.fastjson.JSONObject;
import com.para.commons.core.error.CommonErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.error.ErrorLogic;
import para.sdk.sync.ldap.source.common.IBaseSource;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.LDAPVersion;
import para.sdk.sync.ldap.type.ObjectClass;
import para.sdk.sync.ldap.utils.ADUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname CreateSource
 * @Description TODO
 * @Date 2020/2/21 13:29
 * @Created by zengzg
 */
@Component
public class CreateSource extends CommonSourceOperation implements IBaseSource<String, Attributes> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String executeImpl(LDAPConnect connect, ObjectClass objectClass, Attributes attrs) {


        String ouAddress = "";
        try {

            // 校验属性是否合法
            ADUtils.checkAttrInfo(attrs, objectClass);

            // 获取 OU Address
            ouAddress = ADUtils.buildNewAddress(connect.getCtx(), connect.getConfig(), null, attrs, objectClass);
            logger.info("------> Create Address is :{} <-------", ouAddress);

            // step 1 : checkAttrbuter
            Map<String, Attribute> map = new HashMap<>();

            checkAttrs(connect, attrs, map, objectClass);

            // 准备属性
            final BasicAttributes adAttrs = getAttriutes(attrs, objectClass, connect);


            // 特殊字符转换
            ouAddress = ADUtils.exchangeIllegalEntryDN(ouAddress);
            //            ouAddress = "CN=Sync3606\\2CEND,OU=武汉研发,OU=研发部,OU=上海派拉,DC=wdhacpoc,DC=com,DC=cn";
            logger.info("------> createSubcontext :{} <-------", ouAddress);

            // step 3 : 创建对象
            connect.getCtx().createSubcontext(ouAddress, adAttrs);

            // step 4 : 加组操作
            ADUtils.addUserToMemberGroup(ouAddress, map.get(DefaultProperties.LDAP_GROUPS_NAME), connect.getCtx());

            ADUtils.addGroupMember(connect, ouAddress, map.get(DefaultProperties.LDAP_MEMBER));

            // step 5 : 添加额外属性用于后续处理
            attrs.put(DefaultProperties.DNPARAMNAME, ouAddress);

        } catch (NamingException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
            ErrorLogic.buidSyncException(e);
        } catch (Exception e1) {
            logger.error("E----> error :{} -- content :{}", e1.getClass(), e1.getMessage());
            throw e1;
        }

        logger.info("------> this in ok :{} <-------", JSONObject.toJSONString(connect.getCtx()));

        return ADUtils.backGUIDStr(connect, ouAddress);
    }

    /**
     * 完善 BasicAttributes 信息
     * 1 添加 Object Class
     *
     * @return
     */
    public BasicAttributes getAttriutes(Attributes attrs, ObjectClass orgClass, LDAPConnect connect) {
        BasicAttributes adAttrs = (BasicAttributes) attrs;
        Attribute attr = attrs.get("objectClass");
        if (attr == null || attr.size() == 0) {
            if (orgClass.getObjectLdapValue() == null) {
                throw new SyncException(CommonErrorEnum.CREATE_ERR_EXT, "objectClass is null");
            }//如属性中没有配置对应objectClass，则是采用默认的
            // Add ObjectClass Value
            BasicAttribute objectClass = new BasicAttribute("objectClass");

            for (String ldapClass : orgClass.getObjectLdapValue()) {

                if ("user".equals(ldapClass) && connect.getConfig().getLdapVersion().equals(LDAPVersion.OPENLDAP)) {
                    logger.info("------> this Ldap version 不支持此 Object Class <-------");
                } else {
                    objectClass.add(ldapClass);
                }

            }
            // 添加 Object Class 信息
            adAttrs.put(objectClass);
        }


        return adAttrs;
    }

}
