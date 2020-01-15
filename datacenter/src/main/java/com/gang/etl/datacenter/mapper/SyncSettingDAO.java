package com.gang.etl.datacenter.mapper;

import com.gang.etl.datacenter.entity.SyncSetting;
import org.springframework.stereotype.Repository;

/**
 * SyncSettingDAO继承基类
 */
@Repository
public interface SyncSettingDAO extends MyBatisBaseDao<SyncSetting, SyncSetting> {
}