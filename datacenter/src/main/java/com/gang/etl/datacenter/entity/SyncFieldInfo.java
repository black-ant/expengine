package com.gang.etl.datacenter.entity;

import java.io.Serializable;

public class SyncFieldInfo implements Serializable {
    private Integer id;

    private String filedOrigin;

    private String fieldSource;

    private String fieldType;

    private String createUser;

    private String version;

    private String appType;

    private String dataType;

    private String status;

    private String domainId;

    private String policyType;

    private String syncType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFiledOrigin() {
        return filedOrigin;
    }

    public void setFiledOrigin(String filedOrigin) {
        this.filedOrigin = filedOrigin == null ? null : filedOrigin.trim();
    }

    public String getFieldSource() {
        return fieldSource;
    }

    public void setFieldSource(String fieldSource) {
        this.fieldSource = fieldSource == null ? null : fieldSource.trim();
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId == null ? null : domainId.trim();
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType == null ? null : policyType.trim();
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType == null ? null : syncType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", filedOrigin=").append(filedOrigin);
        sb.append(", fieldSource=").append(fieldSource);
        sb.append(", fieldType=").append(fieldType);
        sb.append(", createUser=").append(createUser);
        sb.append(", version=").append(version);
        sb.append(", appType=").append(appType);
        sb.append(", dataType=").append(dataType);
        sb.append(", status=").append(status);
        sb.append(", domainId=").append(domainId);
        sb.append(", policyType=").append(policyType);
        sb.append(", syncType=").append(syncType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}