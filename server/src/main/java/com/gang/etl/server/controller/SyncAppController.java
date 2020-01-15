package com.gang.etl.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SyncAppController
 * @Description TODO
 * @Date 2020/1/15 21:50
 * @Created by zengzg
 */
@RestController
@RequestMapping("/app")
public class SyncAppController {

    @GetMapping("get/{key}")
    public String get(@PathVariable("key") String key) {
        return "";
    }


}
