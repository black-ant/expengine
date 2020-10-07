package com.gang.etl.engine.api.to;

import lombok.Data;

/**
 * @Classname EngineBaseResponse
 * @Description TODO
 * @Date 2020/6/27 23:13
 * @Created by zengzg
 */
@Data
public class EngineBaseResponse {

    private Boolean status;

    private String code;

    private String backMsg;

    private String extInfo;
}
