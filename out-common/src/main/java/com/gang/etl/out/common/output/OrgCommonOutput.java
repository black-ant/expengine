package com.gang.etl.out.common.output;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.out.common.logic.SyncInvoke;
import com.gang.ext.sdk.workwechat.to.WorkWechatConfig;
import com.gang.sdk.api.to.SyncConfig;
import com.gang.sdk.api.type.SyncOperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname OrgOutput
 * @Description TODO
 * @Date 2020/1/12 22:53
 * @Created by zengzg
 */
@Component
public class OrgCommonOutput {

    @Autowired
    private SyncInvoke syncInvoke;

    public String createOrg() {
        String claaName = "com.gang.ext.sdk.workwechat.logic.OrgLogic";
        syncInvoke.invoke(claaName, SyncOperationType.CREATE, getConfig());
        return "ok";
    }

    public Map<String, Object> getConfig() {
        Map syncConfig = new HashMap();
        syncConfig.put("appId", "ww72f872df46c2b36f");
        syncConfig.put("appSecret", "UqY3axjUkhzqd37VNUrsO9BeMXizZksMhCHZVm32dZs");
        return syncConfig;
    }
}
