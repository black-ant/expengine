package com.gang.etl.engine.conversion;

import com.gang.etl.datacenter.dao.SyncFiledInfoDAO;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.engine.api.bean.EngineConsumerBean;
import com.gang.etl.engine.api.bean.EngineProduceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        SyncFieldInfo syncInfo = syncFiledInfoDAO.selectBySyncType(produceBean.getSyncType() + "_" + consumerBean.getSyncType());
        logger.debug("------> DictionaryConversion conversion :{} <-------", syncInfo.getSyncType());

        consumerBean.setData(exchangeInfo(syncInfo, produceBean.getData()));
    }
}
