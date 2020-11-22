package com.gang.etl.dingtalk.to;


import com.gang.etl.engine.api.annotation.SyncField;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Classname DingtalkUserTO
 * @Description TODO
 * @Created by zengzg
 */
@Data
public class DingTalkUserTO extends BaseDingTalkTO {

    private static final long serialVersionUID = -1385437685891326341L;

    @SyncField(description = "员工唯一标识ID")
    private String userid;

    @SyncField(description = "成员名称")
    private String name;

    @SyncField(description = "成员名称")
    private String unionid;

    @SyncField(description = "成员名称")
    private Boolean isBoss;

    @SyncField(description = "成员名称")
    private Boolean isSenior;
}
