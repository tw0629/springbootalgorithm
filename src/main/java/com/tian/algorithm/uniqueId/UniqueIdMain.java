package com.tian.algorithm.uniqueId;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/28 10:16
 */
public class UniqueIdMain {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int initialCapacity = 100000;
        Set<String> set = new HashSet<>(initialCapacity);
        List<Long> list = new ArrayList<>(initialCapacity);
        CountDownLatch countDownLatch = new CountDownLatch(initialCapacity);

        /**
         * TODO:在线程并发高的时候, 即使是 同一个消费者获取自增Id时候,也都在排队等待同步资源,所以就会出现"相邻的自增Id不连续"现象!!!!!!
         * TODO:但是不影响自增业务!!!!!!
         *
         * TODO:所以 "想看相邻的自增Id连续" 应该用for循环同步去测试
         */

        // 单个消费者-for循环同步
        //IdWorker idWorker = new IdWorker(Thread.currentThread().getId(),1,System.currentTimeMillis());
        //IdWorker2 idWorker = new IdWorker2(Thread.currentThread().getId());
        /*IdWorker3 idWorker = new IdWorker3(Thread.currentThread().getId(),new AtomicLong(0));
        for(int i=0;i<initialCapacity;i++){
            Long id = idWorker.nextId();

            System.out.println("=======>"+idWorker.getWorkerId()+" 第"+ i+"次获取 "+id);
            set.add(id.toString());
            list.add(id);
            countDownLatch.countDown();
        }*/


        // 单个消费者-多线程异步
        //IdWorker idWorker = new IdWorker(Thread.currentThread().getId(),1,System.currentTimeMillis());
        /*IdWorker3 idWorker = new IdWorker3(Thread.currentThread().getId(),new AtomicLong(0));
        for(int i=0;i<initialCapacity;i++){
            int time = i;
            CompletableFuture.runAsync(()->{
                Long id = idWorker.nextId();

                System.out.println("=======>"+idWorker.getWorkerId()+" 第"+ time+"次获取 "+id);
                set.add(id.toString());
                list.add(id);
                countDownLatch.countDown();
            });
        }*/

        // 多线程交叉-for循环异步（测试不同消费者同时高并发获取自增Id）
        List<IdWorker2> listIdWorkers = initIdWorkers(3);
        Random random = new Random();
        for(int i=0;i<initialCapacity;i++){
            int time = i;
            CompletableFuture.runAsync(()->{
                int n = random.nextInt(listIdWorkers.size());
                IdWorker2 iw = listIdWorkers.get(n);
                Long id = iw.nextId();

                if(iw.equals(listIdWorkers.get(0))){
                    //只打印其一
                    System.out.println("=======>"+iw.getWorkerId()+" 第"+ time+"次获取 "+id);
                }
                //System.out.println("=======>"+iw.getWorkerId()+" 第"+ time+"次获取 "+id);

                set.add(iw.getWorkerId()+"-"+id);
                list.add(id);
                countDownLatch.countDown();
            });
        }

        // 多线程交叉-异步for循环（自增Id有先后顺序）
        /*int idWorkerNum = 3;
        List<IdWorker> listIdWorkers = initIdWorkers(idWorkerNum);
        for(int i=0;i<idWorkerNum;i++){
            IdWorker iw = listIdWorkers.get(i);
            CompletableFuture.runAsync(()->{
                for(int j=0;j<initialCapacity;j++){
                    Long id = iw.nextId();

                    System.out.println("=======>"+iw.getWorkerId()+": "+id);
                    set.add(iw.getWorkerId()+"-"+id);
                    list.add(id);
                    countDownLatch.countDown();
                }
            });
        }*/

        countDownLatch.await();

        System.out.println("=======> set size: "+set.size());
        System.out.println("=======> list size: "+list.size());
        System.out.println("=======> cost time: "+(System.currentTimeMillis()-start));

        // TODO: 想办法打印一下，丢失的问题

        System.out.println("======== finish ======");
    }

    public static List<IdWorker2> initIdWorkers(int n){
        //List<IdWorker> listIdWorkers = new ArrayList<>();
        List<IdWorker2> listIdWorkers = new ArrayList<>();
        //List<IdWorker3> listIdWorkers = new ArrayList<>();
        long threadId = Thread.currentThread().getId();
        for(int i=0;i<n;i++){
            //IdWorker idWorker = new IdWorker(threadId,i,System.currentTimeMillis());
            IdWorker2 idWorker = new IdWorker2(threadId);
            //IdWorker3 idWorker = new IdWorker3(threadId,new AtomicLong(0));
            threadId++;
            listIdWorkers.add(idWorker);
        }
        return listIdWorkers;
    }
}
