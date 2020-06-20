package com.gang.etl.engine.web.controller;

import com.gang.etl.datacenter.entity.ExpUser;
import com.gang.etl.datacenter.service.impl.ExpUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ExpUserController
 * @Description TODO
 * @Date 2020/2/1 17:19
 * @Created by zengzg
 */

@RestController
@RequestMapping("/user")
public class ExpUserController extends AbstratController<ExpUserServiceImpl, ExpUser> {

    @InitBinder
    @Autowired
    public void setRespository(ExpUserServiceImpl expUserService) {
        this.baseMapper = expUserService;
    }
}
