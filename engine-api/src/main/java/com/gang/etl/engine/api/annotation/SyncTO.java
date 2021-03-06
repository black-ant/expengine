package com.gang.etl.engine.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname SyncTO
 * @Description TODO
 * @Date 2020/1/22 17:40
 * @Created by zengzg
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface SyncTO {

    String type() default "";

    String app() default "";

    String name() default "";

    String value() default "";
}
