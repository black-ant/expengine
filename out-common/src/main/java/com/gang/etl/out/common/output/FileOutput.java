package com.gang.etl.out.common.output;

import com.gang.common.lib.utils.DateUtil;
import com.gang.etl.common.lib.api.OutputConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;

/**
 * @Classname FileOutput
 * @Description TODO
 * @Date 2019/12/14 21:06
 * @Created by zengzg
 */
@Component
public class FileOutput extends AbstractOut<String, String> {

    private static Logger logger = LoggerFactory.getLogger(FileOutput.class);

    public String output(String source, OutputConfig outputConfig) {
        writeFile(outputConfig.getOutPath(), source);
        return "write ok";
    }

    public void run() {
        logger.info("------> file is exists :{} <-- -----", checkFileExists("D:\\java\\workspace\\git\\case\\case Java Other\\case Java IO\\doc\\test.txt"));
        logger.info("------> file is exists :{} <-- -----", checkFileExists("D:\\java\\workspace\\git\\case\\case Java Other\\case Java IO\\doc", "test.txt"));
        //        writeFile("D:\\java\\workspace\\git\\case\\case Java Other\\case Java IO\\doc\\test.txt");
        //        readFile("D:\\java\\workspace\\git\\case\\case Java Other\\case Java IO\\doc\\test.txt", 0);
        readLine("D:\\java\\workspace\\git\\case\\case Java Other\\case Java IO\\doc\\test.txt");

    }

    /**
     * 读取文本
     *
     * @param filepath
     */
    public void readFile(String filepath, int fromLineNum) {

        // TODO : 各种判断略

        File logFile = new File(filepath);
        StringBuffer logContentBuffer = new StringBuffer();
        int toLineNum = 0;
        LineNumberReader reader = null;
        try {
            //reader = new LineNumberReader(new FileReader(logFile));
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(logFile), "utf-8"));
            String line = null;

            while ((line = reader.readLine()) != null) {
                toLineNum = reader.getLineNumber();        // [from, to], start as 1
                if (toLineNum >= fromLineNum) {
                    logContentBuffer.append(line).append("\n");
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
        logger.info("------> msg is :{} <-------", logContentBuffer.toString());

    }

    public void readLine(String path) {
        File logFile = new File(path);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile), "utf-8"));
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

    /**
     * 写入文本
     */
    public void writeFile(String path, String content) {

        File file = new File(path);

        if (!checkFileExists(file)) {
            Boolean isCreate = createFile(file);
            logger.info("------> 文件已创建 <-------", isCreate);
        } else {
            logger.info("------> 文件无需创建 <-------");
        }


        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
            fos.write(content.getBytes("utf-8"));
            // 换行
            fos.write(System.getProperty("line.separator").getBytes());

            fos.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 创建 File 文件
     */
    public static Boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            logger.info("------> create error  <-------");
            return Boolean.FALSE;
        }
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
        stringBuffer.append(DateUtil.formatDateTime(new Date())).append(" ")
                .append("[" + this.getClass().getSimpleName() + "#" + this.getClass().getPackage() + "]").append("-");

        return stringBuffer.toString();
    }


}
