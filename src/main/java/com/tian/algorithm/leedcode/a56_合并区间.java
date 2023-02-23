package com.tian.algorithm.leedcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/26 18:23
 */
public class a56_合并区间 {

    /**
     * 1 先按照区间起始位置排序
     * 2 因为已经排好序了，直接合并/比较相邻两个就行
     *
     */
    public static int[][] merge(int[][] intervals) {
        // 1 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        //Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        /*Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });*/

        // 2 遍历区间   !!!注意：因为已经排好序了，直接比较相邻两个就行
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // !!!
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                // !!! 因为以及排好序了，
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }


    public static void main(String[] args) {
        int[][] array20={{1,3},{2,5},{7,9},{8,10}};
        System.out.println(Arrays.deepToString(merge(array20)));

    }

}
