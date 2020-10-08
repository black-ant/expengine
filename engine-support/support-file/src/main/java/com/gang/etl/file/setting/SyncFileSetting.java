package com.gang.etl.file.setting;

import com.gang.common.lib.annotation.AnnoField;
import com.gang.etl.engine.api.to.EngineBaseSetting;
import lombok.Data;

/**
 * @Classname SyncFileSetting
 * @Description TODO
 * @Date 2020/6/27 21:37
 * @Created by zengzg
 */
public class SyncFileSetting extends EngineBaseSetting {

    @AnnoField(name = "根路径", nullable = false)
    private String rootPath;

    @AnnoField(name = "前缀")
    private String prefix;

    @AnnoField(name = "后缀")
    private String suffix;

    @AnnoField(name = "文件类型", nullable = false)
    private String fileType;

    @AnnoField(name = "自动分割")
    private Boolean segmentation;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Boolean getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(Boolean segmentation) {
        this.segmentation = segmentation;
    }

    @Override
    public String toString() {
        return "SyncFileSetting{" +
                "rootPath='" + rootPath + '\'' +
                ", prefix='" + prefix + '\'' +
                ", suffix='" + suffix + '\'' +
                ", fileType='" + fileType + '\'' +
                ", segmentation=" + segmentation +
                '}';
    }
}
