package com.gang.etl.engine.api.type;

import com.gang.etl.engine.api.annotation.SyncCreate;
import com.gang.etl.engine.api.annotation.SyncDelete;
import com.gang.etl.engine.api.annotation.SyncInit;
import com.gang.etl.engine.api.annotation.SyncSearch;
import com.gang.etl.engine.api.annotation.SyncUpdate;

/**
 * @Classname SyncOperationType
 * @Description TODO
 * @Date 2020/10/7 16:41
 * @Created by zengzg
 */
public enum SyncOperationType {

    CREATE(SyncCreate.class, "CREATE"),
    UPDATE(SyncUpdate.class, "UPDATE"),
    DELETE(SyncDelete.class, "DELETE"),
    SEARCH(SyncSearch.class, "SEARCH"),
    INIT(SyncInit.class, "INIT");

    private Class clazzName;
    private String operationType;

    SyncOperationType(Class clazzName, String operationType) {
        this.clazzName = clazzName;
        this.operationType = operationType;
    }

    public Class getClazzName() {
        return clazzName;
    }

    public String getOperationType() {
        return operationType;
    }
}

