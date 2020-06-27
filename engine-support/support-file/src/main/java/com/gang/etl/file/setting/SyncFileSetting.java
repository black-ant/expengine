package com.gang.etl.file.setting;

import com.gang.common.lib.annotation.AnnoField;
import com.gang.etl.engine.api.bean.EngineBaseSetting;
import lombok.Data;

/**
 * @Classname SyncFileSetting
 * @Description TODO
 * @Date 2020/6/27 21:37
 * @Created by zengzg
 */
@Data
public class SyncFileSetting extends EngineBaseSetting {

    @AnnoField(name = "根路径", nullable = false)
    private String rootPath;

    @AnnoField(name = "前缀")
    private String prefix;

    @AnnoField(name = "后缀")
    private String suffix;

    @AnnoField(name = "文件类型", nullable = false)
    private String fileType;
}
