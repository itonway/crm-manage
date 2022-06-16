package com.itontheway.manage;

import com.itontheway.manage.dynamicdatasource.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: 公众号 itontheway
 * @description: 主启动类
 * @date 2020/3/7 12:21
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import(DataSourceConfig.class)
//dao层所在目录，不然扫描不到
@MapperScan(value = "com.itontheway.manage.dao")
//保证切面可以切入
@EnableAspectJAutoProxy
//保证定时任务执行
@EnableScheduling
//开启缓存
@EnableCaching
@Slf4j
public class CrmManageWebApplication {
    public static void main(String[] args) {
        log.info("CrmManageWebApplication 启动开始。。。");
        SpringApplication.run(CrmManageWebApplication.class);
        log.info("CrmManageWebApplication 启动成功。。。");
    }
}
