package com.gang.etl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @Classname CommonOutApplication
 * @Description TODO
 * @Date 2019/12/29 11:43
 * @Created by zengzg
 */
@SpringBootApplication
@ComponentScan(value = {"com.gang"})
public class CommonOutApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CommonOutApplication.class, args).getBeanFactory();
    }
}
