package com.gang.expengine.core.exception;

public enum ExchangeErrorEnum {

    ENG00001("ENG00001", "Server Error", "Please Relation Administrator");

    private String errorCode;
    private String errorMsg;
    private String solution;

    ExchangeErrorEnum(String errorCode, String errorMsg, String solution) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.solution = solution;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
