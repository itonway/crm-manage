package com.itontheway.manage.dynamicdatasource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xxy
 * @Description todo 操作数据源 设置，获取，清空
 * @Date 2019/9/25 10:54
 **/
@Slf4j
public class DynamicDataSourceSwitcher {
    public static final String Mater = "master";
    public static final String Slave1 = "slave1";

    //利用ThreadLocal 将数据源与当前线程绑定，并提供get set方法
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<> ();

    public static void setDataSource(String name) {
        log.error ( "DynamicDataSourceSwitcher设置数据源数据源为 ：{} ", name );
        //设置完后调用  AbstractRoutingDataSource.getConnection 然后调用 DynamicDataSource.determineCurrentLookupKey
        contextHolder.set ( name );
    }

    public static String getDataSource() {
        return contextHolder.get ();
    }

    public static void cleanDataSource() {
        contextHolder.remove ();
    }
}
