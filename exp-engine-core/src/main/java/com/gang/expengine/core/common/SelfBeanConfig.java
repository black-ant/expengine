package com.gang.expengine.core.common;


import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelfBeanConfig {

    private static String beanVersion = "1.0";

    @Bean
    public VelocityEngine getVelocityEngine() {
        return new VelocityEngine();
    }
}
