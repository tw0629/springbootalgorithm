package com.tian.algorithm.concurrentRob.redPackage4;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @desc
 *
 * 实现思路：
 *   1 悲观锁机制  synchronized,lock (本人实现方式)
 *   2 乐观锁机制  CAS AtomicInteger (TODO: ??? 由于是两个维度的变量, 暂时没想出只用CAS实现的方式)
 *
 * 存在问题：
 * 1 测试过程发现:即使用了countDownLatch, redPackageRecords总是添加不全？
 *   list是非线程安全的,并发添加会被覆盖;用Vector
 * 2 有时候出现countDownLatch.await()一直阻塞, 怀疑是子线程可能会因为某些原因无法执行countdown？？
 *
 * 3 ThreadLocalRandom
 *   Random(); 并发下改用ThreadLocalRandom
 *   分析到这里我们可以看出Random的局限性并不是线程安全的问题，
 *   而是在大量线程并发的时候，通过CAS机制更新随机数种子会导致大量线程自旋，耗费CPU性能，导致系统吞吐量下降。
 *
 * @since 2021/6/30 15:32
 */
public class RobRedPackageMain {

    // 并发添加时,不要使用List,用Vector
    //public static List<RedPackageRecord> redPackageRecords = new LinkedList<>();
    public static List<RedPackageRecord> redPackageRecords = new Vector<>();

    private static AtomicInteger redPackageAmount = new AtomicInteger(0);
    private static AtomicInteger redPackageCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        RedPackage redPackage = sendRandomRedPackage("Root",10,2);

        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for(int i=0;i<1000;i++){

            //robRedPackage(""+i,redPackage);

            int finalI = i;
            CompletableFuture.runAsync(()->{
                robRedPackage(""+finalI, redPackage);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        System.out.println("");
        System.out.println("当前红包情况： 金额："+getBigDecimal(redPackage.getAmount().get())+"  个数："+redPackage.getQuantity());
        redPackageRecords();
    }

    /**
     * 查看领取记录
     */
    private static void redPackageRecords() {
        if(CollectionUtils.isEmpty(redPackageRecords)){
            return;
        }

        // 倒序:领取记录列表
        Collections.sort(redPackageRecords, new Comparator<RedPackageRecord>() {
            @Override
            public int compare(RedPackageRecord o1, RedPackageRecord o2) {
                return o1.getRobTime()>o2.getRobTime()?-1:1;
            }
        });
        for(RedPackageRecord record : redPackageRecords){
            System.out.println("用户："+record.getRobUser()+"，在"+record.getRobTime()+"时间，抢了"+record.getRedPackageId()+"的金额："+record.getMoney()+"元");
        }

        System.out.println("");
        System.out.println("======>  红包金额核算  <======");
        System.out.println("红包下发的金额："+getBigDecimal(redPackageAmount.get()));
        System.out.println("红包领取的个数："+redPackageCount);
    }

    /**
     * 红包领取
     * @param robUser
     * @param redPackage
     *
     * synchronized
     *
     */
    private static void robRedPackage(String robUser,RedPackage redPackage) {

        if(redPackage.getAmount().get()<1 || redPackage.getQuantity().get()<1){
            System.out.println(robUser+"没有抢到："+redPackage.getUserId()+"的红包");
            return;
        }

        // 红包时间有效期校验
        // 略

        /**
         * 扣减红包的金额和红包的个数 (同时)
         *
         * 注意:
         * 1 TODO: 金额和个数 必须同时控制住; 用了synchronized;
         *       TODO: !!!由于是两个CAS,而不是一个CAS,所以必须用锁锁住整体。
         *
         * 2 TODO: 否则就出现并发问题,要么金额不为0_个数为0;要么金额为0_个数不为0;
         */
        int randomMoney = 0;
        int remainAmount = 0;
        int remainCount = 0;
        synchronized (redPackage.getRedPackageId()) {
            if(redPackage.getQuantity().get()==1 || redPackage.getAmount().get()<=1){
                randomMoney = redPackage.getAmount().get();
            }else {
                // 左闭右闭 [a,b]：rand.nextInt(b-a+1)+a
                // 左闭右开 [a,b)：rand.nextInt(b-a)+a
                //Random random = new Random(); 并发下改用ThreadLocalRandom
                ThreadLocalRandom random = ThreadLocalRandom.current();
                //randomMoney = random.nextInt(redPackage.getAmount().get()-1+1)+1;
                // 会有并发问题
                randomMoney = random.nextInt(redPackage.getAmount().get()-redPackage.getQuantity().get()+1)+1;
            }
            remainAmount = redPackage.getAmount().addAndGet(-randomMoney);
            remainCount = redPackage.getQuantity().decrementAndGet();
        }
        if(remainAmount<0 || remainCount<0){
            redPackage.getAmount().addAndGet(randomMoney);
            redPackage.getQuantity().incrementAndGet();
            System.out.println("=>"+robUser+"没有抢到："+redPackage.getUserId()+"的红包");
            return;
        }

        // 核算记录
        redPackageAmount.addAndGet(randomMoney);
        redPackageCount.incrementAndGet();

        // 红包领取记录
        BigDecimal remainAmountBd = getBigDecimal(remainAmount);
        BigDecimal randomMoneyBd = getBigDecimal(randomMoney);

        RedPackageRecord record = RedPackageRecord.builder().
                robUser(robUser).
                money(randomMoneyBd).
                robTime(System.nanoTime()).
                redPackageId(redPackage.getRedPackageId())
                .build();
        redPackageRecords.add(record);

        System.out.println(robUser+"抢到："+redPackage.getUserId()+"的红包"+redPackage.getRedPackageId()+"的金额："+randomMoneyBd+"元"+"。当前剩余红包金额："+remainAmountBd+"元，当前剩余红包个数："+remainCount+"个");

    }

    private static BigDecimal getBigDecimal(int remainAmount) {
        BigDecimal amount = new BigDecimal(remainAmount);
        BigDecimal remainAmountBd = amount.divide(new BigDecimal(100));
        remainAmountBd = remainAmountBd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return remainAmountBd;
    }

    /**
     * 红包创建
     * @param sendUser
     * @param money
     * @param quantity
     * @return
     */
    private static RedPackage sendRandomRedPackage(String sendUser,Integer money,Integer quantity) {
        if(money/0.01<quantity || quantity<1 || money<0.01){
            return null;
        }

        return RedPackage.builder().
                redPackageId(System.currentTimeMillis()/1000).
                userId(sendUser).
                type("RANDOM").
                amount(new AtomicInteger(money*100)).
                quantity(new AtomicInteger(quantity)).
                build();
    }
}
