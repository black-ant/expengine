package com.gang.exp.engine.dao;

import com.gang.exp.engine.entity.ExpUser;
import com.gang.exp.engine.mapper.ExpUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname UserDao
 * @Description TODO
 * @Date 2019/10/30 23:01
 * @Created by ant-black 1016930479@qq.com
 */
@Component
public class UserDao {

    @Autowired
    private ExpUserMapper expUserMapper;

    public ExpUser get(String key) {
        return expUserMapper.selectByPrimaryKey(key);
    }
}
