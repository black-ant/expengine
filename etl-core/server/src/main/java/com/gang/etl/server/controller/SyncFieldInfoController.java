package com.gang.etl.server.controller;

import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.server.logic.SyncFieldInfoLogic;
import com.gang.ext.parent.to.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname SyncFieldInfoController
 * @Description TODO
 * @Date 2019/12/30 16:58
 * @Created by zengzg
 */
@Controller
@RequestMapping("/syncfield")
public class SyncFieldInfoController {

    @Autowired
    private SyncFieldInfoLogic syncFieldInfoLogic;

    @PostMapping("/insert")
    public ResponseModel<SyncFieldInfo> insert(SyncFieldInfo syncFieldInfo) {
        return ResponseModel.commonResponse(syncFieldInfoLogic.insert(syncFieldInfo));
    }

    @GetMapping("/get/{key}")
    public ResponseModel<SyncFieldInfo> get(@PathVariable("key") Integer key) {
        return ResponseModel.commonResponse(syncFieldInfoLogic.selectByPrimaryKey(key));
    }

}
