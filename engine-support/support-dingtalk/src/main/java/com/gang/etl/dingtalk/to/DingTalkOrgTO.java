package com.gang.etl.dingtalk.to;

import lombok.Data;

/**
 * @Classname DingTalkOrgTO
 * @Description TODO
 * @Date 2020/11/22 13:32
 * @Created by zengzg
 */
@Data
public class DingTalkOrgTO extends BaseDingTalkTO {

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
