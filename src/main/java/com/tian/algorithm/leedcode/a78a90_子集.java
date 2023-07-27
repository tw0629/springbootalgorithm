package com.tian.algorithm.leedcode;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/28 12:01
 */
public class a78a90_子集 {

    /**
     * 注意：
     *  子集I 和 子集II 是相通的, 就是在【子集I】逻辑上加上"剪枝的判断"
     *  等价的： for循环+一次递归 ====== 两次递归
     */

    /**
     *  78. 子集 (数组中的元素中互不相同，但子集不能重复)
     *  给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *  解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     *  链接：https://leetcode.cn/problems/subsets/solution/zi-ji-by-leetcode-solution/
     */
    /**
     *  方法一  推荐
     *  排序 + for循环 + 一次递归
     */
    public static List<List<Integer>> subsets11(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        dfs11(nums, 0, res, path);
        return res;
    }
    private static void dfs11(int[] nums, int index, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path)); // // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
        if(index == nums.length){
            return;
        }

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs11(nums,i+1,res,path);
            path.removeLast();
        }
    }

    static boolean[] used112;// 通过used数组标记是否访问过
    public static List<List<Integer>> subsets112(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        used112 = new boolean[nums.length];

        Arrays.sort(nums);
        dfs112(nums, 0, res, path);
        return res;
    }
    private static void dfs112(int[] nums, int index, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path)); // // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
        if(index == nums.length){
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if(used112[i]){
                continue;
            }

            path.add(nums[i]);
            used112[i]=true;
            dfs11(nums,i+1,res,path);
            path.removeLast();
            used112[i]=false;
        }
    }


    /**
     *  方法二 排序 + 两次递归
     */
    public static List<List<Integer>> subsets12(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        recursive12(result,queue,nums,0);
        return result;
    }
    private static void recursive12(ArrayList<List<Integer>> result, LinkedList<Integer> queue, int[] nums, int cur) {
        if (cur==nums.length){
            result.add(new ArrayList<>(queue)); // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
            return;
        }
        queue.offer(nums[cur]);
        recursive12(result, queue, nums, cur+1);
        queue.removeLast();
        recursive12(result, queue, nums, cur+1);

        //注意也可以改变顺序
        /*
        recursive2(result, queue, nums, cur+1);
        queue.offer(nums[cur]);
        recursive2(result, queue, nums, cur+1);
        queue.removeLast();
        */
    }

    /**
     * 方法三
     * 中序遍历写法
     */
    public static List<List<Integer>> subsets13(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        Arrays.sort(nums);
        dfs13(Ints.asList(nums), res, path, 0);
        return res;
    }
    private static void dfs13(List<Integer> list, List<List<Integer>> res, List<Integer> path, int level)
    {
        if(level == list.size()) {
            res.add(path);
        } else {
            dfs13(list, res, new ArrayList<>(path), level + 1);
            path.add(list.get(level));
            dfs13(list, res, new ArrayList<>(path), level + 1);
        }
    }


    /**
     *  90. 子集 II (数组中的元素含重复元素，但子集不能重复)
     *  给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *  解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *  链接：https://leetcode.cn/problems/subsets/solution/zi-ji-by-leetcode-solution/
     *
     *
     *  回溯：
     *  方式一：1 排序; 2 标识数组; 3 for循环+剪枝判断; 4 只有"一次"递归;
     *  方式二：1 排序; 2 for循环+剪枝判断; 3 只有"一次"递归;
     *  方式三：1 排序; 2 剪枝判断; 3 有"两次"递归;
     *
     *  注意写法：
     *      注意1 必粗写成result.add(new ArrayList<>(path))。 不能写成ans.add(perm); 因为每次都会remove掉。
     *      注意2 非必要写，只有无序元素，才写Arrays.sort(nums)排序;
     *      注意3 非必要写，只有重复元素，才这样写
    *             if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
    *                 continue;
    *             }
     *
     */
    static boolean[] used;
    public static List<List<Integer>> subsetsWithDup21(int[] nums) {
        if (nums.length == 0){
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
        LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
        used = new boolean[nums.length];

        Arrays.sort(nums);
        df21(nums, 0, result, path);
        return result;
    }
    private static void df21(int[] nums, int startIndex, List<List<Integer>> result, LinkedList<Integer> path){
        result.add(new ArrayList<>(path)); // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
        if (startIndex == nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            if (used[i] || i > startIndex && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            df21(nums, i + 1, result, path);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * https://leetcode.cn/problems/subsets-ii/solution/90-zi-ji-iiche-di-li-jie-zi-ji-wen-ti-ru-djmf/
     *
     */
    public static List<List<Integer>> subsetsWithDup22(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        dfs22(nums, 0, res, path);
        return res;
    }
    private static void dfs22(int[] nums, int index, List<List<Integer>> res, LinkedList<Integer> path) {
        res.add(new ArrayList<>(path)); // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
        if(index == nums.length){
            return;
        }

        for (int i = index; i < nums.length; i++) {
            /*去重的重要步骤 保留第一次的相同值 跳过同一层的相同值*/
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            dfs22(nums,i+1,res,path);
            path.removeLast();
        }
    }

    /**
     *
     *  链接：https://leetcode.cn/problems/subsets-ii/solution/zi-ji-ii-by-leetcode-solution-7inq/
     *
     */
    public static List<List<Integer>> subsetsWithDup23(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        dfs23(false, 0, nums,ans,t);
        return ans;
    }
    public static void dfs23(boolean choosePre, int cur, int[] nums, List<List<Integer>> ans, List<Integer> t) {
        // 是不是choosePre 就和 标记used 一样的效果？？

        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t)); // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
            return;
        }
        // 不考虑放入cur位置的数
        dfs23(false, cur + 1, nums,ans,t);
        // 考虑当前位置
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs23(true, cur + 1, nums,ans,t);
        t.remove(t.size() - 1);
    }


    public static void main(String[] args) {

        int[] a0 = {1,2,3};
        System.out.println(subsets11(a0));
        System.out.println(subsets112(a0));
        System.out.println(subsets12(a0));
        System.out.println(subsets13(a0));
        System.out.println(subsetsWithDup22(a0));
        System.out.println();

        int[] a = {1,2,2,3};
        System.out.println(subsetsWithDup21(a));
        //System.out.println(subsetsWithDup22(a));
        //System.out.println(subsetsWithDup23(a));
        System.out.println();
    }

}
