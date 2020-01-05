package com.gang.etl.server.controller;

import com.gang.common.swagger.config.SwaggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname StartController
 * @Description TODO
 * @Date 2020/1/2 22:28
 * @Created by zengzg
 */
@RestController
@RequestMapping("/start")
public class StartController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("getInfo")
    public String getInfo() {
        logger.info("------> this is in getInfo <-------");
        return "this Port is " + serverPort;
    }

    @GetMapping("getInfo/{info}")
    public String getInfo(@PathVariable("info") String info) {
        return "test getInfo path , this port is :" + info;
    }
}
