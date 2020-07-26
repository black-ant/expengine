package com.gang.etl.ldap.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * @Classname SyncUtils
 * @Description TODO
 * @Date 2020/2/21 13:29
 * @Created by zengzg
 */
public final class SyncUtils {

    private SyncUtils() {
    }

    public static String dataCreateUtil(String type, String baseData) {
        String cdata = "111111";
        switch (type) {
            case "name":
                cdata = baseData + new Random().nextInt(666);
                break;
            default:
                break;
        }
        return cdata;
    }


    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
