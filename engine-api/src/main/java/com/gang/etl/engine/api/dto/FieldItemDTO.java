package com.gang.etl.engine.api.dto;

/**
 * @Classname FieldItemDTO
 * @Description TODO
 * @Date 2020/11/29 15:17
 * @Created by zengzg
 */
public class FieldItemDTO {

    private String key;
    private String value;
    private String desc;
    private String length;
    private String other;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
