package com.gang.exp.engine.velocity;

import com.gang.exp.engine.api.EngineRunner;
import org.apache.velocity.VelocityContext;

/**
 * @Classname VelocityRunner
 * @Description TODO
 * @Date 2019/11/19 22:38
 * @Created by zengzg
 */
public class VelocityRunner implements EngineRunner {

    @Override
    public String doEngine() {

        elocity.init();

        VelocityContext context = new VelocityContext();

        context.put("name", new String("Velocity"));

        Template template = null;


        return null;
    }
}
