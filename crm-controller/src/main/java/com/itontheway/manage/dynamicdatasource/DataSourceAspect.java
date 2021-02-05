package com.itontheway.manage.dynamicdatasource;

import com.itontheway.manage.validator.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/***
 * @Author: xiegl
 * @desc 多数据源切面
 * @Date 2021-2-4 11:29
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    /**
     * @Author: xiegl
     * @desc 切入点只对 @Service 注解的类上的 @MyDataSource 方法生效
     * @Date 2021-2-4 11:29
     * @Return void
     * @Param [myDataSource]
     */
    @Pointcut(value = "@annotation(com.itontheway.manage.validator.MyDataSource) " +
            " || @within(com.itontheway.manage.validator.MyDataSource) " +
            " || @within(org.springframework.stereotype.Service)")
    public void dataSourcePointCut() {
        log.info ( "DataSourceAspect 多数据源切面 dataSourcePointCut......." );
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取目标对象
        Class targetClazz = pjp.getTarget().getClass();
        //获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();

        //获取对象和方法上注解值
        MyDataSource targetDB = (MyDataSource) targetClazz.getAnnotation(MyDataSource.class);
        MyDataSource methodDB = targetMethod.getAnnotation(MyDataSource.class);

        //判断并处理
        if (Objects.nonNull(targetDB) || Objects.nonNull(methodDB)) {
            String value;
            if (Objects.nonNull(methodDB)) {
                value = methodDB.value();
            } else {
                value = targetDB.value();
            }
            RoutingDataSource.setDataSource(value);
        }
        try {
            return pjp.proceed();
        } finally {
            RoutingDataSource.clearDataSource();
        }
    }
}
