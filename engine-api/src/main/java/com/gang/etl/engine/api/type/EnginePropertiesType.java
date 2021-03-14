package com.gang.etl.engine.api.type;

/**
 * @Classname EnginePropertiesType
 * @Description TODO
 * @Date 2021/3/6 16:18
 * @Created by zengzg
 */
public enum EnginePropertiesType {


    LOGGER("");


    private String prefix;

    EnginePropertiesType(String prefix) {
        this.prefix = prefix;
    }
}
