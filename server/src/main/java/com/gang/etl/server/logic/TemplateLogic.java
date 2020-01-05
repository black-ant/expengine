package com.gang.etl.server.logic;//package com.gang.etl.logic;
//
//import com.gang.etl.common.query.TemplateLogsQuery;
//import TemplateDAO;
//import ExpTemplate;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.velocity.Template;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @Classname TemplateLogic
// * @Description TODO
// * @Date 2019/11/5 22:47
// * @Created by ant-black 1016930479@qq.com
// */
//@Service
//public class TemplateLogic extends AbstractLogic {
//
//    @Autowired
//    private TemplateDAO templateDAO;
//
//
//    public ExpTemplate findPrimary(Integer key) {
//        return templateDAO.get(key);
//    }
//
//    public int insert(ExpTemplate expTemplate) {
//        return templateDAO.insert(expTemplate);
//    }
//
//
//    /**
//     * 从数据库获取模板消息
//     *
//     * @param templateLogsQuery
//     * @return
//     */
//    public String getTemplateFromDatebase(TemplateLogsQuery templateLogsQuery) {
//        ExpTemplate template = templateDAO.get(1);
//        String body = ObjectUtils.isEmpty(template) ? "" : template.getTemplateBody();
//        return body;
//    }
//
//    /**
//     * 文件获取模板文件
//     *
//     * @param sysRoot
//     * @param basePath
//     * @return
//     */
////    public Template loadTemplateFile(String sysRoot, String basePath) {
////        return etl.getTemplate(sysRoot + basePath, "UTF-8");
////    }
//
//    /**
//     * @param classPath
//     * @return
//     */
////    public Template loadTemplateClassPath(String classPath) {
////        return etl.getTemplate(classPath, "gbk");
////    }
//}
