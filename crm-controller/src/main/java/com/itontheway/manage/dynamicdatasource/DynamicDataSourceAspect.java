package com.itontheway.manage.dynamicdatasource;

import com.itontheway.manage.validator.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/***
 * @Author: xiegl
 * @desc 多数据源切面
 * @Date 2021-2-4 11:29
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class DynamicDataSourceAspect {

    /**
     * @Author: xiegl
     * @desc 切入点只对@Service注解的类上的@DataSource方法生效
     * @Date 2021-2-4 11:29
     * @Return void
     * @Param [myDataSource]
     */
    @Pointcut(value = "@within(org.springframework.stereotype.Service) && @annotation(myDataSource)")
    public void dynamicDataSourcePointCut(MyDataSource myDataSource) {
        log.info ( "DynamicDataSourceAspect 多数据源切面 dynamicDataSourcePointCut......." );
    }

    /**
     * @Author: xiegl
     * @desc 执行前切换数据库
     * @Date 2021-2-4 11:29
     * @Return void
     * @Param [myDataSource]
     */
    @Before(value = "dynamicDataSourcePointCut(myDataSource)")
    public void switchDataSource(MyDataSource myDataSource) {
        log.info ( "DynamicDataSourceAspect 设置数据源， switchDataSource" );
        DynamicDataSourceSwitcher.setDataSource ( myDataSource.value () );
    }

    /**
     * @Author: xiegl
     * @desc 切点执行完后 切换成主数据库
     * @Date 2021-2-4 11:27
     * @Return void
     * @Param [myDataSource]
     */
    @After(value = "dynamicDataSourcePointCut(myDataSource)")
    public void after(MyDataSource myDataSource) {
        log.info ( "DynamicDataSourceAspect，执行完后清空数据源， after" );
        DynamicDataSourceSwitcher.cleanDataSource ();
    }
}
