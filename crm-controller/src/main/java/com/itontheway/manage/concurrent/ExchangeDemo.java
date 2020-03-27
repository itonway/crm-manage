package com.itontheway.manage.concurrent;

import java.util.concurrent.Exchanger;

/**
 * @author xiegl
 * @desc ExchangeDemo
 * @date 2020/3/26 16:39
 */
public class ExchangeDemo {
    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                String A = "数据A";
                String B = exchanger.exchange(A);
                System.out.println(Thread.currentThread().getName() + "：A录入的是：" + A + ",B录入是：" + B);
            } catch (InterruptedException e) {
            }
        },"Thread-0").start();

        new Thread(() -> {
            try {
                String B = "数据B";
                String A = exchanger.exchange(B);
                System.out.println(Thread.currentThread().getName() +  "：A录入的是：" + A + ",B录入是：" + B);
            } catch (InterruptedException e) {

            }
        },"Thread-1").start();
    }
}
