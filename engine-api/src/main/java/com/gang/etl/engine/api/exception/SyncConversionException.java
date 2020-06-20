package com.gang.etl.engine.api.exception;

/**
 * @Classname SyncConversionException
 * @Description TODO
 * @Date 2020/6/20 18:54
 * @Created by zengzg
 */
public class SyncConversionException extends SyncException {

    public SyncConversionException() {
        super();
    }

    public SyncConversionException(String message) {
        super(message);
    }

    public SyncConversionException(String syncType, String message) {
        super(syncType, message);
    }
}
