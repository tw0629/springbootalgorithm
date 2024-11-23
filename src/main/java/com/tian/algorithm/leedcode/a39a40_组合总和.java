package com.tian.algorithm.leedcode;

import org.junit.Test;

import java.util.*;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/28 11:16
 */
public class a39a40_组合总和 {

    /**
     * 39. 组合总和  (无限制重复)
     *
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 链接：https://leetcode.cn/problems/combination-sum
     *
     * 产生重复的原因是：
     *    在每一个结点，做减法，展开分支的时候，
     *    由于题目中说 每一个元素可以重复使用，我们考虑了 所有的 候选数，因此出现了重复的列表。
     *
     */
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();// 应该和List效果一样

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        dfs1(candidates, 0, len, target, path, res);
        return res;
    }

    private static void dfs1(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {  // !!!!!!
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {  // !!!!!!
                break;  // !!!!!!
            }

            path.addLast(candidates[i]);
            //System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            dfs1(candidates, i+1, len, target - candidates[i], path, res);
            path.removeLast();
            //System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 40. 组合总和 II  (只能使用一次)
     * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     * candidates中的每个数字在每个组合中只能使用一次。
     *
     * 链接：https://leetcode.cn/problems/combination-sum-ii
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>(len);

        // 关键步骤
        Arrays.sort(candidates);
        dfs2(candidates, 0, target, res, path);
        return res;
    }
    private static void dfs2(int[] candidates, int begin, int target, List<List<Integer>> res, Deque<Integer> path) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {


            // 小剪枝：同一层相同数值x                                  结点，从 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            // 调试语句 ①
            // System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs2(candidates, i + 1, target - candidates[i], res, path);

            path.removeLast();
            // 调试语句 ②
            // System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }
    }


    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        int[] arr = { 1, 3, 2, 4, 5, 6, 7, 8, 9 };
        //System.out.println(combinationSum1(a,8));

        int[] a2= {1,2,3,4,5,6,7,1,6,3,4};
        int[] a3= {1,2,2,3};
        //System.out.println(combinationSum2(a3,5));
        System.out.println();

        int[] nums = {3,7,3,2,5,1,4,3,7,1,9};
        //int[] nums = {3,2,1,5,4,3,7,9};
        List<List<Integer>> res = sequenceChildSum_equal_target2(nums, 10);
        System.out.println(res);
    }


    /**
     * 变形：从数组中找出和为指定值的连续子组合
     * 推荐方法二
     * eg 3,2,1,5,4,3,7,9
     */
    // 方法二
    public static List<List<Integer>> sequenceChildSum_equal_target2(int[] nums,int target){
        List<List<Integer>>  res = new ArrayList<>();
        int sum = 0;
        int start = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];

            while (sum>=target){ // !!!!!!
                if(sum == target){
                    List<Integer> result = new ArrayList<>();
                    for(int j=start;j<=i;j++){ //!!!!!!注意：从start开始
                        result.add(nums[j]);
                    }
                    res.add(result);

                    break; // !!!!!!
                }else {
                    sum -= nums[start++]; // !!!!!! 注意：从start开始， 删除最前面的
                }
            }
        }
        return res;
    }

    // 方法一  （？？好像只适用在全为正数时，有负数会漏掉）
    public static List<List<Integer>> sequenceChildSum_equal_target(int[] nums,int target){
        List<List<Integer>>  res = new ArrayList<>();
        int sum = 0;
        int start = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];

            if(sum == target){
                List<Integer> result = new ArrayList<>();
                for(int j=start;j<=i;j++){
                    result.add(nums[j]);
                }
                res.add(result);
            } else  if(sum>target){
                // 3,2,1,5,4,3,7,9
                while (sum>target){
                    sum -= nums[start++];
                    if(sum == target){
                        List<Integer> result = new ArrayList<>();
                        for(int j=start;j<=i;j++){
                            result.add(nums[j]);
                        }
                        res.add(result);
                    }
                }
            }

        }

        return res;
    }

}
