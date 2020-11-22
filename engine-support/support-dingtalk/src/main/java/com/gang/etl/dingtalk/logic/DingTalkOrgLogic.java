package com.gang.etl.dingtalk.logic;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.gang.etl.dingtalk.setting.DingtalkSetting;
import com.gang.etl.engine.api.query.BaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DingTalkOrgLogic extends DingTalkBaseLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @return
     */
    public List getOrgList(DingtalkSetting setting, BaseQuery query) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(query.getFilter());
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, getAccessToken(setting));
        return response.getDepartment();
    }
}
