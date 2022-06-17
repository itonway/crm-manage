package com.itontheway.manage.config;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Callable;

/**
 * @Author xiegl
 * @Date 2022-6-17 16:41
 * @Desc
 **/
public class ContextAwareCallable<T> implements Callable<T> {
    private Callable<T> task;
    private RequestAttributes context;

    public ContextAwareCallable(Callable<T> task, RequestAttributes context) {
        this.task = task;
        this.context = context;
    }

    @Override
    public T call() throws Exception {
        if (context != null) {
            RequestContextHolder.setRequestAttributes(context);
        }

        try {
            return task.call();
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
