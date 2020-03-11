package com.itontheway.manage.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/11 17:27
 */
@Aspect
@Component
public class ControllerValidatorAspect {
    @Around("execution(* com.itontheway.manage.controller..*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object returnVal ;
        if (bindingResult.hasErrors ()) {
            throw new BindException(bindingResult);
        } else {
            returnVal = pjp.proceed ();
            return returnVal;
        }
    }
}
