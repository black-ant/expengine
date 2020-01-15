package com.gang.etl.server.controller;

import com.gang.etl.datacenter.entity.SyncSetting;
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

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return "";
    }

    @PostMapping("/create")
    public String create(SyncSetting syncSetting) {
        return "";
    }
}
