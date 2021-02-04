package com.itontheway.manage.dynamicdatasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiegl
 * @desc 多数据源配置
 * @Date 2021-2-4 11:27
 */
@Slf4j
@Configuration
public class MultipleDataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource createMasterDataSource() {
        log.info ( "MultipleDataSourceConfig 初始化master库。。。。。" );
        return new DruidDataSource();
    }


    @Bean("slave1")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource createSlave1DataSource() {
        log.info ( "MultipleDataSourceConfig 初始化slave1库。。。。。" );
        return new DruidDataSource();
    }

    /**
     * @Author: xiegl
     * @desc 设置动态数据源，通过@Primary 来确定主DataSource
     *      * 自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
     * @Date 2021-2-4 11:26
     * @Return javax.sql.DataSource
     * @Param [master, slave1]
     */
    @Bean
    @Primary
    public DataSource createDynamicDataSource(
            @Qualifier("master") DataSource master,
            @Qualifier("slave1") DataSource slave1) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        //设置默认数据源
        routingDataSource.setDefaultTargetDataSource ( master );
        //配置多数据源
        Map<Object, Object> map = new HashMap<> ();
        map.put ( "master", master );
        map.put ( "slave1", slave1 );
        routingDataSource.setTargetDataSources ( map );
        return routingDataSource;
    }
}
