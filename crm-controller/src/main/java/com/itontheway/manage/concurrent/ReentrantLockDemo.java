package com.itontheway.manage.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/20 21:04
 */
public class ReentrantLockDemo {
    private Lock lock = new ReentrantLock(true);
    public void fairLock(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()  + "正在持有锁");
        }finally {
            System.out.println(Thread.currentThread().getName()  + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo myFairLock = new ReentrantLockDemo();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            myFairLock.fairLock();
        };
        Thread[] thread = new Thread[5];
        for(int i = 0;i < 5;i++){
            thread[i] = new Thread(runnable);
        }
        for(int i = 0;i < 5;i++){
            thread[i].start();
        }
    }

}
