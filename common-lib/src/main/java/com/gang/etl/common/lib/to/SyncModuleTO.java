package com.gang.etl.common.lib.to;

import lombok.Data;

/**
 * @Classname SyncTO
 * @Description TODO
 * @Date 2020/2/2 19:00
 * @Created by zengzg
 */
@Data
public class SyncModuleTO {

    private SyncPartTypeEnum partType;

    private SyncTypeEnum opType;

    private String syncBody;

}
