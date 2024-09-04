package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2023/3/29 08:47
 */
public class a_最长子数组 {

    // 区别： 最大的子数组和 和 和最大的最长子数组
    // com.tian.algorithm.leedcode.a53_最大子数组和

    /**
     * 和最大的最长子数组
     * 和最大的最长子数组
     * 和最大的最长子数组
     */
    public static int[] maxSumSubarray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int start = 0, end = 0, tempStart = 0;

        for (int i = 0; i < arr.length; i++) {
            if (currentSum + arr[i] > arr[i]) {
                currentSum += arr[i];
            } else {
                currentSum = arr[i];
                tempStart = i;
            }

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
