package com.gang.etl.engine.api.dto;

import java.util.Date;

/**
 * @Classname SyncLogTO
 * @Description TODO
 * @Date 2021/3/14 20:44
 * @Created by zengzg
 */
public class SyncLogTO {

    private String id;

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
     *
     */
    private String logData;

    /**
     * 同步返回
     */
    private String logResponse;

    /**
     * 同步状态 : 0 : 失败 1 : 拉取成功 2：推送成功
     */
    private String logStatus;

    /**
     * 创建时间
     */
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogDomain() {
        return logDomain;
    }

    public void setLogDomain(String logDomain) {
        this.logDomain = logDomain;
    }

    public String getLogChannel() {
        return logChannel;
    }

    public void setLogChannel(String logChannel) {
        this.logChannel = logChannel;
    }

    public String getLogBody() {
        return logBody;
    }

    public void setLogBody(String logBody) {
        this.logBody = logBody;
    }

    public String getLogRequest() {
        return logRequest;
    }

    public void setLogRequest(String logRequest) {
        this.logRequest = logRequest;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    public String getLogResponse() {
        return logResponse;
    }

    public void setLogResponse(String logResponse) {
        this.logResponse = logResponse;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
