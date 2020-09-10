package com.gang.etl.engine.web.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.engine.web.logic.FieldLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncFieldInfoController
 * @Description TODO
 * @Date 2019/12/30 16:58
 * @Created by zengzg
 */
@RestController
@RequestMapping("/sync/field")
public class SyncFieldInfoController extends AbstratController<SyncFieldInfoServiceImpl, SyncFieldInfo> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


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
