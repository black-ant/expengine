package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gang.common.lib.to.AbstractEntity;

import java.util.Date;

import com.gang.etl.engine.api.annotation.SyncField;
import com.gang.etl.engine.api.to.ISyncBusiness;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author ant-black
 * @since 2020-06-27
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_business")
public class SyncBusiness extends AbstractEntity implements ISyncBusiness {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 业务名称
     */
    @SyncField
    private String businessName;

    /**
     * 业务唯一编码
     */
    private String businessCode;

    /**
     * 业务描述
     */
    private String businessDesc;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务类型排序
     */
    private String businessTypeSort;

    /**
     * 业务类型参数
     */
    private String businessTypeInfo;

    /**
     * 生产者
     */
    private String syncProduce;

    /**
     * 生产者配置信息
     */
    private String syncProduceSetting;

    /**
     * 消费者
     */
    private String syncConsumer;

    /**
     * 生产者配置信息
     */
    private String syncConsumerSetting;

    /**
     * 定时任务
     */
    private String syncSchedule;

    /**
     * info信息
     */
    private String syncInfo;

    /**
     * 属性映射关系
     */
    private String syncField;

    /**
     * 同步策略ID
     */
    private String syncStrategy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 业务状态
     */
    private String busineStatus;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getBusinessName() {
        return businessName;
    }

    @Override
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String getBusinessCode() {
        return businessCode;
    }

    @Override
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Override
    public String getBusinessDesc() {
        return businessDesc;
    }

    @Override
    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    @Override
    public String getBusinessType() {
        return businessType;
    }

    @Override
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Override
    public String getBusinessTypeSort() {
        return businessTypeSort;
    }

    @Override
    public void setBusinessTypeSort(String businessTypeSort) {
        this.businessTypeSort = businessTypeSort;
    }

    @Override
    public String getBusinessTypeInfo() {
        return businessTypeInfo;
    }

    @Override
    public void setBusinessTypeInfo(String businessTypeInfo) {
        this.businessTypeInfo = businessTypeInfo;
    }

    @Override
    public String getSyncProduce() {
        return syncProduce;
    }

    @Override
    public void setSyncProduce(String syncProduce) {
        this.syncProduce = syncProduce;
    }

    @Override
    public String getSyncProduceSetting() {
        return syncProduceSetting;
    }

    @Override
    public void setSyncProduceSetting(String syncProduceSetting) {
        this.syncProduceSetting = syncProduceSetting;
    }

    @Override
    public String getSyncConsumer() {
        return syncConsumer;
    }

    @Override
    public void setSyncConsumer(String syncConsumer) {
        this.syncConsumer = syncConsumer;
    }

    @Override
    public String getSyncConsumerSetting() {
        return syncConsumerSetting;
    }

    @Override
    public void setSyncConsumerSetting(String syncConsumerSetting) {
        this.syncConsumerSetting = syncConsumerSetting;
    }

    @Override
    public String getSyncSchedule() {
        return syncSchedule;
    }

    @Override
    public void setSyncSchedule(String syncSchedule) {
        this.syncSchedule = syncSchedule;
    }

    @Override
    public String getSyncInfo() {
        return syncInfo;
    }

    @Override
    public void setSyncInfo(String syncInfo) {
        this.syncInfo = syncInfo;
    }

    @Override
    public String getSyncField() {
        return syncField;
    }

    @Override
    public void setSyncField(String syncField) {
        this.syncField = syncField;
    }

    @Override
    public String getSyncStrategy() {
        return syncStrategy;
    }

    @Override
    public void setSyncStrategy(String syncStrategy) {
        this.syncStrategy = syncStrategy;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    public String getBusineStatus() {
        return busineStatus;
    }

    @Override
    public void setBusineStatus(String busineStatus) {
        this.busineStatus = busineStatus;
    }
}
