package com.gang.exp.engine.logic;

import com.gang.exp.engine.dao.UserDao;
import com.gang.exp.engine.entity.ExpUser;
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
