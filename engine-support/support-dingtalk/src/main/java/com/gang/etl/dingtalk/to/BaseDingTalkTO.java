package com.gang.etl.dingtalk.to;

/**
 * @Classname BaseDingTalkTO
 * @Description TODO
 * @Date 2020/11/22 12:20
 * @Created by zengzg
 */
public class BaseDingTalkTO {

    protected String errcode;

    protected String errmsg;

    protected Integer id;

    protected String name;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
