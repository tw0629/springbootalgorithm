package com.tian.algorithm.concurrentRob.coupon;//package com.coupon;
//
//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.concurrent.Semaphore;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @author David Tian
// * @desc
// * @since 2020-08-22 15:35
// */
//public class CouponMain {
//
//    //优惠券数量
//    final static AtomicInteger type1Count = new AtomicInteger(5);
//    final static AtomicInteger type2Count = new AtomicInteger(5);
//    final static AtomicInteger type3Count = new AtomicInteger(5);
//
//    //控制同时多少人抢优惠券
//    static Semaphore type1 = new Semaphore(10);
//    static Semaphore type2 = new Semaphore(10);
//    static Semaphore type3 = new Semaphore(10);
//
//
//    public static void init(){
//
//        //优惠券类型 优惠券数量 货品类型 交易金额（数量*单价） 交易时间段
//        Map<String, List<String>> goodTocoupon = new HashMap<>();
//        goodTocoupon.put("good1", Arrays.asList("couponA","couponB","couponC"));
//        goodTocoupon.put("good2", Arrays.asList("couponA","couponB","couponC"));
//        goodTocoupon.put("good3", Arrays.asList("couponA","couponB","couponC"));
//
//
//        Map<String, String> couponRule = new HashMap<>();
//        //goodTocoupon.put("couponA", "fixed");
//        //goodTocoupon.put("couponB", "discount");
//        //goodTocoupon.put("couponC", "discount");
//    }
//
//    public static void main(String[] args){
//
//        //初始化环境
//        init();
//
//        //模拟多个用户
//        for(int i=0; i<10; i++){
//            new Thread(()->{buyGoodByCoupon("1","good1",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("1","good2",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("1","good3",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("2","good1",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("2","good2",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("2","good3",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("3","good1",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("3","good2",new BigDecimal(100.00));}).start();
//            new Thread(()->{buyGoodByCoupon("3","good3",new BigDecimal(100.00));}).start();
//        }
//
//
//
//    }
//
//    public static List<BigDecimal> buyGoodByCoupon(String userId, String goodId, BigDecimal count){
//
//        //策略模式优化
//        if(goodId.equals("good1")){
//            int p =  type1Count.get();
//            if(p<1){
//                //判断优惠规则
//
//                return Arrays.asList(new BigDecimal(0.00));
//            }
//            type1.acquireUninterruptibly();
//            type1Count.getAndDecrement();
//            type1.release();
//            return Arrays.asList(new BigDecimal(count*));
//
//        }
//
//        if(goodId.equals("good2")){
//            int p =  type2Count.get();
//            if(p<1){
//                //判断优惠规则
//                return Arrays.asList(new BigDecimal(0.00));
//            }
//            type2.acquireUninterruptibly();
//            type2Count.getAndDecrement();
//            type1.release();
//            //判断优惠规则
//            return Arrays.asList(new BigDecimal(count*));
//        }
//
//        if(goodId.equals("good3")){
//            int p =  type3Count.get();
//            if(p<1){
//                //判断优惠规则
//                return Arrays.asList(new BigDecimal(0.00));
//            }
//            type3.acquireUninterruptibly();
//            type3Count.getAndDecrement();
//            type3.release();
//            //判断优惠规则
//            return Arrays.asList(new BigDecimal(count*));
//        }
//
//
//        return null;
//    }
//
//    class CouponRule {
//
//        //fixed 和  discount
//        private String ruleType;
//
//        private int fixed;
//
//        private float Discount;
//
//        public String getRuleType() {
//            return ruleType;
//        }
//
//        public void setRuleType(String ruleType) {
//            this.ruleType = ruleType;
//        }
//
//        public int getFixed() {
//            return fixed;
//        }
//
//        public void setFixed(int fixed) {
//            this.fixed = fixed;
//        }
//
//        public float getDiscount() {
//            return Discount;
//        }
//
//        public void setDiscount(float discount) {
//            Discount = discount;
//        }
//    }
//}
