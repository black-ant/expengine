package com.gang.etl.dingtalk.service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListIdsRequest;
import com.dingtalk.api.response.OapiDepartmentListIdsResponse;
import com.gang.etl.dingtalk.constant.DingTalkConstant;
import com.gang.etl.dingtalk.logic.DingTalkOrgLogic;
import com.gang.etl.dingtalk.setting.DingtalkSetting;
import com.gang.etl.dingtalk.to.DingTalkQuery;
import com.gang.etl.engine.api.common.IProduceService;
import com.gang.etl.engine.api.exception.SyncErrorEnum;
import com.gang.etl.engine.api.exception.SyncException;
import com.gang.etl.engine.api.to.EngineBaseResponse;
import com.gang.etl.engine.api.to.EngineProduceBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname DingTalkProduceService
 * @Description TODO
 * @Date 2020/7/26 21:42
 * @Created by zengzg
 */
@Component
public class DingTalkProduceService implements IProduceService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DingTalkOrgLogic orgLogic;

    @Override
    public EngineProduceBean execute(EngineProduceBean produceBean) {

        EngineBaseResponse response = new EngineBaseResponse();

        try {
            List list = orgLogic.getOrgList(getSeting(produceBean), produceBean.getQuery());
            response.setBackData(list);
        } catch (Exception e) {
            response.setStatus(Boolean.FALSE);
        }
        produceBean.setResponse(response);
        return produceBean;
    }

    public DingtalkSetting getSeting(EngineProduceBean produceBean) {
        if (StringUtils.isNotEmpty(produceBean.getConfig())) {
            try {
                return JSONObject.parseObject(produceBean.getConfig(), DingtalkSetting.class);
            } catch (Exception e) {
                throw new SyncException(SyncErrorEnum.SYNC_SETTING_BUILD_ERROR, DingTalkConstant.SYNC_TYPE);
            }
        } else {
            throw new SyncException(SyncErrorEnum.SYNC_SETTING_NOT_FOUND, DingTalkConstant.SYNC_TYPE);
        }
    }

}
