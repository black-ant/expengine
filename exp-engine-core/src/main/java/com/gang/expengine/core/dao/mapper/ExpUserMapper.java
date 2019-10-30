package com.gang.expengine.core.dao.mapper;

import com.gang.expengine.core.dao.entity.ExpUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ExpUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(ExpUser record);

    ExpUser selectByPrimaryKey(String id);

    List<ExpUser> selectAll();

    int updateByPrimaryKey(ExpUser record);
}