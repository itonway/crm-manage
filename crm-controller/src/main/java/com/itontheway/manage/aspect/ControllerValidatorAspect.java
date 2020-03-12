package com.itontheway.manage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * @author: 公众号 itontheway
 * @description: 可以拿到方法的参数（校验参数是否合法），但是却拿不到http请求和响应的对象
 * @date 2020/3/11 17:27
 */
@Slf4j
@Aspect
@Component
public class ControllerValidatorAspect {
    @Around("execution(* com.itontheway.manage.controller..*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        log.info("ControllerValidatorAspect doAround 得到的参数为: {}....",pjp.getArgs());
        Object returnVal ;
        if (bindingResult.hasErrors ()) {
            throw new BindException(bindingResult);
        } else {
            returnVal = pjp.proceed ();
            return returnVal;
        }
    }
}
