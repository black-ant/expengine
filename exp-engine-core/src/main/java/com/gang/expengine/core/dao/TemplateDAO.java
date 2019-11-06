package com.gang.expengine.core.dao;

import com.gang.expengine.core.dao.entity.ExpTemplate;
import com.gang.expengine.core.dao.mapper.ExpTemplateMapper;
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
        return mapper.selectByPrimaryKey(key);
    }

    public int insert(ExpTemplate expTemplate) {
        return mapper.insert(expTemplate);
    }

}
