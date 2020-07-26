package com.gang.etl.ldap.logic;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import para.sdk.sync.ldap.to.ExchangeGlobalBookTO;
import para.sdk.sync.ldap.type.ExchangeType;
import para.sdk.sync.ldap.utils.ExchangeUtils;

/**
 * @Classname ExchangeAddressBookSource
 * @Description TODO
 * @Date 2020/3/4 16:28
 * @Created by zengzg
 */
@Component
public class ExchangeAddressBookSource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getGlobalBook(ExchangeConnect connect, ExchangeGlobalBookTO globalBookTO) {
        JSONArray array = addArraysItem(globalBookTO);
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_GET_GLOBE);
    }

    public String createGlobalBook(ExchangeConnect connect, ExchangeGlobalBookTO globalBookTO) {
        JSONArray array = addArraysItem(globalBookTO);
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_NEW_GLOBE);
    }

    public String updateGlobalBook(ExchangeConnect connect, ExchangeGlobalBookTO globalBookTO) {
        JSONArray array = addArraysItem(globalBookTO);
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_UPDATE_GLOBE);
    }

    public String deleteGlobalBook(ExchangeConnect connect, ExchangeGlobalBookTO globalBookTO) {
        JSONArray array = new JSONArray();
        array.add(ExchangeUtils.getTOItem("Identity", globalBookTO.getIdentity()));
        array.add(ExchangeUtils.getTOItem("Confirm:$false", ""));
        return ExchangeUtils.doOperation(connect, array, ExchangeType.OP_REMOVE_GLOBE);
    }

    /**
     * @param globalBookTO
     * @return
     */
    private JSONArray addArraysItem(ExchangeGlobalBookTO globalBookTO) {

        JSONArray array = new JSONArray();

        if (StringUtils.isNotBlank(globalBookTO.getIdentity())) {
            array.add(ExchangeUtils.getTOItem("Identity", globalBookTO.getIdentity()));
        }

        if (StringUtils.isNotBlank(globalBookTO.getName())) {
            array.add(ExchangeUtils.getTOItem("Name", globalBookTO.getName()));
        }

        if (StringUtils.isNotBlank(globalBookTO.getDomainController())) {
            array.add(ExchangeUtils.getTOItem("DomainController", globalBookTO.getDomainController()));
        }


        if (StringUtils.isNotBlank(globalBookTO.getRecipientFilter())) {
            array.add(ExchangeUtils.getTOItem("RecipientFilter", globalBookTO.getRecipientFilter()));
        }

        return array;
    }
}
