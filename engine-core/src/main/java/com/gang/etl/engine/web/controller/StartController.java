package com.gang.etl.engine.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
import com.gang.etl.engine.web.logic.StartLogic;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private StartLogic startLogic;

    @Value("${server.port}")
    private String serverPort;

    //    @Autowired
    //    private CacheInvoke cacheInvoke;

    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    @GetMapping("test")
    private String testSql() {
        //        QueryWrapper queryWrapper = new QueryWrapper();

        //        queryWrapper.eq("type_name", "李白");


        //        queryWrapper.and(wrapper -> wrapper.eq("type_name", "活着"));
        //        String sql = queryWrapper.getSqlSelect();
        //        logger.info("------> this is slq :{} <-------", sql);


        QueryWrapper<String> userWrapper = new QueryWrapper<>();
        String type = "111";
        if (StringUtils.isNotBlank(type)) {
            userWrapper.eq("is_admin", "admin".equals(type) ? true : false);
        }
        String keys = "222";
        if (StringUtils.isNotBlank(keys)) {
            userWrapper.and(wrapper -> wrapper.like("login_name", keys).or().like("tel", keys).or().like("email",
                    keys));
        }

        return userWrapper.getSqlSelect();
    }

    @GetMapping("getInfo")
    public String getInfo() {
        logger.info("------> this is in getInfo <-------");
        return "this Port is " + serverPort;
    }

    @GetMapping("getInfo/{info}")
    public String getInfo(@PathVariable("info") String info) {
        return "test getInfo path , this port is :" + info;
    }

    //    @GetMapping("create")
    //    public String create() {
    //        return startLogic.create();
    //    }

    //    @GetMapping("testredis")
    //    public String testRedis() {
    //        logger.info("------> this is test redis <-------");
    //        cacheInvoke.set("1", "ok");
    //        logger.info("------> this is test redis :{}<-------", cacheInvoke.get("1"));
    //        return String.valueOf(cacheInvoke.get("1"));
    //    }
}
