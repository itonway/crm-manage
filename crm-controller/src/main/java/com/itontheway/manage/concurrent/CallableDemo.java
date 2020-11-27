package com.itontheway.manage.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author xiegl
 * @Date 2020/6/28 16:35
 * @Desc
 **/
public class CallableDemo  {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws Exception{
        Test test = new Test();
        Future<String> submit = executorService.submit(test);
        String s = submit.get();
        System.out.println(s);
        executorService.shutdown();
    }
}


class Test implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("test print");
        return "test";
    }
}