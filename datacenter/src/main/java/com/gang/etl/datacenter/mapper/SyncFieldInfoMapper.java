package com.gang.etl.datacenter.mapper;

import com.gang.etl.datacenter.entity.SyncFieldInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SyncFieldInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncFieldInfo record);

    SyncFieldInfo selectByPrimaryKey(Integer id);

    List<SyncFieldInfo> selectAll();

    int updateByPrimaryKey(SyncFieldInfo record);
}