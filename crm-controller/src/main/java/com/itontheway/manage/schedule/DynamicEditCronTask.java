//package com.itontheway.manage.schedule;
//
//import com.itontheway.manage.common.DateUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @author: 公众号 itontheway
// * @description: TODO
// * @date 2020/3/12 20:55
// */
//@Slf4j
//@Component
//public class DynamicEditCronTask implements SchedulingConfigurer {
//    private static String cron = "0/3 * * * * ?";
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(() -> {
//            // 任务逻辑
//            log.error("dynamicCronTask is running..." + DateUtil.formatLocal(new Date()));
//        }, triggerContext -> {
//            CronTrigger cronTrigger = new CronTrigger(cron);
//            return cronTrigger.nextExecutionTime(triggerContext);
//        });
//    }
//
//    /**
//     * 供应用端调用动态修改cron参数方法
//     */
//    public void setCron(String cron) {
//        this.cron=cron;
//    }
//}
