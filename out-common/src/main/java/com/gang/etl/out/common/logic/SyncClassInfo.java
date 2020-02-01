package com.gang.etl.out.common.logic;

import com.gang.common.lib.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname SyncClassInfo
 * @Description TODO
 * @Date 2020/2/1 21:21
 * @Created by zengzg
 */
@Component
public class SyncClassInfo {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyncSDKFactory syncSDKFactory;

    public List<String> getTosParams(String className) {
        List<String> paramsList = new ArrayList<>();
        try {
            paramsList = ReflectionUtils.getFieldsName(Class.forName(className), null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("E----> error :{} -- content :{}", e.getClass() + e.getMessage(), e);
        }
        return paramsList;
    }
}
