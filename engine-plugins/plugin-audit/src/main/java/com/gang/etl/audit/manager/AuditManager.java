package com.gang.etl.audit.manager;

import com.gang.etl.audit.annotation.EngineAuditAnnotation;
import com.gang.etl.audit.api.IAuditService;
import com.gang.etl.engine.api.utils.SyncContextUtils;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname AuditManager
 * @Description 审计模块 , 审计的目的时送耦合
 * 1 无需通过注解也可以审计
 * 2
 *
 * @Date 2021/3/6 15:37
 * @Created by zengzg
 */
@Component
public class AuditManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Map<String, IAuditService> clazzMap = new ConcurrentHashMap();

    /**
     * 运行审计逻辑
     */
    public void runAudit() {

        logger.info("------> [运行审计逻辑] <-------");

        for (IAuditService serviceItem : clazzMap.values()) {
//            if (serviceItem.support(restCheck.getKey(), annotation, request)) {
//                restCheck = serviceItem.doHandle(restCheck, annotation, request, response);
//            }
        }
    }


    /**
     * 运行自定义业务处理
     *
     * @param beanName
     * @param checkSuccess
     * @param request
     * @param response
     */
    private Pair<Boolean, String> doPermissionService(String beanName, EngineAuditAnnotation annotation,
                                                      Pair<Boolean, String> checkSuccess, HttpServletRequest request, HttpServletResponse response) {
        Pair<Boolean, String> permissionSuccess = checkSuccess;
        if (StringUtils.isNotEmpty(beanName)) {
            try {
                IAuditService permissionService;
                if (!clazzMap.containsKey(beanName)) {
                    Class clazz = Class.forName(beanName);
                    permissionService = (IAuditService) SyncContextUtils.getBean(clazz);
                    clazzMap.put(beanName, permissionService);
                } else {
                    permissionService = clazzMap.get(beanName);
                }
//                if (permissionService.support(NULL)) {
//                    permissionSuccess = permissionService.doHandle(checkSuccess, annotation, request, response);
//                }

            } catch (Exception e) {
                logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
            }
        }
        return permissionSuccess;

    }
}
