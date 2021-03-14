package com.gang.etl.engine.api.config;

import com.gang.etl.engine.api.annotation.EngineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Classname EngineProperties
 * @Description TODO
 * @Date 2021/3/6 16:12
 * @Created by zengzg
 */
@Component
public class EngineProperties {

    @Autowired
    private Environment environment;

    @EngineConfig
    private EngineLogger engineLogger;


    public void initConfig(){

    }


    public void initDefaultConfig(){

    }

    public void initDBConfig(){}
}
