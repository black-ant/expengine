package com.gang.etl.engine.template;

import com.gang.common.lib.exception.CommonException;
import com.gang.etl.engine.template.ConsumerTemplate;
import com.gang.etl.engine.template.ProduceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname BaseSyncFlow
 * @Description TODO
 * @Date 2020/10/7 19:58
 * @Created by zengzg
 */
public class BaseSyncTemplate {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * notify consumer
     */
    public void doNotify(Object lock) {
        lock.notifyAll();
    }

    /**
     * wait produce
     *
     * @param lock
     */
    public void doLock(Object lock) {
        if (lock != null) {
            synchronized (lock) {
                try {
                    logger.info("------> no consumer , do lock wait <-------");
                    lock.wait(10000);
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
                    throw new CommonException("doLock Error");
                }
            }
        }
    }

}
