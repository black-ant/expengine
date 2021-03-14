package com.gang.etl.audit.api;

import com.gang.etl.audit.annotation.EngineAuditAnnotation;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname IAuditService
 * @Description TODO
 * @Date 2021/3/6 15:39
 * @Created by zengzg
 */
public interface IAuditService {

    /**
     * @return
     */
    Pair<Boolean, String> doHandle(EngineAuditAnnotation auditAnnotation);

    /**
     * @return
     */
    boolean support(Boolean checkSuccess, EngineAuditAnnotation auditAnnotation);
}
