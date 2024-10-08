package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2022/10/26 18:03
 */
public class a55_跳跃游戏 {

    // 本题目关键是 if(i<=rightmost) ==【 i <= i-1 + nums[i-1] 或者 其他之前的rightmost 】
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) { // 关键!!! i <= rightmost   【当前的位置是之前的能到达的】
                rightmost = Math.max(rightmost, i + nums[i]); // i位置可以跳跃到的位置i + nums[i]
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     *
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     */
    // 这个好理解
    public boolean canJump2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }

}
