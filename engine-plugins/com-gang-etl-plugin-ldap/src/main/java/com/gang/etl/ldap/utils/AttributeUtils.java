//CHECKSTYLE:OFF
package com.gang.etl.ldap.utils;

import com.para.commons.core.error.CommonErrorEnum;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import para.sdk.common.sync.SyncBaseBean;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.to.ADGroupTO;
import para.sdk.sync.ldap.to.ADOrgTO;
import para.sdk.sync.ldap.to.ADUserTO;
import para.sdk.sync.ldap.type.*;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Classname AttributeUtils
 * @Description TODO
 * @Date 2020/2/25 16:35
 * @Created by zengzg
 */
public class AttributeUtils {

    private static Logger LOG = LoggerFactory.getLogger(AttributeUtils.class);

    public static Attributes buildAttrs() {
        return new BasicAttributes();
    }

    public static BasicAttribute buildAttrItem(String key, String value) {
        return new BasicAttribute(key, value);
    }

    public static BasicAttribute buildAttrItemObject(String key, Object value) {
        return new BasicAttribute(key, value);
    }

    public static BasicAttribute buildAttrItemArray(String key, String[] value) {
        return new BasicAttribute(key, value);
    }

    /**
     * @param attrs
     * @param key
     * @return
     */
    public static String getAttributorValue(Attributes attrs, String key) {

        String attrValue = null;
        if (attrs != null && attrs.size() > 0) {
            attrValue = getAttrValueStr(attrs.get(key));
        }
        return attrValue;
    }

    /**
     * 获取属性值
     *
     * @param attr
     * @return
     */
    public static String getAttrValueStr(Attribute attr) {
        return getAttrValue(attr, new String());
    }

    /**
     * search 出的类型为LdapAttrbuter ,该属性数组获取为字符串 , 针对 LdapGroups
     *
     * @param attr
     * @return
     */
    @Deprecated
    public static String[] getLdapAttrValueArray(Attribute attr) {
        String backStr = getAttrValue(attr, new String());
        String[] backArray = {};
        // TODO : Member Of 为 String 此处进行解析
        if (DefaultProperties.LDAP_PARENT_GROUP_NAME.equals(attr.getID()) && backStr != null) {
            String groupStr = String.valueOf(backStr);
            Set<String> set = new HashSet<>();
            if (groupStr.indexOf(",CN") < 0) {
                set.add(groupStr);
            } else {
                do {
                    if (groupStr.indexOf(",CN") < 0) {
                        break;
                    }
                    set.add(groupStr.substring(0, groupStr.indexOf(",CN")));
                    groupStr = groupStr.substring(groupStr.indexOf(",CN") + 1);
                } while (Boolean.TRUE);
            }
            backArray = set.toArray(backArray);
        }
        return backArray;
    }

    public static String[] getAttrValueArray(Attribute attr) {
        List<String> back = getAttrValue(attr, new ArrayList<>());
        return back.toArray(new String[]{});
    }

