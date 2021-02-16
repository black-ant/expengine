package com.gang.etl.engine.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname SyncField
 * @Description TODO
 * @Date 2020/2/2 11:25
 * @Created by zengzg
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface SyncField {

    String defaultValue() default "";

    String description() default "";

    String name() default "";

    String alias() default "";

    String length() default "";

    /**
     * 展示等级 :
     * ALL 所有场景均展示
     * ADMIN 管理员展示
     * USER 普通人员展示
     * NONE 不展示
     *
     * @return
     */
    String viewRole() default "ALL";

}
