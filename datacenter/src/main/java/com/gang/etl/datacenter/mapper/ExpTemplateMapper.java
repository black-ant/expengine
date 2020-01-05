package com.gang.etl.datacenter.mapper;

import com.gang.etl.datacenter.entity.ExpTemplate;
import java.util.List;

public interface ExpTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpTemplate record);

    ExpTemplate selectByPrimaryKey(Integer id);

    List<ExpTemplate> selectAll();

    int updateByPrimaryKey(ExpTemplate record);
}