package com.tian.algorithm.leedcode;

import java.util.Arrays;

/**
 * @author David Tian
 * @desc
 * @since 2023/3/28 15:45
 */
public class a_最长递增子序列 {

    /**
     *  最长递增子序列  （是序列，可不连续）
     *
     *  链接：https://leetcode.cn/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     *
     *  dp[i] 的值代表 nums 以nums[i] 结尾的最长子序列长度;
     *  dp[i] 所有元素置1，含义是每个元素都至少可以单独成为子序列，此时长度都为1。
     */
    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = 0;

        Arrays.fill(dp, 1);//!!!

        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // ❌ 错的，是最长 递增'子序列'
    public static int lengthOfLIS2(int[] nums) {
        if(nums.length == 0) return 0;
        int max = 0;
        int len = 1;

        for(int i = 0; i < nums.length-1; i++) {
            if(nums[i] < nums[i+1]){
                len++;
                max = Math.max(max, len);
            }else {
                len = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] nums = {1,2,4,5,1,2,6,2,1,2,3};
        System.out.println();
        System.out.println("lengthOfLIS: "+lengthOfLIS(nums));
        System.out.println();
        System.out.println("lengthOfLIS2: "+lengthOfLIS2(nums));
    }
}
