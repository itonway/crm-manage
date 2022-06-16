package com.itontheway.manage.controller;

import com.itontheway.manage.exception.BusinessException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@Slf4j
@Api(value = "异常测试管理",tags = {"异常测试管理"})
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController extends BaseController{

    @GetMapping(value = "/test1")
    public void test1(){
        throw new BusinessException(400,"业务异常错误信息");
    }


    @GetMapping(value = "/test2")
    public void test2(){
        throw new NullPointerException("手动抛出异常错误信息");
    }

    @GetMapping(value = "/test3")
    public void test3(){
        int a = 10 / 3;
    }

}
