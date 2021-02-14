package com.gang.etl.engine.database.to;

/**
 * @Classname DatabaseUserTO
 * @Description TODO
 * @Date 2021/2/14 13:55
 * @Created by zengzg
 */
public class DatabaseUserTO {

    private String id;

    private String userName;

    private String realNaem;

    private String groudId;

    private String orgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealNaem() {
        return realNaem;
    }

    public void setRealNaem(String realNaem) {
        this.realNaem = realNaem;
    }

    public String getGroudId() {
        return groudId;
    }

    public void setGroudId(String groudId) {
        this.groudId = groudId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
