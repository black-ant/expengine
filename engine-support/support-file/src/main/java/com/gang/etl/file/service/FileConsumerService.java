package com.gang.etl.file.service;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.common.IComsumerService;
import com.gang.etl.file.setting.SyncFileSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname FileConsumerService
 * @Description TODO
 * @Date 2020/9/10 16:44
 * @Created by zengzg
 */
@Component
public class FileConsumerService implements IComsumerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public EngineConsumerBean execute(EngineConsumerBean consumerBean) {
        logger.info("------> FileConsumerService in {}  <-------", consumerBean);
        SyncFileSetting fileSetting = JSONObject.parseObject(consumerBean.getConfig(), SyncFileSetting.class);
        FileUtil.writeString(String.valueOf(consumerBean.getSimpleValue()), fileSetting.getRootPath(), "UTF-8");
        return consumerBean;
    }
}
