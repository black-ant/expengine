package com.gang.etl.engine.api.to;

/**
 * @Classname ISyncBusiness
 * @Description TODO
 * @Date 2021/2/17 21:35
 * @Created by zengzg
 */
public interface ISyncBusiness {
    String getId();

    String getBusinessName();

    String getBusinessCode();

    String getBusinessDesc();

    String getBusinessType();

    String getBusinessTypeSort();

    String getBusinessTypeInfo();

    String getSyncProduce();

    String getSyncProduceSetting();

    String getSyncConsumer();

    String getSyncConsumerSetting();

    String getSyncSchedule();

    String getSyncInfo();

    String getSyncField();

    String getSyncStrategy();

    java.util.Date getCreateDate();

    String getCreateUser();

    String getBusineStatus();

    void setId(String id);

    void setBusinessName(String businessName);

    void setBusinessCode(String businessCode);

    void setBusinessDesc(String businessDesc);

    void setBusinessType(String businessType);

    void setBusinessTypeSort(String businessTypeSort);

    void setBusinessTypeInfo(String businessTypeInfo);

    void setSyncProduce(String syncProduce);

    void setSyncProduceSetting(String syncProduceSetting);

    void setSyncConsumer(String syncConsumer);

    void setSyncConsumerSetting(String syncConsumerSetting);

    void setSyncSchedule(String syncSchedule);

    void setSyncInfo(String syncInfo);

    void setSyncField(String syncField);

    void setSyncStrategy(String syncStrategy);

    void setCreateDate(java.util.Date createDate);

    void setCreateUser(String createUser);

    void setBusineStatus(String busineStatus);

    String toString();

    boolean equals(Object o);

    int hashCode();
}
