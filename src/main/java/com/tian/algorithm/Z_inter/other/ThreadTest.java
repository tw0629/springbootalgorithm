package com.tian.algorithm.Z_inter.other;

/**
 * @author David Tian
 * @desc
 * @since 2021/9/1 11:35
 */
public class ThreadTest {

    //定义一个共享变量，用来在线程中进行通信
    private static final Object obj = new Object();

    //定义一个变量来记录打印的次数，控制打印条件
    private static int count = 1;

    public static void main(String[] args) {
        //创建三个线程，然后把三个任务分别放入这三个线程执行

        //创建线程1执行任务1
        new Thread(new Task1()).start();
        //创建线程2执行任务2
        new Thread(new Task2()).start();
        //创建线程3执行任务4
        new Thread(new Task3()).start();
    }

    //任务1
    private static class Task1 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                //打印十次A
                for (int i = 0; i < 10; i++) {
                    //一直轮询，如果条件不是要打印A的条件，那么直接释放锁
                    /*
                        !!! 这个地方一定要用while，
                        如果用了if的话，再下次重新获得锁的时候，是继续往下去执行，走到obj.notifyAll()这一行代码，不会再判断count的值，
                        用while的话会再去判断count的值，如果符合条件再往下执行，走到obj.notifyAll()这一行代码，可以自己把while换成if试试，看看结果
                     */
                    while (count % 3 != 1) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //通知其他所有线程可以来抢占锁了，但是发现现在线程1在持有锁，其他线程还抢不到，只有等到线程1释放锁之后，才可以抢到锁
                    obj.notifyAll();
                    System.out.println("A:"+count);
                    count++;
                }
            }
        }
    }

    //任务2
    private static class Task2 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                //打印十次B
                for (int i = 0; i < 10; i++) {
                    //一直轮询，如果条件不是要打印B的条件，那么直接释放锁
                    while (count % 3 != 2) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //通知其他所有线程可以来抢占锁了，但是发现现在线程2在持有锁，其他线程还抢不到，只有等到线程2释放锁之后，才可以抢到锁
                    obj.notifyAll();
                    System.out.println("B:"+count);

                    count++;
                }
            }
        }
    }

    //任务3
    private static class Task3 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                //打印十次C
                for (int i = 0; i < 10; i++) {
                    //一直轮询，如果条件不是要打印C的条件，那么直接释放锁
                    while (count % 3 != 0) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //通知其他所有线程可以来抢占锁了，但是发现现在线程3在持有锁，其他线程还抢不到，只有等到线程3释放锁之后，才可以抢到锁
                    obj.notifyAll();
                    System.out.println("C:"+count);

                    count++;
                }
            }
        }
    }
}
