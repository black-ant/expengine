package com.gang.expengine.core.logic;

import com.gang.expengine.core.common.model.query.TemplateLogsQuery;
import com.gang.expengine.core.dao.TemplateDAO;
import com.gang.expengine.core.dao.entity.ExpTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

/**
 * @Classname TemplateLogic
 * @Description TODO
 * @Date 2019/11/5 22:47
 * @Created by ant-black 1016930479@qq.com
 */
@Service
public class TemplateLogic extends AbstractLogic {

    @Autowired
    private TemplateDAO templateDAO;


    public ExpTemplate findPrimary(Integer key) {
        return templateDAO.get(key);
    }

    public int insert(ExpTemplate expTemplate) {
        return templateDAO.insert(expTemplate);
    }


    /**
     * 从数据库获取模板消息
     *
     * @param templateLogsQuery
     * @return
     */
    public String getTemplateFromDatebase(TemplateLogsQuery templateLogsQuery) {
        ExpTemplate template = templateDAO.get(1);
        String body = ObjectUtils.isEmpty(template) ? "" : template.getTemplateBody();
        return body;
    }

    /**
     * 文件获取模板文件
     *
     * @param sysRoot
     * @param basePath
     * @return
     */
    public Template loadTemplateFile(String sysRoot, String basePath) {
        return engine.getTemplate(sysRoot + basePath, "UTF-8");
    }

    /**
     * @param classPath
     * @return
     */
    public Template loadTemplateClassPath(String classPath) {
        return engine.getTemplate(classPath, "gbk");
    }
}
