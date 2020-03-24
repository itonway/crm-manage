package com.itontheway.manage.concurrent;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/18 20:36
 */
public class ThreadLocalDemo {
    private static Integer num = 0;

    public static void main1(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                num += 5;
                System.out.println(Thread.currentThread().getName() + " : " + num);
            }, "Thread-" + i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static final ThreadLocal<Integer> local=new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0; //通过initialValue方法设置默认值
        }
    };

    public static void main(String[] args) {
        Thread[] threads=new Thread[5];
        for(int i=0;i<5;i++){
            threads[i]=new Thread(()->{
                int num=local.get().intValue();
                num+=5;
                System.out.println(Thread.currentThread().getName()+" : "+num);
            },"Thread-"+i);
        }

        for(Thread thread:threads){
            thread.start();
        }
    }

}
