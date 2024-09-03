package com.tian.algorithm.leedcode;

import java.util.*;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/27 09:39
 */
public class a57_插入区间 {

    /**
     * 方法二
     *
     * |_____| 即：   0_____1
     * |_____| 即：left_____right
     *
     *  第一轮
     *  intervals:      |_____| |_____| |_____| |_____| |_____| |_____| 从左到右，从小到大
     *  newInterval:    |_____|
     *
     */
    public static int[][] insert2(int[][] intervals, int[] newInterval) {

        // 若无序， Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);

        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});//起初
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集       // 注意：有交集时，要重新计算left,right
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }


        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr0 = {{12,33},{43,45},{53,65},{66,77}};
        int[] arr02 = {45,60};
        int[][] insert0 = insert2(arr0, arr02);

        // 说明arr数组不能有重叠
        int[][] arr = {{12,33},{32,45},{53,65},{66,77}};
        int[] arr2 = {41,60};

        int[][] insert = insert2(arr, arr2);

        System.out.println(insert);
    }



}
