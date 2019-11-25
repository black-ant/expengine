package com.gang.exp.engine.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gang.exp.engine.**",})
public class EngineServer {

    public static void main(String[] args) {
        SpringApplication.run(EngineServer.class, args);
    }

}
