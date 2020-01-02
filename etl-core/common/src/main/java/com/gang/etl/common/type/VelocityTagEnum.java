package com.gang.etl.common.type;

/**
 * @Classname VelocityTagEnum
 * @Description TODO
 * @Date 2019/11/10 18:59
 * @Created by ant-black 1016930479@qq.com
 */
public enum VelocityTagEnum {

    IF("if", "#if", "if 语句"),
    SET("set", "#set", ""),
    ELSE("else", "#else", ""),
    END("end", "#end", ""),
    FOEEACH("foreach", "#foreach", ""),
    INCLUDE("include", "#include", ""),
    PARSE("parse", "#parse", ""),
    MACO("macro", "#macro", ""),
    // --> 表示类
    A_OBJECT("a object", "$", ""),
    SINGLE_LINE_COMMENT("single line comment", "##", "单行注释"),
    MULTI_LINE_COMMENT("Multi-line comment", "#*", "多行注释");

    private String type;
    private String code;
    private String desc;

    VelocityTagEnum(String type, String code, String desc) {
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
