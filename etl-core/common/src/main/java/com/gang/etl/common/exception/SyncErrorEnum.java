package com.gang.etl.common.exception;

/**
 * @Classname SyncErrorEnum
 * @Description TODO
 * @Date 2019/12/29 21:55
 * @Created by zengzg
 */
public enum SyncErrorEnum {

    SYNC_SERVICE_ERROR("E_SYNC_00001", "Service Error", ""),

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
