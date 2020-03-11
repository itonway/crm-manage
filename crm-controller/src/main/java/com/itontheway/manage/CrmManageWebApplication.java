package com.itontheway.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/7 12:21
 */
@SpringBootApplication
//dao层所在目录，不然扫描不到
@MapperScan(value = "com.itontheway.manage.dao")
@EnableAspectJAutoProxy
public class CrmManageWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmManageWebApplication.class);
    }
}
