package com.gang.etl.support.excel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.gang.common.lib.utils.FileUtils;
import com.gang.etl.support.excel.service.ExcelUserListener;
import com.gang.etl.support.excel.to.ExcelUserTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname EasyExcelUtils
 * @Description TODO
 * @Date 2021/2/15 17:34
 * @Created by zengzg
 */
@Service
public class EasyExcelManager {


    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelUserTO}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ExcelUserListener}
     * <p>
     * 3. 直接读即可
     */
    public void simpleRead(String filePath) {
        EasyExcel.read(filePath, ExcelUserTO.class, new ExcelUserListener()).sheet(1).doRead();
    }


    public List<ExcelUserTO> syncRead(String filePath) {
        return EasyExcel.read(filePath).head(ExcelUserTO.class).sheet(1).doReadSync();
    }


}
