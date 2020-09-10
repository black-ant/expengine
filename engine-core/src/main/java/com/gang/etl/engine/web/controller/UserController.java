package com.gang.etl.engine.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2020/9/10 16:51
 * @Created by zengzg
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("{size}")
    public String getUserList(@RequestParam("size") Integer size) {
        JSONArray array = new JSONArray();
        Integer idOrder = new Random().nextInt(99999);
        for (int i = 0; i < size; i++) {
            JSONObject item = new JSONObject();
            item.put("id", idOrder + "-" + i);
            item.put("username", "gang" + new Random().nextInt(999));
            item.put("age", i);
            array.add(item);
        }
        return array.toJSONString();
    }


}
