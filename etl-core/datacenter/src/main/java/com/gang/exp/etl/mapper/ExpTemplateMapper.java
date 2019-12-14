package com.gang.exp.etl.mapper;

import com.gang.exp.etl.entity.ExpTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ExpTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exp_template
     *
     * @mbg.generated
     */
    int insert(ExpTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exp_template
     *
     * @mbg.generated
     */
    List<ExpTemplate> selectAll();

    ExpTemplate selectByKey(@Param("selectKey") int key);
}