package com.gang.etl.engine.database.to;

/**
 * @Classname DatabaseGroupTO
 * @Description TODO
 * @Date 2021/2/14 13:56
 * @Created by zengzg
 */

public class DatabaseGroupTO {

    private String id;

    private String groupName;

    private String groupType;

    private String orgId;

    private String orgRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgRole() {
        return orgRole;
    }

    public void setOrgRole(String orgRole) {
        this.orgRole = orgRole;
    }
}
