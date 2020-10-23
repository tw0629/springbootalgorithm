package com.tian.algorithm.demo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-11 12:25
 */
public class RedPacket {

    public static void main(String[] args) {

        // 微信红包拆分测试
        double money = 10;  //单位（元）
        int num = 6;
        System.out.println("总金额："+money+"元\t 拆分红包总个数:" + num);
        int[]  array = divived(money,num);

        System.out.println("拆分后的各个红包金额数如下:");
        for (int i:array){
            System.out.print((double) i/100+"元\t");
        }
    }

    /****
     * 微信红包拆分方法
     * @param money  被拆分的总金额 (单位元)
     * @param n    被拆分的红包个数
     * @return  拆分后的每个红包金额数组
     */
    public static int[] divived(double money, int n){

        int fen = (int) (money*100);
        if(fen < n || fen < 1){
            throw new IllegalArgumentException("被拆分的总金额不能小于1分");
        }
        // 创建一个长度等于n的红包数组
        int[] array = new int[n];
        //第一步 每个红包先塞1分
        Arrays.fill(array,1);
        //总金额减去已分配的n 分钱
        fen -= n;
        //第二步，循环遍历如果剩余金额>0 则一直分配
        int i = 0;  //从第一个红包进行分配

        //创建一个随机分配对象
        Random random = new Random();
        while (fen > 1){
            int f  = random.nextInt(fen);  //创建范围[0,fen)
            array[i++%n] +=  f;
            fen -= f;
        }

        //判断剩余未分配的金额是否大于0,如果大于0，可以把剩下未分配金额塞到第一个红包中
        if (fen > 0){
            array[0] +=  fen;
        }

        return array;
    }

    public static int[] divived2(double money, int n){

        int fen = (int) (money*100);
        if(fen < n || fen < 1){
            throw new IllegalArgumentException("被拆分的总金额不能小于1分");
        }
        // 创建一个长度等于n的红包数组
        int[] array = new int[n];
        //第一步 每个红包先塞1分
        Arrays.fill(array,1);
        //总金额减去已分配的n 分钱
        fen -= n;
        //第二步，循环遍历如果剩余金额>0 则一直分配
        int i = 0;  //从第一个红包进行分配

        //创建一个随机分配对象
        Random random = new Random();
        while (fen > 0 && i != n){
            int f  = random.nextInt(fen);  //创建范围[0,fen)
            array[random.nextInt(n)] +=  f;
            i++;
            fen -= f;
        }
        //不判断。直接加
        array[random.nextInt(n)] += fen;

        return array;
    }
}
