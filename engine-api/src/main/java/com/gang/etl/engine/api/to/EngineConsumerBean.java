package com.gang.etl.engine.api.to;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Classname BaseConsumerBean
 * @Description TODO
 * @Date 2020/6/11 22:28
 * @Created by zengzg
 */
public class EngineConsumerBean extends EngineBaseBean {

    public EngineConsumerBean() {
        setTypeOperation(OP_CONSUME);
    }

    public EngineConsumerBean(EngineProduceBean baseBean) {
        setTypeOperation(OP_CONSUME);
        setTypePart(baseBean.getTypePart());
    }

    public Object getSimpleValue() {
        List data = null != getData() ? getData() : null;
        return data.get(0);
    }

}
