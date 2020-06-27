package com.gang.etl.datacenter.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * DAO公共基类，由MybatisGenerator自动生成请勿修改
 *
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK>    The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 */
public interface MyBatisBaseMapper<Model, PK extends Serializable> {

    int insert(Model record);

    int update(Model record);

    int insertSelective(Model record);

    Model getOne(PK key);

    List<Model> findAll();

    void deleteById(PK key);


}
