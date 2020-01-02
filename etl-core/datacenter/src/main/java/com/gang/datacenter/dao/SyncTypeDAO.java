package com.gang.datacenter.dao;

import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.mapper.SyncTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncTypeDAO
 * @Description TODO
 * @Date 2019/12/28 21:16
 * @Created by zengzg
 */
@Component
public class SyncTypeDAO {

    @Autowired
    private SyncTypeMapper syncTypeMapper;

    public SyncType getByKey(String key) {
        return syncTypeMapper.selectByPrimaryKey(key);
    }

    public SyncType getByNameAndData(String typeName, String dataType) {
        return syncTypeMapper.selectByNameAndData(typeName, dataType);
    }

}
