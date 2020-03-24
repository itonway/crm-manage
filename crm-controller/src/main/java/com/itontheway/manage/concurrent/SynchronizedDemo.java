package com.itontheway.manage.concurrent;

/**
 * @author: 公众号 itontheway
 * @description: Synchronized 解析
 * @date 2020/3/19 19:53
 */
public class SynchronizedDemo {
    public void method1(){
        System.out.println("Method 1 start...");
        try {
            synchronized (this) {
                System.out.println("Method 1 execute...");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end...");
    }

    public void method2(){
        System.out.println("Method 2 start...");
        try {
            synchronized (this) {
                System.out.println("Method 2 execute...");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end...");
    }

    public static void main(String[] args) {
        final SynchronizedDemo test = new SynchronizedDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();
    }
}
