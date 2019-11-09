package com.gang.expengine.core.dao.mapper;

import com.gang.expengine.core.dao.entity.ExpUser;
import java.util.List;

public interface ExpUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExpUser record);

    ExpUser selectByPrimaryKey(String id);

    List<ExpUser> selectAll();

    int updateByPrimaryKey(ExpUser record);
}