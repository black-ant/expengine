package com.gang.etl.engine.api.exception;

/**
 * @Classname SyncErrorEnum
 * @Description TODO
 * @Date 2019/12/29 21:55
 * @Created by zengzg
 */
public enum SyncErrorEnum {

    SYNC_SERVICE_ERROR("E_SYNC_00001", "Service Error", ""),
    SYNC_SYNCTYPE_ERROR("E_SYNC_00002", "No Sync Type", ""),
    SYNC_SYNCDATE_ERROR("E_SYNC_00003", "No Sync Data", ""),
    SYNC_SYNCSERVICE_ERROR("E_SYNC_00004", "No Sync Service", ""),
    SYNC_FIELD_NULL("E_SYNC_00005", "Null Field", ""),
    SYNC_SETTING_NOT_FOUND("E_SYNC_00006", "Null Setting", ""),
    SYNC_SETTING_BUILD_ERROR("E_SYNC_00007", "Setting Exchange Error", ""),
    SYNC_APP_AUTH_ERROR("E_SYNC_00008", "App Auth error", ""),

    NO_CLASS("E_SYNC_00010", "No Class info", ""),
    NO_METHOD("E_SYNC_00030", "No Method", "");


    private String errorCode;
    private String errorInfo;
    private String solution;

    SyncErrorEnum(String errorCode, String errorInfo, String solution) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
        this.solution = solution;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
