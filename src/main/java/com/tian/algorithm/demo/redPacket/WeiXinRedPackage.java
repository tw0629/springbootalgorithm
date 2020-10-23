package com.tian.algorithm.demo.redPacket;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-12 09:03
 */
public class WeiXinRedPackage{

    public static void main(String[] args) {
        double sum = 0;
        ArrayList<Double> res
                = WXRedPackageAlgorithm(10,3);

        for(double money:res) {
            sum += money;
            System.out.print(money +" ");
        }

        System.out.println();
        System.out.println(sum);
    }

    private static ArrayList<Double> WXRedPackageAlgorithm(double restMoney,int restNum){
        ArrayList<Double> res
                = new ArrayList<>(restNum);

        Random random=new Random();
        while(restNum>1) {
            //最大的红包为：两倍的平均红包大小
            double max=(restMoney/restNum) * 2;

            //产生[0,1)之间的随机数
            double r=random.nextDouble();

            //抢到的红包区间[0,max)
            double money = r * max;
            if(money<0.01) {
                money = 0.01;
            }else {
                money = Math.floor(money * 100) / 100;
            }

            res.add(money);

            restNum--;
            restMoney -= money;
        }

        //最后一个红包为剩余余额
        res.add(Math.floor(restMoney*100)/100 );
        return res;
    }

}
