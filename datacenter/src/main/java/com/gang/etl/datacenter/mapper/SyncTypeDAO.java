package com.gang.etl.datacenter.mapper;

import com.gang.etl.datacenter.entity.SyncType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * SyncTypeDAO继承基类
 */
@Mapper
@Repository
public interface SyncTypeDAO extends MyBatisBaseDao<SyncType, String> {
}