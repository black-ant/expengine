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


}
