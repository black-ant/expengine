package com.gang.etl.datacenter.mapper;

import com.gang.etl.datacenter.entity.SyncType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SyncTypeMapper extends MyBatisBaseMapper<SyncType, String> {

    int deleteByPrimaryKey(String id);

    int insert(SyncType record);

    SyncType selectByPrimaryKey(String id);

    SyncType selectByNameAndData(String typeName, String dataType);

    List<SyncType> selectAll();

    int updateByPrimaryKey(SyncType record);
}