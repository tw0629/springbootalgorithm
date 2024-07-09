package com.tian.algorithm.classical.concurrentRob.redPacket;


import java.util.Random;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-11 14:27
 */
public class Demo {

    static class RedPackage{

        int remianSize;
        double remianMoney;

        public RedPackage(int remianSize, double remianMoney) {
            this.remianSize = remianSize;
            this.remianMoney = remianMoney;
        }

        public int getRemianSize() {
            return remianSize;
        }

        public void setRemianSize(int remianSize) {
            this.remianSize = remianSize;
        }

        public double getRemianMoney() {
            return remianMoney;
        }

        public void setRemianMoney(double remianMoney) {
            this.remianMoney = remianMoney;
        }
    }

    public static double getRandom(RedPackage redPackage){

//        int remianSize ;
//        double remianMoney ;

        if(redPackage.getRemianSize() == 1){

            redPackage.remianSize--;
            return (double) Math.round(redPackage.getRemianMoney() * 100)/100;
        }

        Random r = new Random();
        double min = 0.01;
        //使用剩余总金额平均数来控制波动，改为使用平均数的某个倍数(限制为0.01 ~ 平均值 *n之间波动)
        //注意：是剩余总金额平均数-不是总金额平均数
        double max = redPackage.getRemianMoney()/redPackage.remianSize*3;
        //nextDouble()方法用于获取下一个从这个伪随机数生成器的序列中均匀分布的0.0和1.0之间的double值。
        double money = r.nextDouble() * max;

        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;

        redPackage.remianSize--;
        redPackage.remianMoney-=money;

        return money;
    }

    public static void main(String[] args) {

        RedPackage redPackage = new RedPackage(10,100);

        int total = redPackage.getRemianSize();
        double[] a = new double[total];

        for(int i=0; i< total; i++){
            double random = getRandom(redPackage);
            a[i] = random;
            System.out.println("=========>"+random);
        }

        System.out.println("===a[10]==="+a);
        double sum = 0.0;
        for (int i=0; i< total; i++){
            sum += a[i];
        }
        System.out.println("====红包总和=====>"+sum);
    }


}
