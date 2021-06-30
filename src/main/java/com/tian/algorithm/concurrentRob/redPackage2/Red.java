package com.tian.algorithm.concurrentRob.redPackage2;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author David Tian
 * @desc
 * @since 2020-09-03 10:24
 */
public class Red {
    
    public static double getRandomMoney(RedPackage _redPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_redPackage.remainSize == 1) {
            _redPackage.remainSize--;
            return (double) Math.round(_redPackage.remainMoney * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01; //
        double max   = _redPackage.remainMoney / _redPackage.remainSize * 2;
        //返回一个大于或等于0.0且小于1.0的随机浮点数
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        _redPackage.remainSize--;
        _redPackage.remainMoney -= money;
        return money;
    }
    //LeftMoneyPackage数据结构如下：

    class RedPackage {
        int    remainSize;
        double remainMoney;

        RedPackage(int remainSize, double remainMoney) {
            this.remainSize = remainSize;
            this.remainMoney = remainMoney;
        }
    }

    //测试时初始化相关数据是：
    public RedPackage init(int size,double money) {
        return new RedPackage(size,money);
    }

    public static void main(String[] args) {

        RedPackage redPackage = new Red().init(10, 100);
        //RedPackage redPackage = null;

        double total = 0.0;
        for(int i = 0; i<10; i++){
            double randomMoney = getRandomMoney(redPackage);
            total = add(total,randomMoney);
            System.out.println(i+" 抢了 "+randomMoney+" 元");
        }
        System.out.println(" 红包总额: " + total);


    }

    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
}
