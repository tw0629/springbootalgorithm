package com.tian.algorithm.Z_inter.other;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/2 01:16
 */
public class DynamicPlan {

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
}
