package com.gang.etl.engine.api.to;

import com.alibaba.fastjson.JSONObject;

/**
 * @Classname BaseProduceBean
 * @Description TODO
 * @Date 2020/6/11 22:28
 * @Created by zengzg
 */
public class EngineProduceBean extends EngineBaseBean<JSONObject> {

    public EngineProduceBean() {
        setTypeOperation(OP_PRODUCE);
    }

    public Object getSimpleValue() {
        return null != getData() ? getData().get(DEFAULT_DATA) : null;
    }

    public void setSimpleValue(Object value) {
        JSONObject values = null != getData() ? getData() : new JSONObject();
        values.put(DEFAULT_DATA, value);
        setData(values);
    }

}
