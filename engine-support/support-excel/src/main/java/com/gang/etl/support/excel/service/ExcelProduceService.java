package com.gang.etl.support.excel.service;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.engine.api.to.EngineProduceBean;
import com.gang.etl.support.excel.setting.ExcelSetting;
import com.gang.etl.support.excel.to.ExcelUserTO;
import com.gang.etl.support.excel.utils.EasyExcelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname ProduceService
 * @Description 基于数据库的配置方式
 * @Date 2021/2/14 13:41
 * @Created by zengzg
 */
@Service
public class ExcelProduceService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EasyExcelManager easyExcelManager;

    @Override
    public EngineProduceBean execute(EngineProduceBean produceBean) {
        logger.info("------> this is in engine produce <-------");
        ExcelSetting setting = JSONObject.parseObject(produceBean.getSetting(), ExcelSetting.class);
        List<ExcelUserTO> excelUserTOS = easyExcelManager.syncRead(setting.getExcelPath());
        produceBean.pullAllValue(excelUserTOS);
        return null;
    }
}
