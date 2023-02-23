package com.tian.algorithm.leedcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/27 09:39
 */
public class a57_插入区间 {

    /**
     * 方法二
     */
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
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
                // 与插入区间有交集，计算它们的并集
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
        int[][] arr = {{12,33},{43,45},{53,65},{66,77}};
        int[] arr2 = {5,88};

        int[][] insert = insert2(arr, arr2);

        System.out.println(insert);
    }



}
