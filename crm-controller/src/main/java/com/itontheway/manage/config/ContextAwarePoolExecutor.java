package com.itontheway.manage.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @Author xiegl
 * @Date 2022-6-17 16:40
 * @Desc
 **/
public class ContextAwarePoolExecutor extends ThreadPoolTaskExecutor {
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        RequestAttributes requestAttributes=null;
        try{
            requestAttributes = RequestContextHolder.currentRequestAttributes();
        }catch (IllegalStateException e){

        }
        return super.submit(new ContextAwareCallable(task,requestAttributes ));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        RequestAttributes requestAttributes=null;
        try{
            requestAttributes = RequestContextHolder.currentRequestAttributes();
        }catch (IllegalStateException e) {

        }
        return super.submitListenable(new ContextAwareCallable(task,requestAttributes));
    }
}
