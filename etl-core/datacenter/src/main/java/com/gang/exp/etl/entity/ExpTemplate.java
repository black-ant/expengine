package com.gang.exp.etl.entity;

import java.io.Serializable;
import java.util.Date;

public class ExpTemplate implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.template_title
     *
     * @mbg.generated
     */
    private String templateTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.template_body
     *
     * @mbg.generated
     */
    private String templateBody;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.template_desc
     *
     * @mbg.generated
     */
    private String templateDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.template_module
     *
     * @mbg.generated
     */
    private String templateModule;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.template_type
     *
     * @mbg.generated
     */
    private String templateType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.create_id
     *
     * @mbg.generated
     */
    private Integer createId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.update_id
     *
     * @mbg.generated
     */
    private Integer updateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exp_template.update_Date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table exp_template
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.id
     *
     * @return the value of exp_template.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.id
     *
     * @param id the value for exp_template.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.template_title
     *
     * @return the value of exp_template.template_title
     *
     * @mbg.generated
     */
    public String getTemplateTitle() {
        return templateTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.template_title
     *
     * @param templateTitle the value for exp_template.template_title
     *
     * @mbg.generated
     */
    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle == null ? null : templateTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.template_body
     *
     * @return the value of exp_template.template_body
     *
     * @mbg.generated
     */
    public String getTemplateBody() {
        return templateBody;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.template_body
     *
     * @param templateBody the value for exp_template.template_body
     *
     * @mbg.generated
     */
    public void setTemplateBody(String templateBody) {
        this.templateBody = templateBody == null ? null : templateBody.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.template_desc
     *
     * @return the value of exp_template.template_desc
     *
     * @mbg.generated
     */
    public String getTemplateDesc() {
        return templateDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.template_desc
     *
     * @param templateDesc the value for exp_template.template_desc
     *
     * @mbg.generated
     */
    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc == null ? null : templateDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.template_module
     *
     * @return the value of exp_template.template_module
     *
     * @mbg.generated
     */
    public String getTemplateModule() {
        return templateModule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.template_module
     *
     * @param templateModule the value for exp_template.template_module
     *
     * @mbg.generated
     */
    public void setTemplateModule(String templateModule) {
        this.templateModule = templateModule == null ? null : templateModule.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.template_type
     *
     * @return the value of exp_template.template_type
     *
     * @mbg.generated
     */
    public String getTemplateType() {
        return templateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.template_type
     *
     * @param templateType the value for exp_template.template_type
     *
     * @mbg.generated
     */
    public void setTemplateType(String templateType) {
        this.templateType = templateType == null ? null : templateType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.create_id
     *
     * @return the value of exp_template.create_id
     *
     * @mbg.generated
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.create_id
     *
     * @param createId the value for exp_template.create_id
     *
     * @mbg.generated
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.update_id
     *
     * @return the value of exp_template.update_id
     *
     * @mbg.generated
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.update_id
     *
     * @param updateId the value for exp_template.update_id
     *
     * @mbg.generated
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.create_date
     *
     * @return the value of exp_template.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.create_date
     *
     * @param createDate the value for exp_template.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exp_template.update_Date
     *
     * @return the value of exp_template.update_Date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exp_template.update_Date
     *
     * @param updateDate the value for exp_template.update_Date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exp_template
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateTitle=").append(templateTitle);
        sb.append(", templateBody=").append(templateBody);
        sb.append(", templateDesc=").append(templateDesc);
        sb.append(", templateModule=").append(templateModule);
        sb.append(", templateType=").append(templateType);
        sb.append(", createId=").append(createId);
        sb.append(", updateId=").append(updateId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}