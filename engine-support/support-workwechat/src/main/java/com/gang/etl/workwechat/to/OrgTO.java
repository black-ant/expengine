package com.gang.etl.workwechat.to;


/**
 * @Classname OrgTO
 * @Description TODO
 * @Date 2019/12/25 21:15
 * @Created by zengzg
 */
@Data
@SyncTO(type = "ORG", app = "WORK_WECHAT", name = "企业微信")
public class OrgTO extends SyncBaseBean {

    private String name;

    private String name_en;

    private Integer parentid;

    private Integer order;

    private String id;
}
