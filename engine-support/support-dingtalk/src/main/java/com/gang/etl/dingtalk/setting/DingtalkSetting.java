package com.gang.etl.dingtalk.setting;

import com.gang.etl.dingtalk.constant.DingTalkConstant;
import com.gang.etl.engine.api.annotation.SyncTO;
import com.gang.etl.engine.api.bean.ISyncBaseTO;
import com.gang.etl.engine.api.common.IBaseSetting;

import java.util.List;

/**
 * @Classname DingtalkSetting
 * @Description TODO
 * @Date 2020/9/11 22:54
 * @Created by zengzg
 */
@SyncTO(name = "DingtalkSetting", type = "SETTING", app = DingTalkConstant.SYNC_TYPE)
public class DingtalkSetting implements IBaseSetting, ISyncBaseTO {

    private String corpid;
    private String appkey;
    private String appsecret;
    private Long agentId;

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Override
    public List<String> getSettingMap() {
        return null;
    }
}
