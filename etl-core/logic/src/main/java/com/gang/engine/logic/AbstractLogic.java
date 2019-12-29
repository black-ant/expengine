package com.gang.engine.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AbstractLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init() {
        logger.info("------> init <-------");
        //        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        //        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //        engine.init();
    }

    //    public Template getBasicTemplate() {
    //        return new Template();
    //    }

    //    public Template getSQLTeplate() {
    //        return new Template();
    //    }

    //    public Template getFileTemplate(String fileName) {
    //        return engine.getTemplate(fileName);
    //    }

}
