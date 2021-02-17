package com.gang.etl.engine.thread;

import com.gang.etl.engine.api.to.EngineBaseBean;
import com.gang.etl.engine.api.common.IEngineService;
import com.gang.etl.engine.container.SyncBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname EngineThread
 * @Description TODO
 * @Date 2020/6/20 20:52
 * @Created by zengzg
 */
@Component
public class EngineThread {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncBeanFactory syncBeanFactory;

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public void createThread(EngineBaseBean engineBaseBean) {
        try {
            executor.submit(() -> {
                logger.info("------> createThread submit :{} -- type :{} <-------", engineBaseBean.getServiceName(),
                        engineBaseBean.getSyncType());
                IEngineService service = syncBeanFactory.getSyncBean(engineBaseBean.getServiceName());
                service.execute(engineBaseBean);


            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
        }

    }

}
