package com.itontheway.manage.config;

import cn.hutool.json.JSONUtil;
import com.itontheway.manage.common.Result;
import com.itontheway.manage.exception.BasicException;
import com.itontheway.manage.exception.CustomizeException;
import com.itontheway.manage.exception.ExceptionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/10 17:30
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionConfig {

    /**
     * @Author 公众号 itontheway
     * @Date 2020/3/10 18:01
     * @Desc 自定义异常
     * @Param [e]
     * @Return com.itontheway.manage.common.Result
     **/
    @ExceptionHandler({CustomizeException.class, ConstraintViolationException.class})
    public Result exceptionHandler(Exception e) {
        if (e instanceof CustomizeException) {
            CustomizeException customizeException = (CustomizeException) e;
            return Result.fail(customizeException.getMsg());
        }
        return Result.fail("服务器异常：" + e.getMessage());
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:50
     * @param [request, exception, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 404异常处理
     **/
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView errorHandler(HttpServletRequest request, NoHandlerFoundException exception, HttpServletResponse response) {
        return commonHandler(request, response, exception.getClass().getSimpleName(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:51
     * @param [request, exception, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 405异常处理
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView errorHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception, HttpServletResponse response) {
        return commonHandler(request, response, exception.getClass().getSimpleName(), HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage());
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:51
     * @param [request, exception, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 415异常处理
     **/
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView errorHandler(HttpServletRequest request, HttpMediaTypeNotSupportedException exception, HttpServletResponse response) {
        return commonHandler(request, response, exception.getClass().getSimpleName(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), exception.getMessage());
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:51
     * @param [request, exception, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 500异常处理
     **/
    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(HttpServletRequest request, Exception exception, HttpServletResponse response) {
        return commonHandler(request, response, exception.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:52
     * @param [request, exception, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 业务异常处理
     **/
    @ExceptionHandler(value = BasicException.class)
    private ModelAndView errorHandler(HttpServletRequest request, BasicException exception, HttpServletResponse response) {
        return commonHandler(request, response, exception.getClass().getSimpleName(), exception.getCode(), exception.getMessage());
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:52
     * @param [exception, request, response]
     * @return com.itontheway.manage.exception.ExceptionEntity
     * @desc 表单验证异常处理
     **/
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ExceptionEntity validExceptionHandler(BindException exception, HttpServletRequest request, HttpServletResponse response) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ExceptionEntity entity = new ExceptionEntity();
        entity.setMessage(JSONUtil.toJsonStr(errors));
        entity.setPath(request.getRequestURI());
        entity.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        entity.setError(exception.getClass().getSimpleName());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return entity;
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:53
     * @param [request, response, error, httpCode, message]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 异常处理数据处理
     **/
    private ModelAndView commonHandler(HttpServletRequest request, HttpServletResponse response, String error, int httpCode, String message) {
        ExceptionEntity entity = new ExceptionEntity();
        entity.setPath(request.getRequestURI());
        entity.setError(error);
        entity.setCode(httpCode);
        entity.setMessage(message);
        return determineOutput(request, response, entity);
    }

    /**
     * @Author xiegl
     * @Date 2022-6-16 11:53
     * @param [request, response, entity]
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 异常输出处理 https://github.com/BNDong/spring-cloud-examples/blob/b7780363ee/spring-cloud-zuul/cloud-zuul/src/main/java/com/microview/zuul/constant/code/ResponseStatusCodeConstant.java
     **/
    private ModelAndView determineOutput(HttpServletRequest request, HttpServletResponse response, ExceptionEntity entity) {
        if (!(request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("exception", entity);
            return modelAndView;
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setCharacterEncoding("UTF8");
            response.setHeader("Content-Type", "application/json");
            try {
                response.getWriter().write(ResultJsonUtil.build(-1, "应用程序异常", JSONUtil.parseObj(JSONUtil.toJsonStr(entity))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
