package com.tian.algorithm.Z_inter.other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author David Tian
 * @desc https://blog.csdn.net/weixin_42002747/article/details/107939527
 * @since 2021/8/2 01:24
 */
public class MyBlockingQueueForLock<T> {
    private Queue<T> queue=new LinkedList<>();
    private final int MAX;
    private ReentrantLock lock=new ReentrantLock();
    private Condition producer=lock.newCondition();
    private Condition consumer=lock.newCondition();

    public MyBlockingQueueForLock(int limit){
        this.MAX=limit;
    }

    public void put(T t) throws InterruptedException {
        final ReentrantLock lock=this.lock;
        lock.lockInterruptibly();
        try {
            while(queue.size()==MAX){
                producer.await();//响应中断
            }
            queue.offer(t);
            consumer.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public T get() throws InterruptedException {
        final ReentrantLock lock=this.lock;
        lock.lockInterruptibly();
        T t;
        try {
            while (queue.size()==0){
                consumer.await();//响应中断
            }
            t=queue.poll();
            producer.signalAll();
        }finally {
            lock.unlock();
        }
        return t;
    }
}
