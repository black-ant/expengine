package com.gang.etl.engine.database.config;

import com.gang.etl.engine.api.annotation.SyncTO;
import com.gang.etl.engine.database.type.DatabaseConstant;

/**
 * @Classname DatabaseSetting
 * @Description TODO
 * @Date 2021/2/14 14:24
 * @Created by zengzg
 */
@SyncTO(name = "DatabaseSetting", type = "SETTING", app = DatabaseConstant.SYNC_TYPE)
public class DatabaseSetting {

    private String url;
    private String username;
    private String password;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
