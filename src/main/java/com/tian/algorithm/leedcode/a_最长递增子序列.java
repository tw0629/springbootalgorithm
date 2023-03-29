package com.tian.algorithm.leedcode;

import java.util.Arrays;

/**
 * @author David Tian
 * @desc
 * @since 2023/3/28 15:45
 */
public class a_最长递增子序列 {

    /**
     *  最长递增子序列
     *
     *  链接：https://leetcode.cn/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     *
     *  dp[i] 的值代表 nums 以nums[i] 结尾的最长子序列长度;
     *  dp[i] 所有元素置1，含义是每个元素都至少可以单独成为子序列，此时长度都为1。
     */
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;

        Arrays.fill(dp, 1);//!!!

        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
