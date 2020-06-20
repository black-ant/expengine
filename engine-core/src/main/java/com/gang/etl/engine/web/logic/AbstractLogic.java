package com.gang.etl.engine.web.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AbstractLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init() {
        logger.info("------> init <-------");
        //        etl.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        //        etl.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //        etl.init();
    }

    //    public Template getBasicTemplate() {
    //        return new Template();
    //    }

    //    public Template getSQLTeplate() {
    //        return new Template();
    //    }

    //    public Template getFileTemplate(String fileName) {
    //        return etl.getTemplate(fileName);
    //    }

}
