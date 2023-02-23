package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/21 10:44
 */
public class a11_盛最多水的容器 {

    /**
     * 积水面积最大问题
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);

            if (height[l] <= height[r]) { // 关键
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
