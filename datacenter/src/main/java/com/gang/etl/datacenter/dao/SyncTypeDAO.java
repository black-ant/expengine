package com.gang.etl.datacenter.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gang.etl.datacenter.entity.SyncType;
import com.gang.etl.datacenter.mapper.SyncTypeMapper;
import com.gang.etl.datacenter.service.ISyncTypeService;
import com.gang.etl.datacenter.service.impl.SyncTypeServiceImpl;
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
public class SyncTypeDAO extends AbstractEngineDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncTypeServiceImpl syncTypeService;

    /**
     * Use union code search one result
     *
     * @param typeCode
     * @return
     */
    public SyncType getByTypeCode(String typeCode) {
        return syncTypeService.getOne(getQueryWrapper("type_code", typeCode));
    }


    /**
     * @param typeName
     * @param dataType
     * @return
     */
    public SyncType getByNameAndData(String typeName, String dataType) {
        return syncTypeService.getById(dataType);
    }

    /**
     * @param syncType
     * @return
     */
    public SyncType insert(SyncType syncType) {
        syncTypeService.save(syncType);
        return syncType;
    }
}
