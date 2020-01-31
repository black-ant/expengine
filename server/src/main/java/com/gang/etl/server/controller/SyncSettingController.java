package com.gang.etl.server.controller;

import com.gang.common.lib.to.ResponseModel;
import com.gang.etl.datacenter.entity.SyncSetting;
import com.gang.etl.datacenter.mapper.SyncSettingMapper;
import com.gang.etl.datacenter.service.ISyncSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncSettingController
 * @Description TODO
 * @Date 2020/1/15 23:06
 * @Created by zengzg
 */
@RestController
@RequestMapping("setting")
public class SyncSettingController {

    @Autowired
    private ISyncSettingService settingService;

    @GetMapping("/get/{key}")
    public ResponseModel get(@PathVariable("key") String key) {
        return ResponseModel.commonResponse(
                settingService.getById(key)
        );
    }

    @GetMapping("/findall}")
    public ResponseModel findAll(@PathVariable("key") String key) {
        return ResponseModel.commonResponse(
                settingService.list()
        );
    }

    @PostMapping("/insert")
    public String create(SyncSetting syncSetting) {
        return "";
    }
}
