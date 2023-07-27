package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/21 12:20
 */
public class a42_接雨水 {

    /**
     * 方法一
     * 积水问题   和最大问题
     * https://blog.csdn.net/qq_42247231/article/details/106482591
     */
    public static int waterSum(int[] height){
        int ans = 0;
        int n = height.length;

        int l_max = height[0];
        int r_max = height[n - 1];

        int left = 0;
        int right = n - 1;

        while (left <= right)
        {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // 每次较低台阶的指针移动一格
            if (l_max <= r_max)
            {
                ans += l_max - height[left];
                ++left;
            }
            else
            {
                ans += r_max - height[right];
                --right;
            }
        }

        return ans;
    }

    /**
     * 方法二
     * 积水问题2  有问题？？待调整
     *
     * 自己思考的出的
     * 思路类似：连续子数组的最大和
     */
    public static int waterSum2(int[] arr) {
        // write code here
        // dp[i]代表到第i位的时侯,以arr[i]结尾的连续子数组最大累加和
        // 开辟dp
        int[] dp = new int[arr.length];
        int[] dp2 = new int[arr.length];

        //初始化
        dp[0] = arr[0];
        dp[arr.length-1] = arr[arr.length-1];
        dp2[0] = arr[0];
        dp2[arr.length-1] = arr[arr.length-1];

        // 遍历,找到每个位置的左边最大dp 和 右边最大dp2
        for (int i = 1; i < arr.length-1; i++) {

            // 方式一
            dp[i] = Math.max(dp[i-1],arr[i]);
            dp2[arr.length-1-i] = Math.max(dp2[arr.length-1-i+1],arr[arr.length-1-i]);

            // 方式二
            //dp1[i] = Math.max(dp1[i-1],arr[i-1]);
            //dp2[length-1-i] = Math.max(dp2[length-1-i+1],arr[length-1-i+1]);
        }

        int waterSum = 0;
        for (int i = 1; i < arr.length-1; i++) {
            // 上面方式一二都可以,是因为累加的时候 必须是dp[i]>arr[i] && dp2[i]>arr[i]
            if(dp[i]>arr[i] && dp2[i]>arr[i]){
                waterSum += dp[i]<=dp2[i]?(dp[i]-arr[i]):(dp2[i]-arr[i]);
            }
        }

        return waterSum;
    }

    // 同方法二
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
