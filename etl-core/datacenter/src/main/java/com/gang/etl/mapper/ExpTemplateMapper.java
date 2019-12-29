package com.gang.etl.mapper;

import com.gang.etl.entity.ExpTemplate;
import java.util.List;

public interface ExpTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpTemplate record);

    ExpTemplate selectByPrimaryKey(Integer id);

    List<ExpTemplate> selectAll();

    int updateByPrimaryKey(ExpTemplate record);
}