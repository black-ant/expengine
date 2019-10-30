package com.gang.expengine.core;


import com.gang.expengine.core.logic.StartLogic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gang.expengine.core")
public class EngineApplication {


    @Autowired
    private StartLogic logic;

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }
}
