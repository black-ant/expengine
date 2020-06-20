package com.gang.etl.engine.api.exception;

import com.gang.common.lib.exception.CommonException;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.C;

import java.util.Base64;

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

    public SyncException(String syncType, String message) {
        super(message);
        this.syncType = syncType;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }
}

