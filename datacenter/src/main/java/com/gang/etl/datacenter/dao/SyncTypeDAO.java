package com.gang.etl.datacenter.dao;

import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.mapper.SyncTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncTypeDAO
 * @Description TODO
 * @Date 2020/1/5 15:53
 * @Created by zengzg
 */
@Component
public class SyncTypeDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncTypeMapper syncTypeMapper;

    public SyncType getByKeyOrCode(String key) {
        return syncTypeMapper.selectByPrimaryKey(key);
    }

    public SyncType getByNameAndData(String typeName, String dataType) {
        return syncTypeMapper.selectByNameAndData(typeName, dataType);
    }

    public SyncType insert(SyncType syncType) {
        syncTypeMapper.insert(syncType);
        return syncType;
    }
}
