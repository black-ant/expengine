package com.gang.etl.datacenter.dao;

import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.datacenter.mapper.SyncFieldInfoMapper;
import com.gang.etl.datacenter.service.ISyncFieldInfoService;
import com.gang.etl.datacenter.service.impl.SyncFieldInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncFiledInfoDAO
 * @Description TODO
 * @Date 2019/12/30 16:49
 * @Created by zengzg
 */
@Component
public class SyncFiledInfoDAO {

    @Autowired
    private SyncFieldInfoServiceImpl syncFieldInfoMapper;

    public SyncFieldInfo insert(SyncFieldInfo syncFieldInfo) {
        syncFieldInfoMapper.save(syncFieldInfo);
        return syncFieldInfo;
    }

    public SyncFieldInfo selectByPrimaryKey(Integer hey) {
        return syncFieldInfoMapper.getById(hey);
    }
}
