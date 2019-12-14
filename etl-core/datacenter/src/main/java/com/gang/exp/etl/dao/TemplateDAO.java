package com.gang.exp.etl.dao;

import com.gang.exp.etl.entity.ExpTemplate;
import com.gang.exp.etl.mapper.ExpTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname TemplateDAP
 * @Description TODO
 * @Date 2019/11/5 22:51
 * @Created by ant-black 1016930479@qq.com
 */
@Component
public class TemplateDAO {

    @Autowired
    private ExpTemplateMapper mapper;

    public ExpTemplate get(Integer key) {
        return mapper.selectByKey(key);
    }

    public int insert(ExpTemplate expTemplate) {
        return mapper.insert(expTemplate);
    }

    public ExpTemplate selectByKey(Integer key) {
        return mapper.selectByKey(key);
    }

}
