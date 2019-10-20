package com.gang.expengine.core.logic;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StartLogic implements ApplicationRunner {


    @Autowired
    private AbstractLogic logic;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logic.init();
        Template template = logic.getFileTeplate("hellovelocity.vm");
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "Velocity");
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        ctx.put("list", list);
        // 输出
        StringWriter sw = new StringWriter();
        template.merge(ctx, sw);
        System.out.println(sw.toString());
    }
}
