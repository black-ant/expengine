package com.gang.etl.datacenter.mapper;

import com.gang.etl.datacenter.entity.SyncStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * SyncStrategyDAO继承基类
 */
@Mapper
@Repository
public interface SyncStrategyMapper extends MyBatisBaseMapper<SyncStrategy, String> {
}