package com.gang.etl.engine.api.to;

import com.alibaba.fastjson.JSONObject;

/**
 * @Classname BaseConsumerBean
 * @Description TODO
 * @Date 2020/6/11 22:28
 * @Created by zengzg
 */
public class EngineConsumerBean extends EngineBaseBean<JSONObject> {

    public EngineConsumerBean() {
        setTypeOperation(OP_CONSUME);
    }

    public EngineConsumerBean(EngineProduceBean baseBean, String config) {
        setTypeOperation(OP_CONSUME);
        setData(baseBean.getData());
        setConfig(config);
        setTypePart(baseBean.getTypePart());
    }
}
