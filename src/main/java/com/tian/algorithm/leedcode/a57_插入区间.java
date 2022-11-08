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
     * 方法一 有点问题  自己实现
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int i = 0;
        int m = 0;
        int n = 0;
        for (int[] interval: intervals) {
            if(interval[0]<=newInterval[0] && newInterval[0]<=interval[1]){
                m = i;
            }
            if(interval[0]<=newInterval[1] && newInterval[1]<=interval[1]){
                n = i;
            }
            if(n!=0&&m==n){
                break;
            }
            i++;
        }
        // 没考虑 最左，最右，中间不交

        if(m==n){
            return intervals;
        }

        int[][] merge = new int[intervals.length][];
        int j = 0;
        int t = 0;
        int left = 0;
        int right = 0;
        for (int[] interval: intervals) {
            if(m<=j && j<=n){
                left=left==0?interval[0]:left;
                left = Math.min(left,interval[0]);
                right = Math.max(right,interval[1]);
                merge[t] = new int[]{left, right};
            }else {
                if(j!=0){
                    ++t;
                }
                merge[t] = interval;
            }
            j++;
        }
        return merge;
    }

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
                    ansList.add(new int[]{left, right});
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
