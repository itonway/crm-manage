package com.itontheway.manage.interceptor;

import com.itontheway.manage.common.Result;
import com.itontheway.manage.exception.CustomizeExceptionDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/10 17:30
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionConfig {
    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 18:01
     * @Desc 自定义异常
     * @Param [e]
     * @Return com.itontheway.manage.common.Result
     **/
    @ExceptionHandler({CustomizeExceptionDemo.class,ConstraintViolationException.class, BindException.class})
    public Result exceptionHandler(Exception e) {
        if(e instanceof UndeclaredThrowableException){
            Throwable e1 = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
            List<FieldError> fieldErrors = ((BindException) e1).getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder ();
            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append ( fieldError.getDefaultMessage () + "," );
            }
            String string = stringBuilder.toString ();
            string = string.substring ( 0, string.length () - 1 );
            Result fail = Result.fail(string,"校验失败");
            return fail;
        }
        return Result.fail("服务器异常：" + e.getMessage());
    }

}
