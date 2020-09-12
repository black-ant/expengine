package com.gang.etl.dingtalk.to;


import com.gang.etl.engine.api.annotation.SyncField;

import java.util.List;
import java.util.Map;

/**
 * @Classname DingtalkUserTO
 * @Description TODO
 * @Date 2020/9/12 20:26
 * @Created by zengzg
 */
public class DingTalkUserTO {

    private static final long serialVersionUID = -1385437685891326341L;
    /**
     * 员工唯一标识ID（不可修改），企业内必须唯一。 长度为1~64个字符，如果不传，服务器将自动生成一个userid
     */
    @SyncField(description = "员工唯一标识ID")
    private String userid;
    /**
     * 成员名称。 长度为1~64个字符
     */
    @SyncField(description = "成员名称")
    private String name;
    /**
     * 在对应的部门中的排序, Map结构的json字符串, key是部门的Id, value是人员在这个部门的排序值
     */
    @SyncField(description = "在对应的部门中的排序，Map结构的json字符串, key是部门的Id, value是人员在这个部门的排序值")
    private Map<String, Long> orderInDepts;
    /**
     * 数组类型，数组里面值为整型，成员所属部门id列表
     */
    @SyncField(description = "成员所属部门id列表")
    private List<Long> department;
    /**
     * 手机号码，企业内必须唯一，不可重复
     */
    @SyncField(description = "手机号码")
    private String mobile;
    /**
     * 职位信息。 长度为0~64个字符
     */
    @SyncField(description = "职位信息")
    private String position;
    /**
     * 分机号，长度为0~50个字符，企业内必须唯一，不可重复
     */
    @SyncField(description = "分机号")
    private String tel;
    /**
     * 办公地点，长度为0~50个字符
     */
    @SyncField(description = "办公地点")
    private String workPlace;
    /**
     * 备注，长度为0~1000个字符
     */
    @SyncField(description = "备注")
    private String remark;
    /**
     * 邮箱。长度为0~64个字符。企业内必须唯一，不可重复
     */
    @SyncField(description = "邮箱")
    private String email;
    /**
     * 员工的企业邮箱，员工的企业邮箱已开通，才能增加此字段， 否则会报错
     */
    @SyncField(description = "员工的企业邮箱")
    private String orgEmail;
    /**
     * 员工工号。对应显示到OA后台和客户端个人资料的工号栏目。 长度为0~64个字符
     */
    @SyncField(description = "员工工号")
    private String jobnumber;
    /**
     * 是否号码隐藏, true表示隐藏, false表示不隐藏 隐藏手机号后，手机号在个人资料页隐藏，但仍可对其发DING、发起钉钉免费商务电话。
     */
    @SyncField(description = "是否号码隐藏")
    private Boolean isHide = false;
    /**
     * 是否高管模式， true表示是， false表示不是。
     * 开启后，手机号码对所有员工隐藏。普通员工无法对其发DING、发起钉钉免费商务电话。高管之间不受影响。
     */
    @SyncField(description = "是否高管模式")
    private Boolean isSenior = false;
    /**
     * 扩展属性，可以设置多种属性 (但手机上最多只能显示10个扩展属性，
     * 具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置)
     */
    @SyncField(description = "扩展属性")
    private Map<String, Object> extattr;
}
