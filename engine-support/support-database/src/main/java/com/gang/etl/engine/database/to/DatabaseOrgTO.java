package com.gang.etl.engine.database.to;

import com.gang.etl.engine.api.annotation.SyncTO;
import com.gang.etl.engine.api.bean.ISyncBaseTO;
import com.gang.etl.engine.database.type.DatabaseConstant;

/**
 * @Classname DatabaseOrgTO
 * @Description TODO
 * @Date 2021/2/14 13:58
 * @Created by zengzg
 */
@SyncTO(name = "DatabaseOrgTO", type = "TO", app = DatabaseConstant.SYNC_TYPE)
public class DatabaseOrgTO implements ISyncBaseTO {

    private String id;

    private String orgName;

    private String orgType;

    private String orgRole;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgRole() {
        return orgRole;
    }

    public void setOrgRole(String orgRole) {
        this.orgRole = orgRole;
    }
}
