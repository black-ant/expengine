package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gang.common.lib.to.AbstractEntity;
import com.gang.etl.engine.api.to.ISyncFieldInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 *
 * </p>
 *
 * @author ant-black
 * @since 2020-01-31
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SyncFieldInfo extends AbstractEntity implements ISyncFieldInfo {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 资源字段
     */
    private String fieldBody;

    /**
     * 来源模板
     */
    private String fieldOriginFormat;

    /**
     * 去处模板
     */
    private String fieldSourceFormat;


    /**
     * 来源 Code
     */
    private String originCode;

    /**
     * 目标Code
     */
    private String targetCode;

    /**
     *
     */
    private String produceQuery;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 版本
     */
    private String version;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 数据;类型
     */
    private String typePart;

    /**
     * 状态
     */
    private String status;

    /**
     * 同步策略
     */
    private String policyType;

    /**
     * 同步类型
     */
    private String syncTypeCode;

    /**
     * 处理方式
     */
    private String operationType;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getFieldBody() {
        return fieldBody;
    }

    @Override
    public void setFieldBody(String fieldBody) {
        this.fieldBody = fieldBody;
    }

    @Override
    public String getFieldOriginFormat() {
        return fieldOriginFormat;
    }

    @Override
    public void setFieldOriginFormat(String fieldOriginFormat) {
        this.fieldOriginFormat = fieldOriginFormat;
    }

    @Override
    public String getFieldSourceFormat() {
        return fieldSourceFormat;
    }

    @Override
    public void setFieldSourceFormat(String fieldSourceFormat) {
        this.fieldSourceFormat = fieldSourceFormat;
    }

    @Override
    public String getOriginCode() {
        return originCode;
    }

    @Override
    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    @Override
    public String getTargetCode() {
        return targetCode;
    }

    @Override
    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    @Override
    public String getProduceQuery() {
        return produceQuery;
    }

    @Override
    public void setProduceQuery(String produceQuery) {
        this.produceQuery = produceQuery;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getAppType() {
        return appType;
    }

    @Override
    public void setAppType(String appType) {
        this.appType = appType;
    }

    @Override
    public String getTypePart() {
        return typePart;
    }

    @Override
    public void setTypePart(String typePart) {
        this.typePart = typePart;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getPolicyType() {
        return policyType;
    }

    @Override
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    @Override
    public String getSyncTypeCode() {
        return syncTypeCode;
    }

    @Override
    public void setSyncTypeCode(String syncTypeCode) {
        this.syncTypeCode = syncTypeCode;
    }

    @Override
    public String getOperationType() {
        return operationType;
    }

    @Override
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
