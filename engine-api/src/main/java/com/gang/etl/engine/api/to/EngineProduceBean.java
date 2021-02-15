package com.gang.etl.engine.api.to;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;

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

    public void setSimpleValue(Object value) {

        List backList = null != getData() ? getData() : new LinkedList();
        backList.add(value);

        EngineBaseResponse response = new EngineBaseResponse();
        response.setBackData(backList);
        setResponse(response);
    }

    public void pullAllValue(List datas) {
        EngineBaseResponse response = new EngineBaseResponse();
        response.setBackData(datas);
        setResponse(response);
    }

}
