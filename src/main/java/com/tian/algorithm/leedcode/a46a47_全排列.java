package com.tian.algorithm.leedcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/27 17:22
 */
public class a46a47_全排列 {

    /**
     * 全排列一 没有重复元素的全排列
     *
     * 详情：com.tian.algorithm.Z_inter.array.ArrayOp#permutation2(int[], int)
     *
     */
    /**
     * 方法一 回溯+剪枝
     */
    static boolean[] used;// 通过used数组标记是否访问过
    public static List<List<Integer>> permutation11(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        used = new boolean[len];

        dfs11(nums, len, 0, res, path);
        return res;
    }
    private static void dfs11(int[] nums, int len, int depth, List<List<Integer>> res, List<Integer> path) {

        if (depth == len) {
            res.add(new ArrayList<>(path)); // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
            //System.out.println(path);
            return;
        }

        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            path.add(nums[i]);
            used[i] = true;
            //System.out.println("  递归之前 => " + path);
            dfs11(nums, len, depth + 1, res, path);
            // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
            used[i] = false;
            path.remove(path.size() - 1); // 移出最后一个
            //System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 好像有问题
     */
    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> dfs13( int[] arr,List<Integer> list){
        List<Integer> temp = new ArrayList<>(list);
        if (arr.length == list.size()){
            res.add(temp);
        }
        for (int i=0;i<arr.length;i++){
            if (temp.contains(arr[i])){
                continue;
            }
            temp.add(arr[i]);
            dfs13(arr,temp);
            temp.remove(temp.size()-1);
        }
        return res;
    }

    /**
     * 方法二
     */
    public static void permutation12(int[] chs,int start ) {
        if(start==chs.length-1) {
            //System.out.println(Arrays.toString(chs));
            //如果已经到了数组的最后一个元素，前面的元素已经排好，输出。
        }
        for(int i=start;i<=chs.length-1;i++) {
            //把第一个元素分别与后面的元素进行交换，递归的调用其子数组进行排序
            Swap(chs,i,start);
            //System.out.println("  Swap "+i+" "+start+" "+Arrays.toString(chs));
            permutation12(chs,start+1);
            Swap(chs,i,start);
            //System.out.println("  Swap回去 "+i+" "+start+" "+Arrays.toString(chs));// 要关注这个打印

            //System.out.println("======");

            //子数组排序返回后要将第一个元素交换回来。
            //如果不交换回来会出错，比如说第一次1、2交换，第一个位置为2，子数组排序返回后如果不将1、2
            //交换回来第二次交换的时候就会将2、3交换，因此必须将1、2交换, 使1还是在第一个位置;
        }

        //System.out.println("######");
    }
    public static void Swap(int[] chs,int i,int j) {
        int temp;
        temp=chs[i];
        chs[i]=chs[j];
        chs[j]=temp;
    }


    // =================================================================================
    // =================================================================================
    // =================================================================================


    /**
     * 全排列二  含重复元素的全排列
     * 
     * I 根据上述分析，在排序后的数组   nums 中遍历到下标 ii 时，以下两种情况不应将nums[i] 加入当前排列。
     *  1 如果nums[i] 已经加入当前排列，则不能多次加入当前排列。
     *  2 如果当i>0 时，nums[i]=nums[i−1] 且 nums[i−1] 未加入当前排列，
     *      则不能将nums[i] 加入当前排列，否则nums[i−1] 将在nums[i] 之后加入当前排列，导致出现重复排列。
     *
     * II 其余情况下，执行如下操作。
     *  1 将   [i]nums[i] 加入当前排列，并将该元素的状态更新为已经加入当前排列，然后继续回溯。
     *  2 将当前排列的末尾元素（即   [i]nums[i]）移除，并将该元素的状态更新为未加入当前排列，恢复到原始状态。
     * 遍历结束时，即可得到数组   nums 的无重复全排列。
     *
     */
    static boolean[] visited;
    public static List<List<Integer>> permuteUnique21(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();// 放所有的子排列
        List<Integer> perm = new ArrayList<Integer>();//每个子排列
        visited = new boolean[nums.length];//每个位置的元素是否访问过  boolean默认值是false

        Arrays.sort(nums); //排序
        backtrack(nums, ans, 0, perm);
        return ans;
    }
    public static void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm)); // !!!注意：不能写成ans.add(perm); 因为每次都会remove掉
            return;
        }
        for (int i = 0; i < nums.length; ++i) { // 此时每次都是从i=0开始的
            // 剪枝条件 去重
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }

            perm.add(nums[i]);
            visited[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            visited[i] = false; // 后两行应该没有顺序
            perm.remove(idx);
        }
    }


    public static void main(String[] args) {

        int[] a = {1,2,3};
        //permutation12(a,0);
        System.out.println();
        System.out.println(permutation11(a));
        //System.out.println(dfs13(a,new ArrayList<>()));
        System.out.println("============================");

        int[] a1 = {1,2,2};
        System.out.println(permuteUnique21(a1));
        System.out.println();
    }


}
//
//              递归之前 => [1]
//              递归之前 => [1, 2]
//              递归之前 => [1, 2, 3]
//          递归之后 => [1, 2]
//          递归之后 => [1]
//              递归之前 => [1, 3]
//              递归之前 => [1, 3, 2]
//          递归之后 => [1, 3]
//          递归之后 => [1]
//          递归之后 => []
//              递归之前 => [2]
//              递归之前 => [2, 1]
//              递归之前 => [2, 1, 3]
//          递归之后 => [2, 1]
//          递归之后 => [2]
//              递归之前 => [2, 3]
//              递归之前 => [2, 3, 1]
//          递归之后 => [2, 3]
//          递归之后 => [2]
//          递归之后 => []
//              递归之前 => [3]
//              递归之前 => [3, 1]
//              递归之前 => [3, 1, 2]
//          递归之后 => [3, 1]
//          递归之后 => [3]
//              递归之前 => [3, 2]
//              递归之前 => [3, 2, 1]
//          递归之后 => [3, 2]
//          递归之后 => [3]
//          递归之后 => []
//          输出 => [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
//
//          作者：liweiwei1419
//          链接：https://leetcode.cn/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
//          来源：力扣（LeetCode）
//          著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
