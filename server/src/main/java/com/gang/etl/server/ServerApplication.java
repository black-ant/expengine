package com.gang.etl.server;

import com.gang.etl.server.logic.StartLogic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname server
 * @Description TODO
 * @Date 2020/1/2 23:41
 * @Created by zengzg
 */
@SpringBootApplication(scanBasePackages = {"com.gang", "com.gang.common",})
@MapperScan(value = {"com.gang"})
public class ServerApplication {

    @Autowired
    private StartLogic startLogic;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
