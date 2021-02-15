package com.gang.etl.support.excel.setting;

import com.gang.etl.engine.api.annotation.SyncTO;
import com.gang.etl.engine.api.bean.ISyncBaseTO;
import com.gang.etl.support.excel.type.ExcelConstant;

/**
 * @Classname ExcelSetting
 * @Description TODO
 * @Date 2021/2/14 15:14
 * @Created by zengzg
 */
@SyncTO(name = "ExcelSetting", type = "SETTING", app = ExcelConstant.SYNC_TYPE)
public class ExcelSetting implements ISyncBaseTO {

    private String excelPath;

    public ExcelSetting() {
    }

    public ExcelSetting(String excelPath) {
        this.excelPath = excelPath;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }
}
