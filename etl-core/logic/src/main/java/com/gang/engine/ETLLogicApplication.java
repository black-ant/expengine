package com.gang.engine;

import com.gang.engine.common.ExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname ETLLogicApplication
 * @Description TODO
 * @Date 2019/12/28 21:58
 * @Created by zengzg
 */
@SpringBootApplication
public class ETLLogicApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ETLLogicApplication.class, args).getBeanFactory();
    }
}
