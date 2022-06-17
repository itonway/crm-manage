package com.itontheway.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author xiegl
 * @Date 2022-6-17 16:02
 * @Desc
 **/
@Configuration
@EnableAsync(proxyTargetClass = true)
public class ThreadToolConfig {

    @Bean("createThreadPoolTaskExecutor")
    public Executor createThreadPoolTaskExecutor() {
        //获取当前机器的核数
        int cpuNum = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ContextAwarePoolExecutor();
        //配置核心线程数cpuNum
        executor.setCorePoolSize(cpuNum);
        //配置最大线程数cpuNum * 2
        executor.setMaxPoolSize(cpuNum * 2);
        //配置队列大小
        executor.setQueueCapacity(300);
        //线程存活时间
        executor.setKeepAliveSeconds(60);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("pool-thread-");
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

}
