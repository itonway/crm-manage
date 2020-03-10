package com.itontheway.manage.interceptor;

import com.itontheway.manage.common.Result;
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
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.fail("服务器异常：" + e.getMessage());
    }
}
