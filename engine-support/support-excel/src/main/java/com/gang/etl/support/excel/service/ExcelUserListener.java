package com.gang.etl.support.excel.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.gang.etl.plugin.cache.api.impl.MemoryCacheManger;
import com.gang.etl.support.excel.to.ExcelUserTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ExcelListener
 * @Description TODO
 * @Date 2021/2/15 17:51
 * @Created by zengzg
 */
public class ExcelUserListener extends AnalysisEventListener<ExcelUserTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUserListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    MemoryCacheManger cacheManger = MemoryCacheManger.build();


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ExcelUserTO data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        try {
            cacheManger.push(data);
        } catch (Exception e) {
            LOGGER.error("E----> error :{} -- content :{}", e.getClass(), e.getMessage());
        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        LOGGER.info("所有数据解析完成！");
    }

}
