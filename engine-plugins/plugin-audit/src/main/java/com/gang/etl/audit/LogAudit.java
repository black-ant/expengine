package com.gang.etl.audit;

import com.gang.etl.engine.api.annotation.SyncAudit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname LogAudit
 * @Description TODO
 * @Date 2021/2/17 14:26
 * @Created by zengzg
 */
@Component
@Aspect
public class LogAudit {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Boolean doLog = Boolean.FALSE;

    @Around("@annotation(audit)")
    Object doAround(ProceedingJoinPoint joinPoint, SyncAudit audit) throws Throwable {
        String method = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
        if (doLog) {
            logger.info("------> joinPoint method :{}<-------", method);
        }
        return joinPoint.proceed();
    }
}
