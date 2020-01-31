package com.gang.etl.datacenter.dao;

import com.gang.etl.datacenter.entity.ExpUser;
import com.gang.etl.datacenter.mapper.ExpUserMapper;
import com.gang.etl.datacenter.service.IExpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname ExpUserDAO
 * @Description TODO
 * @Date 2020/1/2 23:39
 * @Created by zengzg
 */
@Component
public class ExpUserDAO {

    @Autowired
    private IExpUserService expUserMapper;


    public List<ExpUser> findAll() {
        return expUserMapper.list();
    }
}
