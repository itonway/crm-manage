package com.itontheway.manage.concurrent;

import java.util.concurrent.*;

/**
 * Author xiegl
 * Date 2022-1-6 10:49
 * Desc
 **/
public class CompletionServiceDemo {
    public static void main(String[] args) {
        Integer integer = get();
        System.out.println(integer);
    }


    public static Integer get(){
        Integer integer = null;
        try {

            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            CompletionService<Integer> cs = new ExecutorCompletionService<>(threadPool);
            cs.submit(() -> {
                return 0;
            });
            integer = cs.take().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return integer;
    }

}