    public static <T> T getAttrValue(Attribute attr, T type) {
        try {
            if (null != attr) {
                if (type instanceof ArrayList) {
                    ArrayList<String> groups = new ArrayList();
                    for (int i = 0; i <= attr.size() - 1; i++) {
                        groups.add(String.valueOf(attr.get(i)));
                    }
                    type = (T) groups;
                } else {
                    type = (T) attr.get(0);
                }
            }

        } catch (ClassCastException e) {
            LOG.error("E----> ClassCastException error :{} -- content :{}", e.getClass(), e.getMessage());
        } catch (NamingException e) {
            LOG.error("E----> NamingException error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        return type;
    }

    /**
     * Attributes 转换为 List
     *
     * @param attrs
     * @return
     * @throws NamingException
     */
    public static List<Attribute> attrsToList(Attributes attrs) throws NamingException {
        final NamingEnumeration ne = attrs.getAll();
        List<Attribute> list = new ArrayList<>();
        while (ne.hasMoreElements()) {
            Attribute sr = (Attribute) ne.next();
            list.add(sr);
        }
        return list;
    }

    /**
     * 将 Attributes 转换为 Map
     *
     * @param attrs
     * @return
     * @throws NamingException
     */
    public static Map<String, Object> attrsToMap(Attributes attrs) throws NamingException {
        final NamingEnumeration ne = attrs.getAll();
        Map<String, Object> map = new HashMap<>();
        while (ne.hasMoreElements()) {
            Attribute sr = (Attribute) ne.next();
            map.put(sr.getID(), sr.get());
        }
        return map;
    }

    public static Map<String, Object> attrsToMapNew(Attributes attrs) throws NamingException {
        final NamingEnumeration ne = attrs.getAll();
        Map<String, Object> map = new HashMap<>();
        Object object = null;
        Object fieValue = null;
        while (ne.hasMoreElements()) {
            Attribute sr = (Attribute) ne.next();
            if (DefaultProperties.LDAP_MEMBER.equals(sr.getID()) || "objectClass".equals(sr.getID())) {
                object = sr.getAll();
                fieValue = getList(object);

            } else if (DefaultProperties.OBJECTGUID.equals(sr.getID())) {
                fieValue = GUIDUtils.getGuidAsString((byte[]) sr.get());
            } else if (DefaultProperties.LDAP_USER_PASSWORD.equals(sr.getID())) {
                byte[] bytes = (byte[]) sr.get();
                fieValue = ADUtils.decodePassword(bytes).replace("\"", "");
            } else {
                fieValue = (Object) sr.get();
            }

            map.put(sr.getID(), fieValue);
        }
        return map;
    }

    public static List<String> getList(Object object) throws NamingException {
        List<String> list = new ArrayList<>();
        NamingEnumeration results = (NamingEnumeration) object;
        while (results.hasMoreElements()) {
            Object next = results.next();
            list.add(String.valueOf(next));
        }
        return list;
    }

    /**
     * Bean 转
     *
     * @param baseBean
     * @return
     */
    public static Attributes getAttrsInfoByBean(SyncBaseBean baseBean) {

        List<Field> fields = FieldUtils.getAllFieldsList(baseBean.getClass());
        Attributes attributes = new BasicAttributes();
        fields.stream().forEach(field -> {
            try {
                field.setAccessible(Boolean.TRUE);
                if (field.getAnnotation(ADAnnotation.class) != null && null != field.get(baseBean)) {
                    if ("ldapGroups".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        String[] memberOf = (String[]) field.get(baseBean);
                        BasicAttribute attts = new BasicAttribute(DefaultProperties.LDAP_GROUPS_NAME);
                        for (String item : memberOf) {
                            attts.add(item);
                        }
                        attributes.put(attts);
                    } else if (DefaultProperties.LDAP_MEMBER.equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        String[] memberOf = (String[]) field.get(baseBean);
                        BasicAttribute attts = new BasicAttribute(DefaultProperties.LDAP_MEMBER);
                        for (String item : memberOf) {
                            attts.add(item);
                        }
                        attributes.put(attts);
                    } else if ("groupType".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        GroupTypeEnum groupTypeEnum = (GroupTypeEnum) field.get(baseBean);
                        BasicAttribute attts = new BasicAttribute(DefaultProperties.LDAP_GROUP_TYPE,
                                groupTypeEnum.getValue());
                        attributes.put(attts);
                    } else if ("userPassword".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        String userPassword = (String) field.get(baseBean);
                        //                        BasicAttribute attts = new BasicAttribute(DefaultProperties
                        //                        .LDAP_USER_PASSWORD,
                        //                                ADUtils.encodePassword(userPassword));
                        BasicAttribute attts = new BasicAttribute(DefaultProperties.LDAP_USER_PASSWORD,
                                userPassword);
                        attributes.put(attts);
                    } else if ("userAccountControl".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        PasswordStrategyEnum passwordStrategyEnum = (PasswordStrategyEnum) field.get(baseBean);
                        BasicAttribute attts = new BasicAttribute(DefaultProperties.UACCONTROL_ATTR,
                                passwordStrategyEnum.getValue());
                        attributes.put(attts);
                    } else if ("countryCode".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        CountryCode countryCode = (CountryCode) field.get(baseBean);
                        if (null != countryCode) {
                            BasicAttribute c = new BasicAttribute(DefaultProperties.COUNTRY,
                                    countryCode.getCountrySign());
                            attributes.put(c);
                            BasicAttribute co = new BasicAttribute(DefaultProperties.COUNTRY_NAME,
                                    countryCode.getCountryName());
                            attributes.put(co);
                            BasicAttribute code = new BasicAttribute(DefaultProperties
                                    .COUNTRY_CODE,
                                    countryCode.getCode());
                            attributes.put(code);
                        }

                    } else if ("objectClass".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        List<String> objectClass = (List<String>) field.get(baseBean);
                        BasicAttribute basicAttribute = new BasicAttribute("objectClass");
                        objectClass.forEach(a -> {
                            basicAttribute.add(a);
                        });
                        attributes.put(basicAttribute);
                    } else if ("extendedAttributes".equals(field.getAnnotation(ADAnnotation.class).alias())) {
                        //动态属性创建，更新
                        Map<String, Object> map = (Map<String, Object>) field.get(baseBean);
                        map.forEach((a, b) -> {
                            if ("objectClass".equals(a) && b instanceof List) {
                                List<String> objectClass = (List<String>) b;
                                BasicAttribute basicAttribute = new BasicAttribute("objectClass");
                                objectClass.forEach(c -> {
                                    basicAttribute.add(c);
                                });
                                attributes.put(basicAttribute);
                            } else if ("ldapGroups".equals(a) && b instanceof String[]) {
                                String[] memberOf = (String[]) b;
                                BasicAttribute attts = new BasicAttribute(DefaultProperties.LDAP_GROUPS_NAME);
                                for (String item : memberOf) {
                                    attts.add(item);
                                }
                                attributes.put(attts);
                            } else {
                                attributes.put(buildAttrItemObject(a, b));
                            }

                        });


                    } else {
                        if (field.getAnnotation(ADAnnotation.class).length() > 0) {
                            Integer length = field.getAnnotation(ADAnnotation.class).length();
                            if (String.valueOf(field.get(baseBean)).length() > length) {
                                throw new SyncException(CommonErrorEnum.PARAMETER_ERROR,
                                        "Field -(" + field.getName() + ") Length need more than : " + length);
                            }
                        }
                        attributes.put(buildAttrItemObject(field.getAnnotation(ADAnnotation.class).alias(),
                                field.get(baseBean)));
                    }

                } else {
                    LOG.info("------> this field :{} is not need edit <-------", field.getName());
                }
            } catch (IllegalAccessException e) {
                LOG.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
            }
        });
        return attributes;

    }


    /*
     * List<TO>对象转List<Attributes>
     */
    public static List<SyncBaseBean> convertAttributesList(List<Attributes> attrlist, SyncBaseBean to) {
        List<SyncBaseBean> list = new ArrayList<>();
        for (Attributes attrs : attrlist) {
            SyncBaseBean syncBaseBean = new SyncBaseBean();
            Map<String, Object> attrsToMap;
            //                attrsToMap = attrsToMap(attrs);
            convertMapToAttributes(attrs, syncBaseBean);
            list.add(syncBaseBean);
        }
        return list;
    }

    /*
     * Attributes转TO
     */
    public static SyncBaseBean convertMapToAttributes(Attributes attrs, SyncBaseBean to) {
        //        BasicAttributes adAttrs = new BasicAttributes(true);

        Map<String, Object> map = new HashMap<>();

        try {
            Field[] fieldArray = to.getClass().getDeclaredFields();
            for (Field field : fieldArray) {

                String annoValue = getAnnoValue(field);
                Attribute attr = attrs.get(annoValue);
                attrs.remove(annoValue);

                if (attr != null) {
                    Object fieValue = null;
                    if (!DefaultProperties.LDAP_MEMBER.equals(annoValue) && !"objectClass".equals(annoValue)) {
                        fieValue = attr.get();
                    } else {
                        fieValue = attr.getAll();
                    }

                    if (DefaultProperties.OBJECTGUID.equals(annoValue)) {
                        fieValue = GUIDUtils.getGuidAsString((byte[]) attr.get());
                    } else if (DefaultProperties.LDAP_USER_PASSWORD.equals(annoValue)) {
                        byte[] bytes = (byte[]) fieValue;
                        fieValue = ADUtils.decodePassword(bytes).replace("\"", "");
                    } else if (DefaultProperties.LDAP_GROUP_TYPE.equals(annoValue)) {  // 组类型
                        String groupType = (String) fieValue;
                        fieValue = GroupTypeEnum.getGroupTypeEnumByValue(groupType);
                    } else if (DefaultProperties.UACCONTROL_ATTR.equals(annoValue)) {    // 账户类型
                        String uacAttr = (String) fieValue;
                        fieValue = PasswordStrategyEnum.getPasswordStrategyEnumByValue(uacAttr);
                    } else if (DefaultProperties.COUNTRY_CODE.equals(annoValue)) {     // 国家类型
                        String uacAttr = (String) fieValue;
                        fieValue = CountryCode.getCountryTypeEnumByValue(uacAttr);
                    } else if (DefaultProperties.LDAP_MEMBER.equals(annoValue)) { // member处理
                        List<String> list = new ArrayList<>();
                        NamingEnumeration results = (NamingEnumeration) fieValue;
                        while (results.hasMoreElements()) {
                            Object next = results.next();
                            list.add(String.valueOf(next));
                        }
                        fieValue = list.toArray(new String[list.size()]);
                    } else if ("objectClass".equals(annoValue)) { // objectClass 集合处理
                        List<String> list = new ArrayList<>();
                        NamingEnumeration results = (NamingEnumeration) fieValue;
                        while (results.hasMoreElements()) {
                            Object next = results.next();
                            list.add(String.valueOf(next));
                        }
                        fieValue = list;
                    }
                    field.setAccessible(true);
                    field.set(to, fieValue);
                }
            }
        } catch (NamingException | IllegalAccessException e) {
            LOG.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
        }
        try {
            map = attrsToMapNew(attrs);  //动态属性
            convsion(to.getClass().getDeclaredFields(), map, to);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return to;
    }

    /**
     * 动态属性封装
     *
     * @param fieldArray
     * @param map
     * @param to
     */
    public static void convsion(Field[] fieldArray, Map<String, Object> map, SyncBaseBean to) {
        for (Field field : fieldArray) {
            String annoValue = getAnnoValue(field);
            if ("extendedAttributes".equals(annoValue)) {
                field.setAccessible(true);
                try {
                    field.set(to, map);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
     * TO对象字段注解ldap属性获取
     */
    public static String getAnnoValue(Field field) {

        ADAnnotation[] adAnnotation = field.getAnnotationsByType(ADAnnotation.class);

        String annValue = "";
        for (ADAnnotation itemField : adAnnotation) {
            annValue = itemField.alias();
        }
        return annValue;

    }

    /*
     根据TO对象属性名成集合，反射获取TO对象属性上得LDAP主键属性名。
     */
    public static Attributes getLDAPAttrByField(ObjectClass objectClass, List<String> removeAttr) {
        List<Field> fields = null;
        if (objectClass.is(ObjectClass.ACCOUNT_NAME)) {
            fields = FieldUtils.getAllFieldsList(ADUserTO.class);
        } else if (objectClass.is(ObjectClass.GROUP_NAME)) {
            fields = FieldUtils.getAllFieldsList(ADGroupTO.class);
        } else {
            fields = FieldUtils.getAllFieldsList(ADOrgTO.class);
        }
        Attributes attrs = new BasicAttributes();
        Attributes attributes = new BasicAttributes();
        fields.stream().forEach(field -> {
            try {
                for (String attrName : removeAttr) {
                    if (attrName.equalsIgnoreCase(field.getName()) && field.getAnnotation(ADAnnotation.class) != null) {
                        String annoValue = getAnnoValue(field);
                        attrs.put(new BasicAttribute(annoValue));
                        break;
                    }
                }
            } catch (Exception e) {
                LOG.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
            }
        });
        return attrs;
    }

    public static ObjectClass getObjectClass(Attributes attrs) {
        ObjectClass objectClass = null;
        StringBuffer sb = new StringBuffer();
        try {
            Attribute objectClassAttr = attrs.get("objectClass");
            NamingEnumeration<?> all = objectClassAttr.getAll();
            while (all.hasMoreElements()) {
                sb.append(all.next()).append(",");
            }
        } catch (NamingException e) {
            LOG.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        String objectClassAttr = sb.toString();
        if (objectClassAttr.contains("user")) {
            objectClass = ObjectClass.ACCOUNT;
        } else if (objectClassAttr.contains("organizationalUnit")) {
            objectClass = ObjectClass.ORGANIZATION;
        } else if (objectClassAttr.contains("group")) {
            objectClass = ObjectClass.GROUP;
        }
        return objectClass;
    }
}
