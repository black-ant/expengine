package com.gang.etl.mapper;

import com.gang.etl.entity.SyncType;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyncTypeMapper {

    int deleteByPrimaryKey(String id);

    int insert(SyncType record);

    SyncType selectByPrimaryKey(String id);

    SyncType selectByNameAndData(String typeName, String dataType);

    List<SyncType> selectAll();

    int updateByPrimaryKey(SyncType record);
}