package com.gang.exp.etl;

import com.gang.exp.etl.dao.TemplateDAO;
import com.gang.exp.etl.dao.UserDao;
import com.gang.exp.etl.entity.ExpTemplate;
import com.gang.exp.etl.entity.ExpUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname EngineApplication
 * @Description TODO
 * @Date 2019/12/8 12:27
 * @Created by zengzg
 */

@SpringBootTest(classes = EngineApplication.class)
@RunWith(SpringRunner.class)
public class EngineApplicationTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private UserDao userDao;

    @Test
    public void run() {
        logger.info("------> this is in run <-------");
        //
        ExpTemplate expTemplate = templateDAO.selectByKey(1);
        logger.info("------> this is ExpTemplate :{} <-------", expTemplate);
        //
        //        ExpUser expUser = userDao.selectByKey(1);
        //        logger.info("------> this is expUser :{} <-------", expUser);
    }
}
