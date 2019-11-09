package com.gang.expengine.core.dao.mapper;

import com.gang.expengine.core.dao.entity.ExpTemplate;
import java.util.List;

public interface ExpTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpTemplate record);

    ExpTemplate selectByPrimaryKey(Integer id);

    List<ExpTemplate> selectAll();

    int updateByPrimaryKey(ExpTemplate record);
}