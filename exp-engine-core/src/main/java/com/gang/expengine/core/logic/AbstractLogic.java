package com.gang.expengine.core.logic;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected VelocityEngine engine;

    public void init() {
        logger.info("------> init <-------");
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();
    }

    public Template getBasicTemplate() {
        return new Template();
    }

    public Template getSQLTeplate() {
        return new Template();
    }

    public Template getFileTeplate(String fileName) {
        return engine.getTemplate(fileName);
    }
}