package com.gang.etl.engine.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gang.common.lib.to.ResponseModel;
import com.gang.common.lib.utils.CommonStringUtils;
import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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

                SyncType syncType = null;
                //                        null != clazz.getAnnotation(SyncClass.class) ? createSyncType(clazz,
                //                                clazz.getAnnotation(SyncClass.class)) :
                //                                null != clazz.getAnnotation(SyncTO.class) ? createSyncType(clazz,
                //                                        clazz.getAnnotation(SyncTO.class)) :
                //                                        null != clazz.getAnnotation(SyncConfig.class) ? createSyncType(clazz,
                //                                                clazz.getAnnotation(SyncConfig.class)) : null;

                if (null != syncType) {
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

    /**
     * TODO : 是否使用一个注解 , 更加清晰 ?
     *
     * @param clazz
     * @param annotation
     * @return
     */
    public SyncType createSyncType(Class clazz, Annotation annotation) {
        SyncType syncType = new SyncType();
        syncType.setTypeClass(clazz.getName());
        String appName = "";
        String typeName = "";
        String classType = "";
        String aliasName = "";
        //        if (annotation instanceof SyncTO) {
        //            SyncTO syncTO = (SyncTO) annotation;
        //            appName = syncTO.app();
        //            typeName = syncTO.type();
        //            aliasName = syncTO.name();
        //            syncType.setTypeFiledInfo(getFieldsNameJSON(clazz));
        //            classType = "TO";
        //        } else if (annotation instanceof SyncClass) {
        //            SyncClass syncClass = (SyncClass) annotation;
        //            appName = syncClass.app();
        //            typeName = syncClass.type();
        //            aliasName = syncClass.name();
        //            classType = "OP";
        //        } else {
        //            SyncConfig syncTO = (SyncConfig) annotation;
        //            appName = syncTO.app();
        //            typeName = syncTO.type();
        //            aliasName = syncTO.name();
        //            syncType.setTypeFiledInfo(getFieldsNameJSON(clazz));
        //            classType = "TO";
        //        }

        syncType.setSupplierId(appName);
        syncType.setSupplierName(aliasName);
        syncType.setTypeCode(classType);
        syncType.setTypePart(typeName);
        syncType.setTypeCode(appName + "_" + typeName + "_" + syncType.getTypeCode());

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

    /**
     * 获取JSON 属性
     *
     * @param clazz
     * @return
     */
    public String getFieldsNameJSON(Class<?> clazz) {
        JSONObject fields = new JSONObject();
        //        while (Object.class != clazz && clazz != null) {
        //            for (Field field : clazz.getDeclaredFields()) {
        //                SyncField syncField = field.getAnnotation(SyncField.class);
        //                fields.put(field.getName(), null == syncField ? "" : syncField.defaultValue());
        //            }
        //            clazz = clazz.getSuperclass();
        //        }
        return fields.toJSONString();
    }

}
