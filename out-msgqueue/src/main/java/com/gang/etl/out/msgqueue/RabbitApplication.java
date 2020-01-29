package com.gang.etl.out.msgqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname RabbitApplication
 * @Description TODO
 * @Date 2020/1/29 22:53
 * @Created by zengzg
 */
@SpringBootApplication
public class RabbitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
    }
}
