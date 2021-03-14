package com.gang.etl.engine.api.request;

import java.util.List;
import java.util.Map;

/**
 * @Classname SyncCustomRequest
 * @Description TODO
 * @Date 2020/10/7 17:34
 * @Created by zengzg
 */
public class SyncCustomRequest {

    private String businessCode;

    private String syncBeanCode;

    private String syncSettingCode;

    private List<Map<String, Object>> data;

    private String fieldInfoCode;


    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String getFieldInfoCode() {
        return fieldInfoCode;
    }

    public void setFieldInfoCode(String fieldInfoCode) {
        this.fieldInfoCode = fieldInfoCode;
    }

    public String getSyncBeanCode() {
        return syncBeanCode;
    }

    public void setSyncBeanCode(String syncBeanCode) {
        this.syncBeanCode = syncBeanCode;
    }

    public String getSyncSettingCode() {
        return syncSettingCode;
    }

    public void setSyncSettingCode(String syncSettingCode) {
        this.syncSettingCode = syncSettingCode;
    }
}
