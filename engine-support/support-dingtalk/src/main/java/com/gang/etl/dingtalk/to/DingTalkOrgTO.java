package com.gang.etl.dingtalk.to;

import com.gang.etl.dingtalk.constant.DingTalkConstant;
import com.gang.etl.engine.api.annotation.SyncTO;
import com.gang.etl.engine.api.bean.ISyncBaseTO;
import lombok.Data;

/**
 * @Classname DingTalkOrgTO
 * @Description TODO
 * @Date 2020/11/22 13:32
 * @Created by zengzg
 */
@Data
@SyncTO(name = "DingTalkOrgTO", type = "TO", app = DingTalkConstant.SYNC_TYPE)
public class DingTalkOrgTO extends BaseDingTalkTO implements ISyncBaseTO {

    private Integer parentid;

    private Integer order;

    private Boolean createDeptGroup;

    private Boolean autoAddUser;

    private Boolean deptHiding;

    private Boolean outerDept;

    private String deptPermits;

    private String userPermits;

    private String outerPermitDepts;

    private String outerPermitUsers;

    private String orgDeptOwner;

    private String deptManagerUseridList;

    private String sourceIdentifier;

}
