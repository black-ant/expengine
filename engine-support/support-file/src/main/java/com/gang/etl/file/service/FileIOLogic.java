package com.gang.etl.file.service;


import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.gang.common.lib.utils.FileUtils;
import com.gang.etl.file.setting.SyncFileSetting;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class FileIOLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 递归处理 File 文件
     * 1 fileName -> contentCode (文件名避免了特殊字符)
     *
     * @param filePath
     * @return
     */
    public Boolean readFileRecursion(String filePath, SyncFileSetting fileSetting) {

        logger.info("------> pullLogic :{} <-------", filePath);

        // Get folder Map Suffix
        Map<String, File> files = FileUtils.getFilesMapNoSuffix(filePath);
        if (!CollectionUtils.isEmpty(files)) {

            File settingFile = files.get(fileSetting.getRootPath());

            String config = FileUtil.isEmpty(settingFile) ? "" : FileUtil.readString(settingFile, "UTF-8");

            // Get files
            files.keySet().forEach(item -> {
                File fileItem = files.get(item);

                // If it is a configuration file : BLOG.md
                if (checkFile(fileItem)) {

                    // If it is a folder, recursive query
                    if (FileUtil.isDirectory(fileItem)) {
                        readFileRecursion(fileItem.getPath(), fileSetting);
                    } else {
                        logger.info("------>  <-------");
                    }
                } else {
                    logger.info("------> skip this file <-------");
                }
            });
        }
        return Boolean.TRUE;
    }

    /**
     * 过滤文本
     *
     * @return
     */
    public Boolean checkFile(File file) {
        String fileName = file.getName();
        if (fileName.contains(".git") || fileName.contains("BLOG.md")) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    public void readLine(String path) {
        File logFile = new File(path);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile), StandardCharsets.UTF_8));
            if (reader != null) {

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        logger.info("------> msg is :{} <-------", sb.toString());
    }

    public void writeFile() {

    }

    /**
     * @param path
     * @return
     */
    public String readFile(String path) {
        return FileUtil.readString(path, "UTF-8");
    }


    /**
     * 查询文件是否存在 , 仅通过路径
     */
    public Boolean checkFileExists(File file) {
        return file.exists();
    }

    /**
     * 查询文件是否存在 , 仅通过路径
     */
    public Boolean checkFileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 查询文件是否存在 ，通过上下文 及文件名（全限定）
     */
    public Boolean checkFileExists(String parent, String name) {
        return new File(parent, name).exists();
    }

    /**
     * 构建简单log
     *
     * @return
     */
    public String logDetail() {
        StringBuffer stringBuffer = new StringBuffer();
        //        stringBuffer.append(DateUtil.formatDateTime(new Date())).append(" ")
        //                .append("[" + this.getClass().getSimpleName() + "#" + this.getClass().getPackage() + "]").append("-");

        return stringBuffer.toString();
    }
}
