package com.gang.etl.ldap.logic;

import com.alibaba.fastjson.JSONArray;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.common.sync.exception.SyncException;
import para.sdk.sync.ldap.to.ExchangeTO;
import para.sdk.sync.ldap.type.ExchangeType;
import para.sdk.sync.ldap.utils.ExchangeUtils;

/**
 * @Classname ExchangeSource
 * @Description TODO
 * @Date 2020/2/28 16:42
 * @Created by zengzg
 */
@Component
public class ExchangeOperationSource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 添加邮箱
     */
    public String enableMailBox(ExchangeConnect connect, ExchangeTO exchangeTO) {
        logger.info("------> this is in enableMailBox <-------");
        return ExchangeUtils.doOperationEasy(connect, exchangeTO.getIdentity(), ExchangeType.OP_ENABLE);
    }

    /**
     * 禁用邮箱
     */
    public String disableMailBox(ExchangeConnect connect, ExchangeTO exchangeTO) {
        logger.info("------> this is in enableMailBox <-------");
        return ExchangeUtils.doOperationEasy(connect, exchangeTO.getIdentity(), ExchangeType.OP_DISABLE);
    }

    /**
     * 添加邮箱联系人
     */
    public String enableMailUser(ExchangeConnect connect, ExchangeTO exchangeTO) {
        logger.info("------> this is in enableMailUser <-------");

        JSONArray array = addArraysItem(exchangeTO);
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_ENABLE_USER);
    }

    /**
     * 禁用邮箱联系人
     */
    public String disableMailUser(ExchangeConnect connect, ExchangeTO exchangeTO) {
        logger.info("------> this is in disableMailUser <-------");

        JSONArray array = new JSONArray();
        array.add(ExchangeUtils.getTOItem("Identity", exchangeTO.getIdentity()));
        array.add(ExchangeUtils.getTOItem("Confirm:$false", ""));

        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_DISABLE_USER);
    }

    /**
     * 修改邮箱属性
     */
    public String setMailBox(ExchangeConnect connect, ExchangeTO exchangeTO) {
        logger.info("------> this is in setMailBox <-------");
        JSONArray array = addArraysItem(exchangeTO);
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_SET_EMAILBOX);
    }

    /**
     * 修改邮箱用户属性
     */
    public String setMailUser(ExchangeConnect connect, ExchangeTO exchangeTO) {
        logger.info("------> this is in setMailUser <-------");
        JSONArray array = addArraysItem(exchangeTO);
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_SET_USER);
    }


    /**
     * 获取JSONArrays 数组
     *
     * @param exchangeTO
     * @return
     */
    public JSONArray addArraysItem(ExchangeTO exchangeTO) {
        JSONArray array = new JSONArray();

        if (StringUtils.isBlank(exchangeTO.getIdentity())) {
            throw new SyncException("Exchange Identity is Empty");
        }
        array.add(ExchangeUtils.getTOItem("Identity", exchangeTO.getIdentity()));

        if (StringUtils.isNotBlank(exchangeTO.getEmailAddress())) {
            array.add(ExchangeUtils.getTOItem("ExternalEmailAddress", exchangeTO.getEmailAddress()));
        }

        if (StringUtils.isNotBlank(exchangeTO.getAliasName())) {
            array.add(ExchangeUtils.getTOItem("Alias", exchangeTO.getAliasName()));
        }

        if (StringUtils.isNotBlank(exchangeTO.getDisplayName())) {
            array.add(ExchangeUtils.getTOItem("DisplayName", exchangeTO.getDisplayName()));
        }


        return array;
    }

    /**
     * 执行 Shell 该方法为本地执行 , 待扩展
     *
     * @return
     */
    public String doShell() {

        logger.info("------> this is in do shell <-------");

        PowerShell powerShell = PowerShell.openSession("powershell.exe");
        //        powerShell.executeCommand()

        powerShell.executeCommand("Add-PSSnapin Microsoft.Exchange*");

        logger.info("------> this is add 管理单元 <-------");
        PowerShellResponse response1 = powerShell.executeCommand("Get-MailUser");
        logger.info("------> this is response111 :{} <-------", response1.getCommandOutput());

        PowerShellResponse response = powerShell.executeCommand("New-DistributionGroup -Name \"ShellDGroup1\" "
                + "-Members"
                + " ShellAccount@wdhacpoc.com.cn");
        powerShell.close();

        logger.info("------> this is response :{} <-------", response.getCommandOutput());
        return response.getCommandOutput();
    }

}
