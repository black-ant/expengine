package com.gang.etl.server.logic;

import com.gang.etl.datacenter.dao.SyncFiledInfoDAO;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncFieldInfoLogic
 * @Description TODO
 * @Date 2019/12/30 17:01
 * @Created by zengzg
 */
@Component
public class SyncFieldInfoLogic {

    @Autowired
    private SyncFiledInfoDAO syncFiledInfoDAO;

    public SyncFieldInfo insert(SyncFieldInfo syncFieldInfo) {
        return syncFiledInfoDAO.insert(syncFieldInfo);
    }

    public SyncFieldInfo selectByPrimaryKey(Integer key) {
        return syncFiledInfoDAO.selectByPrimaryKey(key);
    }


}
