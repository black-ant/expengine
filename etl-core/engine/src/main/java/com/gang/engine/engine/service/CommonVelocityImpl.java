package com.gang.engine.engine.service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Classname CommonVelocityImpl
 * @Description TODO
 * @Date 2019/11/10 19:34
 * @Created by ant-black 1016930479@qq.com
 */
public class CommonVelocityImpl extends AbstractVelocity {

    private String suffix = "ENG_";

//    private String templateToVelocity(String templateBody) {
//        StringBuilder backCode = new StringBuilder();
//
//
//    }


    /**
     * 对较大的文本分割后处理
     */
    public List<String> needPartition(String completionBody) {
        // TODO : NEED check
        LinkedList back = new LinkedList<String>();
        back.add(completionBody);
        return back;
    }
}
