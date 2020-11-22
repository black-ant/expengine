package com.gang.etl.dingtalk.logic;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.gang.etl.dingtalk.setting.DingtalkSetting;
import com.gang.etl.dingtalk.type.DingTalkRestAPI;
import com.gang.etl.engine.api.exception.SyncErrorEnum;
import com.gang.etl.engine.api.exception.SyncException;
import com.gang.etl.engine.api.service.BaseSupportLogic;
import com.taobao.api.ApiException;

/**
 * @Classname DingTalkBaseLogic
 * @Description TODO
 * @Date 2020/11/22 13:08
 * @Created by zengzg
 */
public class DingTalkBaseLogic extends BaseSupportLogic {

    public String getAccessToken(DingtalkSetting setting) {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(DingTalkRestAPI.GET_TOKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(setting.getAppkey());
            request.setAppsecret(setting.getAppsecret());
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            if (0 == response.getErrcode()) {
                return response.getAccessToken();
            } else {
                throw new SyncException(SyncErrorEnum.SYNC_APP_AUTH_ERROR);
            }
        } catch (Exception e) {
            throw new SyncException(SyncErrorEnum.SYNC_APP_AUTH_ERROR);
        }
    }
}
