package com.gang.etl.engine.api.to;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SyncOverBean
 * @Description TODO
 * @Date 2020/10/7 21:40
 * @Created by zengzg
 */
@Data
public class SyncStatusTO {

    /**
     * 同步状态
     */
    private Boolean status = Boolean.FALSE;

    /**
     * 成功情况
     */
    private Integer successNum;
    private Map<String, Integer> success = new HashMap<>();

    /**
     * 失败情况
     */
    private Integer failureNum;
    private Map<String, Integer> failure = new HashMap<>();

    /**
     * 返回信息
     */
    private String info;

    /**
     * 最后同步时间
     */
    private Date lastDate;

    /**
     * 更新状态
     */
    private Boolean produceOver = Boolean.FALSE;
}
