package com.gang.etl;

import com.gang.etl.dao.StartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname EngineApplication
 * @Description TODO
 * @Date 2019/12/8 15:24
 * @Created by zengzg
 */
@SpringBootApplication
public class EngineApplication {

    @Autowired
    private StartDAO startDAO;

    public static void main(final String[] args) {
        SpringApplication.run(EngineApplication.class, args).getBeanFactory();
    }

}
