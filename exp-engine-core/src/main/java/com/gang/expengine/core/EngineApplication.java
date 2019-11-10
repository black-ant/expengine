package com.gang.expengine.core;


import com.gang.expengine.core.logic.StartLogic;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.gang.expengine.core")
public class EngineApplication {


    @Autowired
    private StartLogic logic;

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }

    @Bean
    public OpenApiResource openApiResource() {
        return new OpenApiResource();
    }
}
