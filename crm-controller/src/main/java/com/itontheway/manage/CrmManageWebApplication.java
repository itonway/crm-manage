package com.itontheway.manage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: 公众号 itontheway
 * @description: 主启动类
 * @date 2020/3/7 12:21
 */
@SpringBootApplication
//dao层所在目录，不然扫描不到
@MapperScan(value = "com.itontheway.manage.dao")
//保证切面可以切入
@EnableAspectJAutoProxy
//保证定时任务执行
@EnableScheduling
@Slf4j
public class CrmManageWebApplication {
    public static void main(String[] args) {
        log.info("启动开始。。。");
        SpringApplication.run(CrmManageWebApplication.class);
        log.info("启动成功。。。");
    }
}
