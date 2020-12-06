package com.gang.etl.engine.conversion;

import com.alibaba.fastjson.JSONObject;
import com.gang.etl.datacenter.entity.SyncFieldInfo;
import com.gang.etl.engine.api.common.IEngineConversion;
import com.gang.etl.engine.api.exception.SyncConversionException;
import org.springframework.core.convert.ConversionException;

/**
 * @Classname AbstractEngineConversion
 * @Description TODO
 * @Date 2020/6/20 18:06
 * @Created by zengzg
 */
public abstract class AbstractEngineConversion implements IEngineConversion {

    /**
     * 无需 Conversion 转换
     */
    public static final String NONE_TYPE = "NONE";

    public JSONObject exchangeInfo(SyncFieldInfo syncFieldInfo, JSONObject sourceObject) {
        try {
            JSONObject backObject = new JSONObject();

            JSONObject exchangeField = JSONObject.parseObject(syncFieldInfo.getFieldBody());
            exchangeField.keySet().forEach(key -> {
                backObject.put(String.valueOf(exchangeField.get(key)), sourceObject.get(key));
            });
            return backObject;
        } catch (Exception e) {
            throw new SyncConversionException(syncFieldInfo.getSyncTypeCode(),
                    "AbstractEngineConversion exchangeInfo error : " + e.getMessage());
        }
    }
}
