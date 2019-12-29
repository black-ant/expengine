package com.gang.engine.config;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * @Classname JerseyConfig
 * @Description TODO
 * @Date 2019/11/24 20:20
 * @Created by zengzg
 */
@Component
@ApplicationPath("/engine")
public class JerseyConfig extends ResourceConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JerseyConfig() {
        logger.info("------> this is in  JerseyConfig<-------");
        register(OpenApiResource.class);
    }

}

