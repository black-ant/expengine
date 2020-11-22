package com.gang.etl.engine.api.to;

import lombok.Data;

import java.util.List;

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

    private List backData;

    private String extInfo;
}
