package com.tian.algorithm.Z_inter.other;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/2 01:16
 */
public class DynamicProgram {

    /**
     * 斐波拉契
     */
    public int Fibonacci(int n) {
        int ans[] = new int[40];
        ans[0] = 0;
        ans[1] = 1;

        for(int i=2; i<=n; i++){
            ans[i] = ans[i-1] + ans[i-2];
        }
        return ans[n];
    }

    /**
     * 跳台阶
     */
    public int jumpFloor(int target) {
        if(target==1) {
            return 1;
        } else if(target==2) {
            return 2;
        }
        return jumpFloor(target-1)+jumpFloor(target-2);
    }


    /**
     * 积水问题
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

        // 上面方式一二都可以,是因为累加的时候 必须是dp[i]>arr[i] && dp2[i]>arr[i]
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

    public static void main(String[] args) {

        //int[] a = {4,2,0,3,2,5};
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        int waterSum = waterSum(a);
        int waterSum2 = waterSum2(a);
        System.out.println("=======>"+waterSum);
        System.out.println("=======>"+waterSum2);
        System.out.println("");
    }
}
