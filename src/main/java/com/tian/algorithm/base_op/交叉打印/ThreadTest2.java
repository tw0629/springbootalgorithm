package com.tian.algorithm.base_op.交叉打印;

/**
 * @author David Tian
 * @desc 交叉打印
 * @since 2021/9/1 11:35
 */

import java.util.concurrent.Semaphore;

/**
 * 交叉打印的模板流程：
 * 获取锁；
 *      while( a==t/f ){  // a是否等于特定条件
 *         wait()/await();
 *      }
 *      notify()/signal();
 *      a=t/f; // 赋值a特定条件
 * 释放锁；
 *
 *
 * https://blog.csdn.net/zydybaby/article/details/116454944
 *
 */
public class ThreadTest2 {

    private static Integer num = 10;
    private static Integer count = 0;
    private static final Object LOCK = new Object();

    private void printABC(int targetNum) {
        for (int i = 0; i < num; i++) {
            synchronized (LOCK) {
                while (count % 3 != targetNum) {    //想想这里为什么不能用if代替while，想不起来可以看公众号上一篇文章
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                System.out.print(Thread.currentThread().getName());
                LOCK.notifyAll();
            }
        }
    }

    private static Semaphore s1 = new Semaphore(1); //因为先执行线程A，所以这里设s1的计数器为1
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);

    private void printABC(Semaphore currentThread, Semaphore nextThread) {
        for (int i = 0; i < num; i++) {
            try {
                currentThread.acquire();       //阻塞当前线程，即信号量的计数器减1为0
                System.out.print(Thread.currentThread().getName());
                nextThread.release();          //唤醒下一个线程，即信号量的计数器加1

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        // Object
        ThreadTest2  thread = new ThreadTest2 ();
        new Thread(() -> {
            thread.printABC(0);
        }, "A").start();
        new Thread(() -> {
            thread.printABC(1);
        }, "B").start();
        new Thread(() -> {
            thread.printABC(2);
        }, "C").start();

        System.out.println();

        // Semaphore
//        ThreadTest2 threadSemaphore = new ThreadTest2();
//        new Thread(() -> {
//            threadSemaphore.printABC(s1, s2);
//        }, "A").start();
//        //Thread.sleep(10);
//        new Thread(() -> {
//            threadSemaphore.printABC(s2, s3);
//        }, "B").start();
//        //Thread.sleep(10);
//        new Thread(() -> {
//            threadSemaphore.printABC(s3, s1);
//        }, "C").start();
    }

}
