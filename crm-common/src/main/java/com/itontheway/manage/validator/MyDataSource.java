package com.itontheway.manage.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: xiegl
 * @desc MyDataSource  默认数据源 主库 master
 * @Date 2021-2-4 11:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyDataSource {
    String value() default "master";
}

