package com.gang.etl.engine.conversion;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.dao.SyncFiledInfoDAO;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.engine.api.to.EngineConsumerBean;
import com.gang.etl.engine.api.to.EngineProduceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @Classname DictionaryConversion
 * @Description TODO
 * @Date 2020/6/20 18:05
 * @Created by zengzg
 */
@Component
public class DictionaryConversion extends AbstractEngineConversion {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncFiledInfoDAO syncFiledInfoDAO;

    @Override
    public void conversion(EngineProduceBean produceBean, EngineConsumerBean consumerBean) {

        SyncFieldInfo syncInfo =
                syncFiledInfoDAO.selectBySyncType(produceBean.getSyncType() + "_" + consumerBean.getSyncType());
        logger.info("------> DictionaryConversion conversion :{} -- {} <-------", syncInfo.getSyncTypeCode(),
                produceBean.getResponse());
        if (produceBean != null && produceBean.getResponse() != null) {
            List exchangeList = produceBean.getResponse().getBackData();

            if (syncInfo == null || NONE_TYPE.equals(syncInfo.getTargetCode())) {
                consumerBean.setData(exchangeList);
            } else {
                List overList = new LinkedList();
                exchangeList.forEach(item -> {
                    String dataStr = JSONObject.toJSONString(item);
                    overList.add(exchangeInfo(syncInfo, JSONObject.parseObject(dataStr)));
                });
                consumerBean.setData(overList);
            }
        }


    }
}
