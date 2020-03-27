package com.itontheway.manage.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiegl
 * @desc Condition demo  顺序打印 a b  c
 * @date 2020/3/26 10:14
 */
public class ConditionDemo {
    private static int num = 1;
    private static Lock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static final Condition conditionC = lock.newCondition();

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

    }

    public static void printA() throws InterruptedException {
        lock.lock();
        while (num != 1) {
            conditionA.await(); //不等于1，A线程处于等待
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
        num = 2;
        conditionB.signal();//唤起B线程
        lock.unlock();
    }

    public static void printB() throws InterruptedException {
        lock.lock();
        while (num != 2) {
            conditionB.await();//不等于2，B线程处于等待
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
        num = 3;
        conditionC.signal();//唤起C线程
        lock.unlock();
    }

    public static void printC() throws InterruptedException {
        lock.lock();
        while (num != 3) {
            conditionC.await();//不等于3，C线程处于等待
        }
        for (int i = 1; i <= 15; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
        num = 1;
        conditionA.signal();//唤起A线程
        lock.unlock();
    }
}
