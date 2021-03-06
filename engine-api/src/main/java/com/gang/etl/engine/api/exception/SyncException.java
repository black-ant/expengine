package com.gang.etl.engine.api.exception;

import com.gang.common.lib.exception.CommonException;

/**
 * @Classname SyncException
 * @Description TODO
 * @Date 2019/12/29 21:48
 * @Created by zengzg
 */
public class SyncException extends CommonException {

    private String syncType;

    public SyncException() {
        super();
    }

    public SyncException(String message) {
        super(message);
    }

    public SyncException(SyncErrorEnum errorEnum) {
        super(errorEnum.getErrorInfo(), errorEnum.getErrorCode());
    }

    public SyncException(SyncErrorEnum errorEnum, String syncType) {
        super(errorEnum.getErrorInfo(), errorEnum.getErrorCode());
        this.syncType = syncType;
    }


    public SyncException(String code, String message) {
        super(message, code);
        this.syncType = syncType;
    }

    public SyncException(String code, String syncType, String message) {
        super(message, code);
        this.syncType = syncType;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }
}

