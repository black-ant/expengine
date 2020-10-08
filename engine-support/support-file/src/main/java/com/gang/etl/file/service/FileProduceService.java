package com.gang.etl.file.service;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.file.setting.SyncFileSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname FileProduce
 * @Description TODO
 * @Date 2020/6/20 12:52
 * @Created by zengzg
 */
@Component
public class FileProduceService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileIOLogic fileIOLogic;


    /**
     * read file from path
     *
     * @param consumerBean
     * @return
     */
    @Override
    public EngineProduceBean execute(EngineProduceBean consumerBean) {
        logger.info("------> this is in file produce :{}<-------", consumerBean.getConfig());
        SyncFileSetting fileSetting = JSONObject.parseObject(consumerBean.getConfig(), SyncFileSetting.class);
        logger.info("------> SyncFileSetting :{} <-------", fileSetting);
        consumerBean.setSimpleValue(fileIOLogic.readFile(fileSetting.getRootPath()));
        return consumerBean;
    }
}
