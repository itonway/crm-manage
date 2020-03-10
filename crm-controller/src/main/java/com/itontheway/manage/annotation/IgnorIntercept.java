package com.itontheway.manage.annotation;

import java.lang.annotation.*;

/**
 * @author: 公众号 itontheway
 * @description: 忽略拦截某个方法
 * @date 2020/3/10 16:42
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnorIntercept{
}
