package com.gang.etl.common.output.sdk;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Classname OutputConfig
 * @Description TODO
 * @Date 2019/12/14 19:42
 * @Created by zengzg
 */
@Data
public class OutputConfig {

    private String outPath;

    private JSONObject connetInfo;

}
