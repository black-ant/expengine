package com.gang.etl.engine.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname SyncSearch
 * @Description TODO
 * @Date 2019/12/23 21:12
 * @Created by zengzg
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
public @interface SyncSearch {

    /**
     * desc :
     * 1 list : getList
     * 2 one : getOne
     *
     * @return
     */
    String type() default "list";
}
