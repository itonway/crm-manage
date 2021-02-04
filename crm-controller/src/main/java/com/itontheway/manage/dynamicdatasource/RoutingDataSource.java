package com.itontheway.manage.dynamicdatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: xiegl
 * @desc 重写 determineCurrentLookupKey() 方法 获取当前数据源
 * @Date 2021-2-4 11:18  https://blog.csdn.net/qq_37317845/article/details/101143269
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

//    @Override
//    protected Object determineCurrentLookupKey() {
//        //获取切面放在ThreadLocal里面的目标数据源名称，作为本次请求操作的数据源
//        String dataSource = DynamicDataSourceSwitcher.getDataSource ();
//        log.error ( "DynamicDataSource 当前数据源 {}", dataSource );
//        return dataSource;
//    }

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public RoutingDataSource(DataSource dbOne, Map<Object, Object> datasourceMap) {
        super.setDefaultTargetDataSource(dbOne);
        super.setTargetDataSources(datasourceMap);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = getDatasource();
        log.error ( "DynamicDataSource 当前数据源 {}", datasource );
        return datasource;
    }

    public static String getDatasource() {
        return contextHolder.get();
    }

    public static void setDatasource(String datasource) {
        contextHolder.set(datasource);
    }

    public static void clearDatasource(){
        contextHolder.remove();
    }
}
