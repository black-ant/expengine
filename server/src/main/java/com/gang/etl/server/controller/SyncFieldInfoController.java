package com.gang.etl.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncSettingServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.out.common.logic.SyncClassInfo;
import com.gang.etl.server.logic.FieldLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname SyncFieldInfoController
 * @Description TODO
 * @Date 2019/12/30 16:58
 * @Created by zengzg
 */
@RestController
@RequestMapping("/syncfield")
public class SyncFieldInfoController extends AbstratController<SyncFieldInfoServiceImpl, SyncFieldInfo> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SyncClassInfo classInfo;

    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    @Autowired
    private FieldLogic fieldInfo;

    @GetMapping("getInfo")
    public ResponseModel<String> getFieldInfo(@RequestParam("key") String key) {
        SyncType syncType = syncTypeService.getById(key);
        return ResponseModel.commonResponse(syncType.getTypeFiledInfo());
    }

    @GetMapping("getto")
    public ResponseModel<SyncFieldInfo> getTO(@RequestParam("code") String code) {
        return ResponseModel.commonResponse(fieldInfo.getFieldInfo(code));
    }

    @Override
    public ResponseModel insert(@RequestBody SyncFieldInfo entity) {
        logger.info("------> this is in inner insert :{}<-------", entity);
        return ResponseModel.commonResponse(baseMapper.save(entity));
    }


}
