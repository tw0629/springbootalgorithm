package com.tian.algorithm.Z_test;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Tian {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        waterSum(height);
    }

    public static void  waterSum(int[] height){
        int length = height.length;

        int[] dp1 = new int[length];
        int[] dp2 = new int[length];

        dp1[0] = height[0];
        dp1[0] = height[0];
        dp2[length-1] = height[length-1];
        dp2[length-1] = height[length-1];

        for(int i=1;i<length-1;i++){
            dp1[i] = Math.max(dp1[i-1],height[i-1]);
            dp2[length-1-i] = Math.max(dp2[length-1-i+1],height[length-1-i+1]);
        }

        int waterSum = 0;
        for(int i=1;i<length-1;i++){
            if(dp2[i]>height[i] && dp1[i]>height[i]){
                waterSum += dp1[i] >= dp2[i]?dp2[i]-height[i]:dp1[i]-height[i];
            }
        }

        System.out.println("======>"+waterSum);
    }


}
