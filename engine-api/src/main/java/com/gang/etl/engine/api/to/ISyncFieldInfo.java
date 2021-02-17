package com.gang.etl.engine.api.to;

/**
 * @Classname Ivoid
 * @Description TODO
 * @Date 2021/2/17 21:36
 * @Created by zengzg
 */
public interface ISyncFieldInfo {
    String getId();

    String getFieldBody();

    String getFieldOriginFormat();

    String getFieldSourceFormat();

    String getOriginCode();

    String getTargetCode();

    String getProduceQuery();

    String getCreateUser();

    String getUpdateUser();

    String getUpdateDate();

    String getVersion();

    String getAppType();

    String getTypePart();

    String getStatus();

    String getPolicyType();

    String getSyncTypeCode();

    String getOperationType();

    void setId(String id);

    void setFieldBody(String fieldBody);

    void setFieldOriginFormat(String fieldOriginFormat);

    void setFieldSourceFormat(String fieldSourceFormat);

    void setOriginCode(String originCode);

    void setTargetCode(String targetCode);

    void setProduceQuery(String produceQuery);

    void setCreateUser(String createUser);

    void setUpdateUser(String updateUser);

    void setUpdateDate(String updateDate);

    void setVersion(String version);

    void setAppType(String appType);

    void setTypePart(String typePart);

    void setStatus(String status);

    void setPolicyType(String policyType);

    void setSyncTypeCode(String syncTypeCode);

    void setOperationType(String operationType);

    String toString();

    boolean equals(Object o);

    int hashCode();
}
