package com.tian.algorithm.concurrentRob.coupon2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @desc
 * @since 2020-09-11 00:56
 */
public class CouponDemo {


    public static void main(String[] args) {

        Map<Integer, AtomicInteger> map = new HashMap<>();
        map.put(0, new AtomicInteger(5));
        map.put(1, new AtomicInteger(15));
        map.put(2, new AtomicInteger(10));
        map.put(3, new AtomicInteger(5));
        map.put(4, new AtomicInteger(10));

        //注意: 这里只能有InheritableThreadLocal,不能用ThreadLocal
        InheritableThreadLocal itl = null;
        for(int i=0;i<100;i++){
            itl = new InheritableThreadLocal();
            itl.set(i);
            InheritableThreadLocal finalItl = itl;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer integer = (Integer) finalItl.get();
                    AtomicInteger kucun = map.get(integer % 5);

                    int zhangshu = kucun.decrementAndGet();
                    long l = System.currentTimeMillis();

                    if(zhangshu<0){
                        System.out.println("路人"+integer+" 没抢到优惠券的类型: "+(integer % 5) + " 当前时间为："+l);
                    }else {
                        System.out.println("路人"+integer+" 抢购一张优惠券的类型: "+(integer % 5)+" 还剩:"+zhangshu+"张" + " 当前时间为："+l);
                    }
                }
            }).start();
        }

    }

    @Test
    public void test(){

        Map<String,AtomicInteger> map = new HashMap();

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getAutoIncreate(map,"a");
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getAutoIncreate(map,"b");
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getAutoIncreate(map,"c");
                }
            }).start();
        }

    }

    public void getAutoIncreate(Map<String,AtomicInteger> map,String key){
        //Map<String,AtomicInteger> map = new HashMap();
        if(map.get(key)==null) {
            map.put(key,new AtomicInteger(1));
        }else {
            map.get(key).getAndIncrement();

            //int value = map.get(key).incrementAndGet();
            //map.put(key,new AtomicInteger(value));
        }
        System.out.println("key: "+key+" value: "+map.get(key));
    }


    /**
     *  ？？？看看是否有哪里不对的地方
     */
    @Test
    public void test2(){

        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(0, 5);
        map.put(1, 15);
        map.put(2, 10);
        map.put(3, 5);
        map.put(4, 10);

        //注意: 这里只能有InheritableThreadLocal,不能用ThreadLocal
        InheritableThreadLocal itl = null;
        for(int i=0;i<100;i++){
            itl = new InheritableThreadLocal();
            itl.set(i);
            InheritableThreadLocal finalItl = itl;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer integer = (Integer) finalItl.get();
                    Integer kucun = map.get(integer % 5);

                    int zhangshu = kucun-1;
                    map.put(integer % 5,zhangshu);

                    if(zhangshu<0){
                        System.out.println("路人"+integer+" 没抢到优惠券的类型: "+(integer % 5));
                    }else {
                        System.out.println("路人"+integer+" 抢购一张优惠券的类型: "+(integer % 5)+" 还剩:"+zhangshu+"张");
                    }
                }
            }).start();
        }

    }

}

