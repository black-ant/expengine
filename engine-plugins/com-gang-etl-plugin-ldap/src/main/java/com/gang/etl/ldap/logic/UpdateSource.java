//CHECKSTYLE:OFF
package com.gang.etl.ldap.logic;

import com.alibaba.fastjson.JSONObject;
import com.para.commons.core.error.CommonErrorEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.error.ErrorLogic;
import para.sdk.sync.ldap.source.common.IBaseSource;
import para.sdk.sync.ldap.to.LDAPUpdateTO;
import para.sdk.sync.ldap.type.DefaultProperties;
import para.sdk.sync.ldap.type.ObjectClass;
import para.sdk.sync.ldap.utils.ADUtils;
import para.sdk.sync.ldap.utils.AttributeUtils;
import para.sdk.sync.ldap.utils.SearchUtils;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname UpdateSource
 * @Description TODO
 * @Date 2020/2/21 13:30
 * @Created by zengzg
 */
@Component
public class UpdateSource extends CommonSourceOperation implements IBaseSource<String, LDAPUpdateTO> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 执行 Update 逻辑
     *
     * @param connect      LDAPConnect  容器连接
     * @param objectClass  操作类型
     * @param ldapUpdateTO 操作TO
     * @return
     */
    @Override
    public String executeImpl(LDAPConnect connect, ObjectClass objectClass,
                              LDAPUpdateTO ldapUpdateTO) {

        LdapContext context = connect.getCtx();
        String ouAddressNew = "";
        try {

            // step1 : 获取原对象
            Attributes attrs = SearchUtils.doSarchByObjectKey(connect, ldapUpdateTO.getObjectKey(),
                    objectClass);
            String addressOld = AttributeUtils.getAttributorValue(attrs, DefaultProperties.DNPARAMNAME);

            if (StringUtils.isBlank(addressOld)) {
                throw new SyncException(CommonErrorEnum.UPDATE_ERR_EXT, "The object has been deleted and the object " +
                        "was not found");
            }
            ouAddressNew = ADUtils.buildNewAddress(context, connect.getConfig(), addressOld,
                    ldapUpdateTO.getReplaceAttrs(),
                    objectClass);

            logger.info("------> Create Address is :{} <-------", ouAddressNew);

            // 特殊字符转换
            ouAddressNew = ADUtils.exchangeIllegalEntryDN(ouAddressNew);
            addressOld = ADUtils.exchangeIllegalEntryDN(addressOld);
            if (!addressOld.startsWith(ouAddressNew.substring(0, 2))) {
                throw new SyncException(CommonErrorEnum.SDK_SYNC_RUN, "ObjectGUID does not match, converted to wrong " +
                        "ObjectClass");
            } else {
                if (!addressOld.equals(ouAddressNew)) {
                    context.rename(addressOld, ouAddressNew);
                }
            }


            logger.info("------> update address OU :{} <-------", ouAddressNew);

            // replace == add ?
            Map<String, Attribute> replaceMap = new HashMap<>();
            Map<String, Attribute> removeMap = new HashMap<>();

            // 分别获取待修改和待删除的特殊属性
            checkDeleteAttr(attrs, ldapUpdateTO.getRemoveAttrs());
            checkAttrs(connect, ldapUpdateTO.getReplaceAttrs(), replaceMap, objectClass);
            checkAttrs(connect, ldapUpdateTO.getRemoveAttrs(), removeMap, objectClass);

            // 准备属性
            final ModificationItem[] mods = getAttriutes(ldapUpdateTO, objectClass);

            try {
                // step 3 : 修改对象
                context.modifyAttributes(ouAddressNew, mods);
            } catch (Exception e) {
                logger.error("E----> update error :{} , content :{}", e.getClass(), e.getMessage());
                if (!addressOld.equals(ouAddressNew)) {
                    logger.info("------> error back transaction : addressNew to addressOld <-------");
                    context.rename(ouAddressNew, addressOld);
                }
                throw e;
            }


            // step 4 : 修改组操作
            modifyGroup(connect, ouAddressNew, replaceMap.get(DefaultProperties.LDAP_GROUPS_NAME),
                    removeMap.get(DefaultProperties.LDAP_GROUPS_NAME));

        } catch (NamingException e) {
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
            ErrorLogic.buidSyncException(e);
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("E----> error :{} -- content :{}", e1.getClass(), e1.getMessage());
            throw e1;
        }

        logger.info("------> this in ok :{} <-------", JSONObject.toJSONString(context));

        // 生成返回 GUID
        return ADUtils.isDN(ldapUpdateTO.getObjectKey())
                ? ldapUpdateTO.getObjectKey()
                : ADUtils.backGUIDStr(connect, ouAddressNew);

    }


    /**
     * 对组进行修改
     *
     * @param connect      容器连接
     * @param ouAddressNew OU 地址
     * @param ldapGroups   操作组
     */
    public void modifyGroup(LDAPConnect connect, String ouAddressNew, Attribute ldapGroups, Attribute deleteGroup) {

        // Step1 : 拿到用户所属组
        Attribute attr = SearchUtils.getAttrByEntryDBN(connect.getCtx(), ouAddressNew,
                DefaultProperties.LDAP_PARENT_GROUP_NAME);

        // 获取新 Group List
        String[] newGroupArray = AttributeUtils.getAttrValueArray(ldapGroups);

        if (newGroupArray != null) {
            // 全 EntryDN
            List<String> groupList = attr == null ? new ArrayList<>() :
                    new ArrayList(Arrays.asList(AttributeUtils.getAttrValueArray(attr)));

            // 可能为 EntryDN or Guid
            List<String> newGroupList = Arrays.asList(newGroupArray);
            // step 2 : 迭代处理添加
            newGroupList.forEach(item -> {
                String itemGroupDN = ADUtils.checkTypeReturnEntryDN(connect.getCtx(), item);
                if (groupList.contains(itemGroupDN)) {
                    groupList.remove(itemGroupDN);
                } else {
                    logger.info("------> 加组操作 :{} -- {} <-------", itemGroupDN, ouAddressNew);
                    ADUtils.addGroup(connect.getCtx(), itemGroupDN, ouAddressNew);
                }
            });
        }

        // step 3 : 删除组
        if (deleteGroup != null && deleteGroup.size() > 0) {
            String[] deleteGroupArray = new String[0];
            try {
                if (deleteGroup.get() instanceof String) {
                    deleteGroupArray = AttributeUtils.getAttrValueArray(deleteGroup);
                } else {
                    deleteGroupArray = AttributeUtils.getAttrValueArray((Attribute) deleteGroup.get());
                }
            } catch (NamingException e) {
            }
            for (String item : deleteGroupArray) {
                String removeGroupDN = SearchUtils.doSearchEntryDNByObjectKey(connect, item, ObjectClass.GROUP);
                ADUtils.removeGroup(connect.getCtx(), removeGroupDN, ouAddressNew);
            }
        }


    }

    /**
     * 对删除的属性进行处理
     *
     * @param attrs
     * @param removeAttrs
     */
    public void checkDeleteAttr(Attributes attrs, Attributes removeAttrs) {
        try {
            if (removeAttrs != null) {

                // 获取所有属性 , 不存在的属性会移除
                NamingEnumeration attrIterator = removeAttrs.getAll();
                while (attrIterator.hasMoreElements()) {
                    BasicAttribute attribute = (BasicAttribute) attrIterator.next();
                    if (attrs.get(attribute.getID()) == null && !DefaultProperties.LDAP_GROUPS_NAME.equals(attribute.getID())) {
                        removeAttrs.remove(attribute.getID());
                    }
                }
            }
        } catch (NamingException e) {
            logger.error("E----> checkDeleteAttr error :{} -- content :{}", e.getClass(), e.getMessage());
        }

    }

}
