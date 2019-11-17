package com.gang.exp.engine.logic;

import com.gang.exp.engine.core.common.model.query.TemplateLogsQuery;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class StartLogic implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AbstractLogic abstractLogic;

    @Autowired
    private FileIOLogic fileIOLogic;

    @Autowired
    private TemplateLogic templateLogic;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        logic.init();
//        Template template = logic.getFileTeplate("hellovelocity.vm");
//        VelocityContext ctx = new VelocityContext();
//        ctx.put("name", "Velocity");
//        List list = new ArrayList();
//        list.add("1");
//        list.add("2");
//        ctx.put("list", list);
//        // 输出
//        StringWriter sw = new StringWriter();
//        template.merge(ctx, sw);
//        System.out.println(sw.toString());

        toDisk();
    }

    public void toDisk() {
        String body = templateLogic.getTemplateFromDatebase(new TemplateLogsQuery());
        Velocity.init();
        VelocityContext context = new VelocityContext();
        context.put("username", "张三");
        context.put("password", "123456789");
        context.put("age", "20");
        context.put("address", "123456");
        context.put("blog", "test");

        StringWriter stringWriter = new StringWriter();
        Velocity.evaluate(context, stringWriter, "mystring", body);

        logger.info("------> :{}  <-------", stringWriter.toString());


    }


}
