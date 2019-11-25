package com.gang.exp.engine.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @Classname StartConfig
 * @Description TODO
 * @Date 2019/11/25 22:11
 * @Created by zengzg
 */
@Service
public class StartConfig implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("------> this is run <-------");
    }
}
