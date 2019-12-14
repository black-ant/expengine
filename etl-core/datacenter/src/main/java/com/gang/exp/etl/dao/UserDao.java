package com.gang.exp.etl.dao;

import com.gang.exp.etl.entity.ExpUser;
import com.gang.exp.etl.mapper.ExpUserMapper;
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

    public ExpUser selectByKey(Integer key) {
        return expUserMapper.selectByKey(key);
    }
}
