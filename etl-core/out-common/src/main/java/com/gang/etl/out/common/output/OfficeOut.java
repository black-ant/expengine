package com.gang.etl.out.common.output;

import com.gang.etl.common.output.sdk.OutputConfig;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Classname OfficeOut
 * @Description TODO
 * @Date 2019/12/14 18:54
 * @Created by zengzg
 */
@Component
public class OfficeOut extends AbstractOut<String, String> {


    public static boolean writeWordFile() {

        boolean w = false;
        String path = "c:/";


        if (!"".equals(path)) {

            // 检查目录是否存在
            File fileDir = new File(path);
            if (fileDir.exists()) {

                // 生成临时文件名称
                String fileName = "a.doc";
                String content = "<html>" +
                        "<head>你好</head>" +
                        "<body>" +
                        "<table>" +
                        "<tr>" +
                        "<td>信息1</td>" +
                        "<td>信息2</td>" +
                        "<td>t3</td>" +
                        "<tr>" +
                        "</table>" +
                        "</body>" +
                        "</html>";

                byte b[] = content.getBytes();


            }
        }

        return w;
    }

    public String out(String doc) {
        //        try {
        //            ByteArrayInputStream bais = new ByteArrayInputStream(b);
        //            POIFSFileSystem poifs = new POIFSFileSystem();
        //            DirectoryEntry directory = poifs.getRoot();
        //            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
        //            FileOutputStream ostream = new FileOutputStream(path + fileName);
        //            poifs.writeFilesystem(ostream);
        //            bais.close();
        //            ostream.close();
        //
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        return null;
    }

    @Override
    public String output(String source, OutputConfig outputConfig) {
        return null;
    }
}
