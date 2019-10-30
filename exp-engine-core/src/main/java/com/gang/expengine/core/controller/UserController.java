package com.gang.expengine.core.controller;

import com.gang.expengine.core.common.model.ResponseModel;
import com.gang.expengine.core.dao.entity.ExpUser;
import com.gang.expengine.core.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2019/10/30 23:03
 * @Created by ant-black 1016930479@qq.com
 */
@RestController
@RequestMapping("user")
public class UserController extends AbstarctController {

    @Autowired
    private UserService userService;

    @GetMapping("/{key}")
    public ResponseModel<ExpUser> get(@PathVariable("key") String key) {
        return createResponse(userService.get(key));
    }
}
