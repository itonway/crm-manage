package com.itontheway.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @author: 公众号 itontheway
 * @description: 所有的任务都在同一个线程池但不同线程中完成
 * @date 2020/3/12 20:24
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //根据定时任务数量可做调整
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
    }
}
