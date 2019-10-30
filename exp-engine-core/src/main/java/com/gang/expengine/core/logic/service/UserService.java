package com.gang.expengine.core.logic.service;

import com.gang.expengine.core.dao.UserDao;
import com.gang.expengine.core.dao.entity.ExpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2019/10/30 23:02
 * @Created by ant-black 1016930479@qq.com
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ExpUser get(String key) {
        return userDao.get(key);
    }
}
