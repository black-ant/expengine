package com.gang.engine.sdk;

import com.gang.common.lib.utils.ReflectionUtils;
import com.gang.etl.engine.api.to.EngineBaseSetting;
import com.gang.etl.engine.api.common.IEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname AntEngineFactory
 * @Description TODO
 * @Date 2020/7/4 16:24
 * @Created by zengzg
 */
@Component
public class AntEngineFactory {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReflectionUtils reflectionUtils;

    /**
     * 继承 IAnyOperation 对象的转换
     *
     * @param t
     * @param clazz
     * @param <T>
     * @param <C>
     * @return
     */
    public <T extends EngineBaseSetting, C> C buildIEngine(T t, Class<C> clazz) {
        return (C) buildIEngine(t, clazz.getName());
    }


    public <T extends EngineBaseSetting> IEngineService buildIEngine(T t, String className) {

        logger.info("------> IEngineService :{} <-------", className);
        IEngineService engineService = reflectionUtils.classLoadReflect(className);
        return engineService;
    }

}
