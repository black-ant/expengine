package com.gang.etl.server.logic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class FileIOLogic extends AbstractLogic {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


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
