package com.gang.etl.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gang.common.lib.to.ResponseModel;
import com.gang.common.lib.utils.CommonStringUtils;
import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.sdk.api.annotation.SyncClass;
import com.gang.sdk.api.annotation.SyncTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SyncTypeController
 * @Description TODO
 * @Date 2020/1/20 22:43
 * @Created by zengzg
 */
@RestController
@RequestMapping("/synctype")
public class SyncTypeController extends AbstratController<SyncTypeServiceImpl, SyncType> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("refush/{type}")
    public ResponseModel<List<SyncType>> refush(@PathVariable("type") String type) {
        List<String> typeClassList = new ArrayList<>();
        if ("ALL".equals(type)) {
            typeClassList = ReflectionUtils.scanClazzName("com.gang.ext.sdk", Boolean.TRUE);
        } else {
            typeClassList =
                    ReflectionUtils.scanClazzName("com.gang.ext.sdk." + CommonStringUtils.lineToHump(type).toLowerCase(),
                            Boolean.TRUE);
        }
        List<SyncType> syncTypesList = new ArrayList<>();
        typeClassList.forEach(item -> {
            try {
                Class clazz = Class.forName(item);
                logger.info("------> class Name is :{} <-------", item);
                if (null != clazz.getAnnotation(SyncClass.class) || null != clazz.getAnnotation(SyncTO.class)) {
                    SyncType syncType = null != clazz.getAnnotation(SyncClass.class) ? createSyncType(clazz,
                            clazz.getAnnotation(SyncClass.class)) : createSyncType(clazz,
                            clazz.getAnnotation(SyncTO.class));

                    UpdateWrapper<SyncType> userUpdateWrapper = new UpdateWrapper<>();
                    userUpdateWrapper.eq("type_code", syncType.getTypeCode());
                    baseMapper.saveOrUpdate(syncType, userUpdateWrapper);
                    syncTypesList.add(syncType);
                } else {
                    logger.info("------> other class <-------");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        return ResponseModel.commonResponse(syncTypesList);
    }

    public SyncType createSyncType(Class clazz, Annotation annotation) {
        SyncType syncType = new SyncType();
        syncType.setTypeClass(clazz.getName());
        String appName = "";
        String typeName = "";
        String classType = "";
        if (annotation instanceof SyncTO) {
            SyncTO syncTO = (SyncTO) annotation;
            appName = syncTO.app();
            typeName = syncTO.type();
            syncType.setTypeFiledInfo(JSONObject.toJSONString(ReflectionUtils.getFieldsName(clazz, null)));
            classType = "TO";
        } else {
            SyncClass syncClass = (SyncClass) annotation;
            appName = syncClass.app();
            typeName = syncClass.type();
            classType = "OP";
        }

        syncType.setSupplierId(appName);
        syncType.setSupplierName(appName);
        syncType.setDataType(classType + "_" + typeName);
        syncType.setTypeName(appName + "_" + typeName);
        syncType.setTypeCode(appName + "_" + syncType.getDataType());

        return syncType;
    }


    @GetMapping("getcode/{code}")
    public ResponseModel<String> get(@PathVariable("code") String code) {
        logger.info("------> this is in get SyncType <-------");

        return ResponseModel.commonResponse("this is response");
    }

    @PostMapping("deletecode/{code}")
    public ResponseModel<String> delete(@PathVariable("code") String code) {
        logger.info("------> delete <-------");
        return ResponseModel.commonResponse(code);
    }

    //    @InitBinder
    //    @Autowired
    //    public void setRespository(SyncTypeServiceImpl syncTypeService) {
    //        this.baseMapper = syncTypeService;
    //    }


}
