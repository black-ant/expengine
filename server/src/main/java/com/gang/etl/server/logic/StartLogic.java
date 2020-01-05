package com.gang.etl.server.logic;

import com.gang.etl.datacenter.dao.ExpUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Classname StartLogic
 * @Description TODO
 * @Date 2020/1/2 23:43
 * @Created by zengzg
 */
@Component
public class StartLogic implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpUserDAO expUserDAO;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("------> {} <-------", expUserDAO.findAll());

    }
}
