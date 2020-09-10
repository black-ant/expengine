package com.gang.etl.datacenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gang.common.lib.to.AbstractEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ant-black
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sync_log")
public class SyncLog extends AbstractEntity {

    private static final long serialVersionUID=1L;

    /**
     * 所属域
     */
    private String logDomain;

    /**
     * 所属频道
     */
    private String logChannel;

    /**
     * log 体
     */
    private String logBody;

    /**
     * 同步请求
     */
    private String logRequest;

    /**
     * 同步返回
     */
    private String logResponse;

    /**
     * 同步状态 : 0 : 失败 1 : 拉取成功 2：推送成功
     */
    private String logStatus;

    /**
     * 同步业务
     */
    private String syncBusiness;

    /**
     * 同步策略
     */
    private String syncStrategy;

    /**
     * setting 流转
     */
    private String syncSetting;

    /**
     * 同步信息
     */
    private String syncInfo;

    /**
     * 同步版本
     */
    private String syncVersion;

    /**
     * 下次同步信息
     */
    private String cacheNextInfo;

    /**
     * 当前数据信息
     */
    private String cacheDataHash;

    /**
     * 补充字段
     */
    private String cacheExt;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 拉取 , 推送
     */
    private String syncOperationType;

    /**
     * 增量 , 全量
     */
    private String syncScopeType;


}
