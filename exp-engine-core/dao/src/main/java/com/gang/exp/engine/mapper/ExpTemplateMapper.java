package com.gang.exp.engine.mapper;


import com.gang.exp.engine.entity.ExpTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpTemplate record);

    ExpTemplate selectByPrimaryKey(Integer id);

    List<ExpTemplate> selectAll();

    int updateByPrimaryKey(ExpTemplate record);
}