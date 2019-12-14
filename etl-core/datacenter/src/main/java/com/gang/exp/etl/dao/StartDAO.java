package com.gang.exp.etl.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Classname StartDAO
 * @Description TODO
 * @Date 2019/12/8 15:27
 * @Created by zengzg
 */
@Component
public class StartDAO implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("------> this is run <-------");
        templateDAO.selectByKey(1);
    }
}
