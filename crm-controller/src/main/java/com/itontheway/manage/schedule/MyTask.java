package com.itontheway.manage.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: 公众号 itontheway
 * @description: 所有的任务都是在同一个线程池中的同一个线程来完成的
 * @date 2020/3/12 18:36
 */
@Slf4j
@Component
public class MyTask {
//    //每2秒执行一次​
//    @Scheduled(cron = "0/2 * * * * *")
//    public void task2() {
//        log.info("定时任务开始2...."+ DateUtil.formatLocal(new Date()));
//    }
//    //每3秒执行一次​
//    @Scheduled(cron = "0/3 * * * * *")
//    public void task3() {
//        log.info("定时任务开始1...."+ DateUtil.formatLocal(new Date()));
//    }
//    //每4秒执行一次​
//    @Scheduled(cron = "0/4 * * * * *")
//    public void task4() {
//        log.info("定时任务开始3...."+ DateUtil.formatLocal(new Date()));
//    }

}
