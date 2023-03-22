package com.tian.algorithm.Z_inter.交叉打印;

/**
 * @author David Tian
 * @desc 交叉打印
 * @since 2021/9/1 11:35
 */

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
 */
public class ThreadTest {

    private static Integer num = 10;
    private static int count = 0;  // <<<------ !!!

    public ThreadTest(Integer num){
        this.num=num;
    }

    

    private static final Object object = new Object();

    public static class Task1 implements Runnable{

        @Override
        public void run() {
            synchronized (object){
               for(int i= 0; i<num; i++) {

                   while (count%3!=0){  // <<<------ !!!
                       try {
                           object.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                   System.out.println("task1: "+ count);
                   count++;  // <<<------ !!!
                   object.notifyAll();
               }
            }
        }
    }

    public static class Task2 implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                for(int i= 0; i<num; i++) {

                    while (count%3!=1){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("task2: "+count);
                    count++;
                    object.notifyAll();
                }
            }
        }
    }

    public static class Task3 implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                for(int i= 0; i<num; i++) {

                    while (count%3!=2){
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("task3: "+count);
                    count++;
                    object.notifyAll();
                }
            }
        }
    }
    

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

//    错误示范：
//    public static class TaskA implements Runnable{
//
//        @Override
//        public void run() {
//            try {
//                lock.lockInterruptibly();
//
//                for(int i= 0; i<num; i++) {
//
//                    while (count%3!=0){
//                        conditionB.await();
//                        conditionC.await();
//                    }
//                    System.out.println("taskA: "+count);
//                    count++;
//                    conditionB.signalAll();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//            }
//        }
//    }

    public static class TaskA implements Runnable{

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();

                for(int i= 0; i<num; i++) {

                    while (count%3!=0){
                        conditionA.await();
                    }
                    System.out.println("taskA: "+count);
                    count++;
                    conditionB.signalAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static class TaskB implements Runnable{

        @Override
        public void run() {

            try {
                lock.lockInterruptibly();

                for(int i= 0; i<num; i++) {

                    while (count%3!=1){
                        conditionB.await();
                    }
                    System.out.println("taskB: "+count);
                    count++;
                    conditionC.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static class TaskC implements Runnable{

        @Override
        public void run() {

            try {
                lock.lockInterruptibly();

                for(int i= 0; i<num; i++) {

                    while (count%3!=2){
                        conditionC.await();
                    }
                    System.out.println("taskC: "+count);
                    count++;
                    conditionA.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    // 以A开始的信号量,初始信号量数量为1
    private static Semaphore A = new Semaphore(1);
    // B、C信号量,A完成后开始,初始信号数量为0
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    A.acquire();
                    // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    System.out.println("taskA: "+count);
                    count++;
                    B.release();
                    // B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    B.acquire();
                    System.out.println("taskB: "+count);
                    count++;
                    C.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    C.acquire();
                    System.out.println("taskC: "+count);
                    count++;
                    A.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *     1 synchronized(wait,notifyAll)
     *     2 ReentrantLock Condition(await,signalAll)
     *     3 Semaphore(acquire,release)
     *     4 Semaphore 
     */

    /**
     * https://zhuanlan.zhihu.com/p/346688153
     */
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest(30);

//        new Thread(new Task1()).start();
//        new Thread(new Task2()).start();
//        new Thread(new Task3()).start();


        System.out.println();

//        new Thread(new TaskA()).start();
//        new Thread(new TaskB()).start();
//        new Thread(new TaskC()).start();

        System.out.println();

        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
    
    
        /**
     * 感觉和 CountDownLatch 没啥关系
     */
    private static final int MAX_PRINT_NUM = 30;
    private static volatile int count1 = 0;

    public static void countDownLatchPrint() {
        // 声明CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(() -> {
            while (count1 < MAX_PRINT_NUM) {
                if (count1 % 3 == 0) {
                    System.out.println("num1: " + count1);
                    count1++;
                }
            }
            // 偶数线程执行完则计数器减一
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (count1 < MAX_PRINT_NUM) {
                if (count1 % 3 == 1) {
                    System.out.println("num2: " + count1);
                    count1++;
                }
            }
            // 奇数线程执行完则计数器减一
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (count1 < MAX_PRINT_NUM) {
                if (count1 % 3 == 2) {
                    System.out.println("num3: " + count1);
                    count1++;
                }
            }
            // 奇数线程执行完则计数器减一
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (Exception e) {
        }

        System.out.println("=====>=====>=====> "+count1);

    }

}
