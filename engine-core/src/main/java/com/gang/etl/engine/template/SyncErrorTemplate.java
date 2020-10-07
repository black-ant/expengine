package com.gang.etl.engine.template;

import com.gang.etl.engine.api.to.EngineBaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname SyncErrorTemplate
 * @Description TODO
 * @Date 2020/10/7 23:00
 * @Created by zengzg
 */
@Component
public class SyncErrorTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param engineProduceBean
     * @return
     */
    public void excute(EngineBaseBean engineProduceBean) {
        logger.info("------> this is SyncErrorTemplate <-------");
    }
}
