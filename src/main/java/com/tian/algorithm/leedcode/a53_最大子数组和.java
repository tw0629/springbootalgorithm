package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/25 20:07
 */
public class a53_最大子数组和 {

    // 区别： 最大的子数组和 和 和最大的最长子数组

    /**
     * 最大的子数组和
     */
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


    /**
     * 和最大的最长子数组
     */
    public static int[] maxSumSubarray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int start = 0, end = 0, tempStart = 0;

        for (int i = 0; i < arr.length; i++) {
            // 相当于拆 currentSum = Math.max(currentSum + arr[i], arr[i]);  记录:局部-tempStart
            if (currentSum + arr[i] > arr[i]) {
                currentSum += arr[i];
            } else {
                currentSum = arr[i];
                tempStart = i;
            }

            // 相当于拆 maxSum = Math.max(maxSum, currentSum); 记录：最大的起终-start，end
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            } else if (currentSum == maxSum) {
                // 如果当前和等于最大和，但当前子数组更长
                if (i - tempStart > end - start) {  // ??? 那要是多个[最大和相等的子数组]的呢， 目前是只记录第一个段
                    start = tempStart;
                    end = i;
                }
            }
        }

        // 返回和最大的最长子数组
        int[] result = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            result[i - start] = arr[i];
        }
        return result;
    }

}


