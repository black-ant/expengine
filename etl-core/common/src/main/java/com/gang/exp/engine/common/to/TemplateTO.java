package com.gang.exp.engine.common.to;

import lombok.Data;

import java.util.Date;

/**
 * @Classname TemplateTO
 * @Description TODO
 * @Date 2019/11/6 22:00
 * @Created by ant-black 1016930479@qq.com
 */
@Data
public class TemplateTO extends AbstractTO {

    private Integer id;

    private String templateTitle;

    private String templateBody;

    private String templateDesc;

    private String templateType;

    private Integer createId;

    private Integer updateId;

    private Date createDate;

    private Date updateDate;

}
