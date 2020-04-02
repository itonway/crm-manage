package com.itontheway.manage.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xiegl
 * @desc  所有线程都等待完成后才会继续下一步行动
 * @date 2020/3/27 10:43
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool ( 5 );
        final CyclicBarrier barrier = new CyclicBarrier ( 5, () -> {
            System.out.println ( "所有玩家都已准备好，开始进入游戏..." );
            try {
                TimeUnit.SECONDS.sleep ( 1 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        } );
        for (int i = 1; i <= 5; i++) {
            service.execute ( new Player ( "玩家" + i, barrier ) );
        }
        service.shutdown ();
    }
}
class Player implements Runnable {
    private final String name;
    private final CyclicBarrier barrier;
    public Player(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }
    public void run() {
        try {
            TimeUnit.SECONDS.sleep ( 1 + (new Random().nextInt ( 5 )) );
            System.out.println ( name + "已准备,等待其他玩家准备..." );
            barrier.await ();
            TimeUnit.SECONDS.sleep ( 1 + (new Random ().nextInt ( 5 )) );
            System.out.println ( name + "已加入游戏..." );
        } catch (Exception e) {
            System.out.println ( name + "离开游戏" );
        }
    }
}