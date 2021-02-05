package com.itontheway.manage.dynamicdatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: xiegl
 * @desc 重写 determineCurrentLookupKey() 方法 获取当前数据源
 * @Date 2021-2-4 11:18  https://blog.csdn.net/qq_37317845/article/details/101143269
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = getDataSource();
        log.info ( "DynamicDataSource 当前数据源: {}", datasource );
        return datasource;
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void setDataSource(String datasource) {
        contextHolder.set(datasource);
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }
}
