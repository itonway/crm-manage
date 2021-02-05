package com.itontheway.manage.validator;

import com.itontheway.manage.common.DataSourceType;

import java.lang.annotation.*;

/**
 * @Author: xiegl
 * @desc MyDataSource  默认数据源 主库 master
 * @Date 2021-2-4 11:21
 */
@Target({ElementType.METHOD,ElementType.TYPE})//作用在方法和类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyDataSource {
    String value() default DataSourceType.MASTER;
}

