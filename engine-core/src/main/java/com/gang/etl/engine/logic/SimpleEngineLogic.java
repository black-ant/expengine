package com.gang.etl.engine.logic;

import com.gang.etl.datacenter.dao.SyncTypeDAO;
import com.gang.etl.datacenter.entity.SyncType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname SimpleEngineLogic
 * @Description TODO
 * @Date 2020/6/20 19:08
 * @Created by zengzg
 */
@Component
public class SimpleEngineLogic {

    @Autowired
    private SyncTypeDAO syncTypeDAO;

    /**
     * @param typeCode
     */
    public void excute(String typeCode) {
        SyncType syncType = syncTypeDAO.getByTypeCode(typeCode);



    }
}
