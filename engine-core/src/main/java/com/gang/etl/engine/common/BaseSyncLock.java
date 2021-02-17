package com.gang.etl.engine.common;

import com.gang.common.lib.exception.CommonException;
import com.gang.etl.engine.api.to.EngineBaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname BaseSyncFlow
 * @Description TODO
 * @Date 2020/10/7 19:58
 * @Created by zengzg
 */
public class BaseSyncLock {

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
    public void doLock(Object lock, EngineBaseBean engineBaseBean) {
        if (lock != null) {
            Integer lockNum = 0;
            do {
//                logger.info("------> doLock :{} <-------", engineBaseBean.getData());
                synchronized (lock) {
                    try {
//                        logger.info("------> no consumer , do lock wait <-------");
                        lock.wait(5000);
                        Thread.currentThread().sleep(0);
                    } catch (InterruptedException e) {
                        logger.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
                        throw new CommonException("doLock Error");
                    }
                }
                lockNum++;
            } while (engineBaseBean.getData() == null && lockNum < 3);
            logger.info("------> doLock :over no data wait <-------");
        }
    }

}
