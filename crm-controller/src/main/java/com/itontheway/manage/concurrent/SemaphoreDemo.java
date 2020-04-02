package com.itontheway.manage.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author xiegl
 * @desc
 * @date 2020/3/27 10:43
 */
public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(3);  //模拟三个停车位
    public static void main(String[] args) {
        //模拟六部汽车抢车位
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位...");
                    Thread.sleep(3000);//模拟停车时间
                    System.out.println(Thread.currentThread().getName() + "停车3秒后离开车位...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //每个车离开后，都会释放车位 即释放线程
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
