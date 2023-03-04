package com.tian.algorithm.Z_test;


import java.util.*;

/**
 * @author David Tian
 * @desc
 * @since 2022/12/8 11:27
 */
public class 权重轮训 {

    // 推荐 方法二
    // 推荐 方法二
    // 推荐 方法二

    //https://blog.csdn.net/qq_40083897/article/details/126969186

    private int[] weights;
    private int[] indexs;
    private int[] startWeights;

    public int choseIndex(){
        int weightRange = weights[0];
        startWeights[0] = 0;
        for (int i = 1; i < weights.length; i++) {
            startWeights[i] = weightRange + startWeights[i-1];
            weightRange += weights[i];
        }

        int sum=0;
        for (int i = 0; i < weights.length; i++) {
            sum+=weights[i];
        }

        Random random = new Random(sum);
        for (int i = 0; i < weights.length; i++) {
            if(startWeights[i]<=random.nextInt()){
                return indexs[i];
            }
        }

        return -1;
    }

    public static String getServerByWeight(Map<String, Integer> map) {
        if (map.isEmpty()){
            return null;
        }
        Integer total = 0;
        // 计算所有权重，如100+200+300=600
        for (Integer value : map.values()) {
            total += value;
        }

        Random random = new Random();
        // 在权重范围内随机，600以内随机
        int nextInt = random.nextInt(total);
        // 遍历所有服务提供者provide的ip地址
        for (String ip : map.keySet()) {
            // 取出权重值
            Integer weight = map.get(ip);

            // 权重在范围内，则返回对应ip
            if (nextInt < weight) {
                return ip;
            }
            // 否则减去权重，继续下一次循环，匹配对应的ip
            nextInt -= weight;
        }
        return null;
    }

    // 推荐 方法二
    // 原文链接：https://blog.csdn.net/qq_34827263/article/details/103863213
    public static String getServerByWeight2(Map<String ,Integer> map){
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Random random = new Random();
        int sum=0;
        for (Map.Entry<String, Integer> entry : entries) {
            Integer value = entry.getValue();
            sum+=value;
        }
        int randomInt = random.nextInt(sum)+1;

        int sum_temp = 0;
        for (Map.Entry<String, Integer> entry : entries) {
            Integer value = entry.getValue();
            sum_temp += value;
            if (randomInt <= sum_temp){
                return entry.getKey();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("192.168.1.1", 10);
        map.put("192.168.1.2", 20);
        map.put("192.168.1.3", 30);
        map.put("192.168.1.4", 40);
        System.out.println("map:"+map.toString());


        int sum200003=0;
        int sum200004=0;
        int sum200005=0;
        int sum200006=0;
        for (int i = 0; i < 1000; i++) {
            String ip = getServerByWeight(map);
            //String ip = getServerByWeight2(map);

            if("192.168.1.1".equals(ip)){
                sum200003++;
            }
            if("192.168.1.2".equals(ip)){
                sum200004++;
            }
            if("192.168.1.3".equals(ip)){
                sum200005++;
            }
            if("192.168.1.4".equals(ip)){
                sum200006++;
            }
        }
        System.out.println("sum200003:"+sum200003);
        System.out.println("sum200004:"+sum200004);
        System.out.println("sum200005:"+sum200005);
        System.out.println("sum200006:"+sum200006);

        System.out.println();

    }


}
