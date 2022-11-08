package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/25 20:07
 */
public class a53_最大子数组和 {

    public static void sumMaxChild(int[] a) {
        //max就是上面的dp[i]
        // 这个时候用一个变量temp就可以了, 不必要搞个dp[]数组的
        int temp = a[0];
        //因为这个dp[i]老是变，所以比如你dp[4]是8 dp[5]就变成-7了，所以需要res保存一下
        int sumMax = a[0];
        for (int i = 1; i < a.length; i++) {
            //dp[i] = Math.max(dp[i-1] + a[i], a[i]); 没必要这么写
            temp = Math.max(temp + a[i], a[i]);
            sumMax = Math.max(sumMax, temp);
        }
        System.out.println("sumMaxChild3: "+ sumMax);
    }

    public int maxMaxArray(int[] nums) {
        int n=nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        for(int i=1;i<n;i++){
            f[i] = Math.max(nums[i]+f[i-1], nums[i]);
        }
        int ans=f[0];
        for(int i=0;i<n;i++){
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

}


