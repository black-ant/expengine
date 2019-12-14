package com.gang.exp.engine.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2019/10/30 23:03
 * @Created by ant-black 1016930479@qq.com
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstarctController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Path("one")
    public String test() {
        logger.info("------> this is in test <-------");
        return "one";
    }

}
