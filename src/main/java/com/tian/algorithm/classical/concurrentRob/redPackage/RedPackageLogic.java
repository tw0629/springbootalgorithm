package com.tian.algorithm.classical.concurrentRob.redPackage;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author David Tian
 * @desc
 * //评测题目: 用java语言实现随机金额群红包，包含红包创建、红包领取、查看领取记录三个功能，
 * 随机金额群红包指发送人指定红包码总金额和个数，领取人根据手气随机领到一定金额的红包。
 * @since 2020-09-02 20:07
 */
public class RedPackageLogic {

    public static Map<String, Double> map = new ConcurrentHashMap<>();

    public static List<RedPackageRecord> list = new LinkedList<>();


    /**
     * 1 红包创建
     */
    public  static RedPackage createRedPackage(double money, int size){
        return new RedPackage(size,money);
    }

    /**
     * 2 红包领取
     */
    public synchronized static double getRandomMoney(String userId, RedPackage redPackage) {

        if(redPackage.getSize()<1){
            return 0;
        }

        int remianSize = redPackage.getSize();
        double remianMoney = redPackage.getMoney();

        if (redPackage.getSize() == 1) {
            //redPackage.getSize()--;
            redPackage.setSize(remianSize-1);
            double lastRemianMoney = (double) Math.round(redPackage.getMoney() * 100) / 100;
            //记录红包领取
            map.put(userId,lastRemianMoney);
            list.add(new RedPackageRecord(userId,lastRemianMoney,System.currentTimeMillis()));

            return lastRemianMoney;
        }
        Random r     = new Random();
        double min   = 0.01;
        double max   = redPackage.getMoney() / redPackage.getSize() * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        //redPackage.getSize()--;
        //redPackage.getMoney() -= money;
        redPackage.setSize(remianSize-1);
        redPackage.setMoney(remianMoney-money);

        //记录红包领取
        map.put(userId,money);
        list.add(new RedPackageRecord(userId,money,System.currentTimeMillis()));

        return money;
    }

    /**
     * 3 查看领取记录
     */
    public static List queryRedPackageList(){
        return list;
    }


    public static void main(String[] args) {

        //1 发一个红包
        RedPackage redPackage = createRedPackage(100, 10);

        //2 模拟多人抢红包
        for(int i = 0; i < 150; i++){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String userId = Thread.currentThread().getName();
                    //抢一个红包
                    double money = getRandomMoney(userId, redPackage);

                    System.out.println(showRedPackage(userId,money));
                }
            }).start();
        }

        //3 查看领取记录
        List list = queryRedPackageList();
        System.out.println(" 领取记录: " + list);


        //检测
       /* double total = 0.0;
        for(Object key : map.keySet()){
            System.out.println("total:"+total+"  "+"key:"+key+"-"+"value:"+(double)map.get(key));
            total = add(total, (double)map.get(key));
            System.out.println("    ");
        }
        System.out.println(" 红包总额: " + total);*/
    }

    public static double add(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static String showRedPackage(String userId, Double money){
        //note 友好提示
        String rob = userId+" 抢了 "+money+" 元";
        String notRob = userId+" 红包已经抢完了";
        String message = (money==0)?notRob:rob;
        return message;
    }


}
