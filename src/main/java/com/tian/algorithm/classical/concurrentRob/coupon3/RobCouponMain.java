package com.tian.algorithm.classical.concurrentRob.coupon3;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @desc
 * 面试题：
 *
 * 题目背景：
 * 1. 定义不同场景条件下（货品种类，交易金额，交易时间段）可用的营销优惠券，及优惠金额计算规则（固定金额，按比例）
 * 2. 在多线程访问的场景下，内存中维护一组上述营销优惠券，及相应的可用数量
 * 3. 支付收银台咨询可用优惠券，并按优惠金额降序排序。
 * 产出：
 * 1. 一个通过Java main函数可执行，输出结果的代码
 *
 *
 * 实现思路：
 * 1 悲观锁机制  synchronized,lock
 * 2 乐观锁机制  CAS AtomicInteger (本人实现方式)
 *
 * @since 2021/6/30 10:37
 */
public class RobCouponMain {

    /**
     * 既然使用了原子操作Atomic,
     * 没必要使用ConcurrentHashMap,否则影响性能
     */
    private static Map<String, List<CouponDTO>> couponMap = new HashMap<>();

    private static int initialCapacity = 20;

    private static AtomicInteger foodCoupon = new AtomicInteger(0);
    private static AtomicInteger clothCoupon = new AtomicInteger(0);
    private static AtomicInteger carCoupon = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        init();

        CountDownLatch countDownLatch = new CountDownLatch(initialCapacity);
        for(int i=0;i<initialCapacity;i++){
            //robCoupon(user);

            // 异步并发抢购
            int finalI = i;
            CompletableFuture.runAsync(()->{
                User user = buildUser(finalI);
                robCoupon(user);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        robCouponMainInfo();
    }

    /**
     * 打印这次活动营销优惠券的信息
     */
    private static void robCouponMainInfo() throws Exception {
        System.out.println("");
        System.out.println("======>  营销优惠券 核算  <======");
        System.out.println("活动前：");
        System.out.println("优惠券详情："+couponMap.toString());
        System.out.println("");
        System.out.println("活动后：");
        System.out.println("FOOD优惠券发了："+foodCoupon+"张");
        System.out.println("CLOTH优惠券发了："+clothCoupon+"张");
        System.out.println("CAR优惠券发了："+carCoupon+"张");

        Thread.sleep(100000);
        System.out.println("======>  END  <======");
    }

    /**
     * 抢优惠券
     */
    private static void robCoupon(User user) {
        List<CouponDTO> couponDTOList = couponMap.get(user.getGoodsType());
        CouponDTO couponDTO = couponDTOList.get(0);

        // 优惠券数量校验
        if(couponDTO.getQuantity().get()<1){
            System.out.println(user.getUserName()+"买"+user.getGoodsId()+"，没有抢到"+user.getGoodsType()+"的优惠券");
            return;
        }
        // 优惠券时间区间校验
        /*if(couponDTO.getTradeDateStart().before(new Date)||couponDTO.getTradeDateEnd().after(new Date)){
            return;
        }*/

        // 交易交易金额校验
        if(user.getGoodsAmount().compareTo(couponDTO.getTradeAmount())>-1){

            // 扣减优惠券数量
            int quantity = couponDTO.getQuantity().decrementAndGet();
            if(quantity<0){
                System.out.println("=>"+user.getUserName()+"买"+user.getGoodsId()+"，没有抢到"+user.getGoodsType()+"的优惠券");
                return;
            }
            countCoupon(user.getGoodsType());
            System.out.println(user.getUserName()+"买"+user.getGoodsId()+",抢到一张 "+user.getGoodsType()+" 类型的优惠券"+"。该优惠劵剩余："+quantity+"张");

            // 优惠金额
            BigDecimal goodsAmount = user.getGoodsAmount();
            BigDecimal goodsRatioAmount = null;
            if(couponDTO.getDiscountType().equals("RATIO")){
                goodsRatioAmount = goodsAmount.multiply(couponDTO.getDiscountRatio());
            }
            if(couponDTO.getDiscountType().equals("FIXED")){
                goodsRatioAmount = goodsAmount.subtract(couponDTO.getDiscountMoney());
            }
            goodsRatioAmount = goodsRatioAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            user.setGoodsAmount(goodsRatioAmount);
            System.out.println("    "+user.getUserName()+"买"+goodsAmount+"元的"+user.getGoodsId()+"，优惠类型："+couponDTO.getDiscountType()+"，优惠劵之后的价格："+goodsRatioAmount+"元");

        }else {
            System.out.println("    "+user.getUserName()+"买"+user.getGoodsId()+"的交易额 不满足使用"+user.getGoodsType()+"优惠券条件的");
        }

    }

    /**
     * 统计优惠券的发放情况
     */
    private static void countCoupon(String goodsType) {

        if(goodsType.equals("FOOD")){
            foodCoupon.incrementAndGet();
        }
        if(goodsType.equals("CLOTH")){
            clothCoupon.incrementAndGet();
        }
        if(goodsType.equals("CAR")){
            carCoupon.incrementAndGet();
        }
    }

    /**
     *  模拟一个用户
     */
    private static User buildUser( int i) {
        // index
        String index = "" + i;

        // goodType
        List<String> goodsTypeList = GoodsTypeEnums.goodsTypeList();
        Random random = new Random();
        int n = random.nextInt(goodsTypeList.size());
        String goodsType = goodsTypeList.get(n);

        return User.builder().userId(index).userName(index).goodsId(index).goodsType(goodsType).goodsAmount(new BigDecimal(10000)).build();
    }

    /**
     *  初始化一组营销优惠券
     */
    public static void init(){

        // goodsType:coupon
        List<CouponDTO> couponDTOList = new ArrayList<>();
        CouponDTO couponDTO = CouponDTO.builder().
                couponId(1L).
                order(1).
                couponType("A").
                goodsType("FOOD").
                tradeAmount(new BigDecimal("100")).
                tradeDateStart(new Date()).
                tradeDateEnd(new Date()).
                discountType("FIXED").
                discountMoney(new BigDecimal("10")).
                discountRatio(null).
                quantity(new AtomicInteger(5)).
                build();
        couponDTOList.add(couponDTO);
        couponMap.put("FOOD",couponDTOList);

        List<CouponDTO> couponDTOList2 = new ArrayList<>();
        CouponDTO couponDTO2 = CouponDTO.builder().
                couponId(2L).
                order(1).
                couponType("B").
                goodsType("CLOTH").
                tradeAmount(new BigDecimal("500")).
                tradeDateStart(new Date()).
                tradeDateEnd(new Date()).
                discountType("RATIO").
                discountMoney(null).
                discountRatio(new BigDecimal(0.9)).
                quantity(new AtomicInteger(5)).
                build();
        couponDTOList2.add(couponDTO2);
        couponMap.put("CLOTH",couponDTOList2);

        List<CouponDTO> couponDTOList3 = new ArrayList<>();
        CouponDTO couponDTO3 = CouponDTO.builder().
                couponId(3L).
                order(1).
                couponType("C").
                goodsType("CAR").
                tradeAmount(new BigDecimal("1000")).
                tradeDateStart(new Date()).
                tradeDateEnd(new Date()).
                discountType("RATIO").
                discountMoney(null).
                discountRatio(new BigDecimal(0.9)).
                quantity(new AtomicInteger(5)).
                build();
        couponDTOList3.add(couponDTO3);
        couponMap.put("CAR",couponDTOList3);

    }
}
