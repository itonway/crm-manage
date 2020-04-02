package com.itontheway.manage.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * @author xiegl
 * @desc CountDownLatch Demo
 * 一个主线程和5个辅助线程，等待主线程准备就绪了，
 * 5个辅助线程开始运行，等待5个辅助线程运行完毕了，主线程继续往下运行
 * 1.几件事情完成之后才能开始另外一件事情。
 * 2.需要做的几件事情可以独立完成，并且可以并行处理。
 * @date 2020/3/27 10:42
 */
public class CountDownLatchDemo {
    private static final Integer THREAD_NUM = 7;
    private static CountDownLatch countDownLatch = new CountDownLatch(7);//表示需要等待执行完毕的线程数量
    public static void main(String[] args) throws InterruptedException {
        for(int i = 1 ;i <= THREAD_NUM; i++){
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println ( "第"+ index +"颗龙珠已收集." );
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //每收集一颗龙珠，收集的总数减一
                    countDownLatch.countDown ();
                }
            }).start();
        }
        //等待收集7颗龙珠后，才往下执行。
        countDownLatch.await();
        System.out.println ( "已收集7颗龙珠，召唤神龙." );
    }
}
