package com.itontheway.manage.interceptor;

import com.itontheway.manage.common.Result;
import com.itontheway.manage.exception.CustomizeExceptionDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(CustomizeExceptionDemo.class)
    public Result exceptionHandler(CustomizeExceptionDemo e) {
        e.printStackTrace();
        return Result.fail("服务器异常：" + e.getMsg());
    }
}
