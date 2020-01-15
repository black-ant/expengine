package com.gang.etl.datacenter.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * sync_setting
 *
 * @author
 */
public class SyncSetting implements Serializable {
    @NotEmpty
    private String id;

    /**
     * 配置体
     */
    private String settingBody;

    /**
     * 配置名称
     */
    private String settingName;

    /**
     * 配置类型
     */
    private String settingType;

    private String settingTypeCode;

    /**
     * 配置控制
     */
    private String settingControl;

    /**
     * 配置策略
     */
    private String settingPolicy;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSettingBody() {
        return settingBody;
    }

    public void setSettingBody(String settingBody) {
        this.settingBody = settingBody;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public String getSettingTypeCode() {
        return settingTypeCode;
    }

    public void setSettingTypeCode(String settingTypeCode) {
        this.settingTypeCode = settingTypeCode;
    }

    public String getSettingControl() {
        return settingControl;
    }

    public void setSettingControl(String settingControl) {
        this.settingControl = settingControl;
    }

    public String getSettingPolicy() {
        return settingPolicy;
    }

    public void setSettingPolicy(String settingPolicy) {
        this.settingPolicy = settingPolicy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SyncSetting other = (SyncSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSettingBody() == null ? other.getSettingBody() == null :
                this.getSettingBody().equals(other.getSettingBody()))
                && (this.getSettingName() == null ? other.getSettingName() == null :
                this.getSettingName().equals(other.getSettingName()))
                && (this.getSettingType() == null ? other.getSettingType() == null :
                this.getSettingType().equals(other.getSettingType()))
                && (this.getSettingTypeCode() == null ? other.getSettingTypeCode() == null :
                this.getSettingTypeCode().equals(other.getSettingTypeCode()))
                && (this.getSettingControl() == null ? other.getSettingControl() == null :
                this.getSettingControl().equals(other.getSettingControl()))
                && (this.getSettingPolicy() == null ? other.getSettingPolicy() == null :
                this.getSettingPolicy().equals(other.getSettingPolicy()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null :
                this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSettingBody() == null) ? 0 : getSettingBody().hashCode());
        result = prime * result + ((getSettingName() == null) ? 0 : getSettingName().hashCode());
        result = prime * result + ((getSettingType() == null) ? 0 : getSettingType().hashCode());
        result = prime * result + ((getSettingTypeCode() == null) ? 0 : getSettingTypeCode().hashCode());
        result = prime * result + ((getSettingControl() == null) ? 0 : getSettingControl().hashCode());
        result = prime * result + ((getSettingPolicy() == null) ? 0 : getSettingPolicy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", settingBody=").append(settingBody);
        sb.append(", settingName=").append(settingName);
        sb.append(", settingType=").append(settingType);
        sb.append(", settingTypeCode=").append(settingTypeCode);
        sb.append(", settingControl=").append(settingControl);
        sb.append(", settingPolicy=").append(settingPolicy);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}