package com.gang.exp.etl.mapper;

import com.gang.exp.etl.entity.ExpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exp_user
     *
     * @mbg.generated
     */
    int insert(ExpUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exp_user
     *
     * @mbg.generated
     */
    List<ExpUser> selectAll();

    ExpUser selectByKey(@Param("selectKey") int key);
}