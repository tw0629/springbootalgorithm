package com.tian.algorithm.leedcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/27 16:31
 */
public class a60_排列序列 {
/*
回溯：
   方式一：1 排序; 2 标识数组; 3 for循环+剪枝判断; 4 只有"一次"递归;
   方式二：1 排序; 2 for循环+剪枝判断; 3 只有"一次"递归;
   方式三：1 排序; 2 剪枝判断; 3 有"两次"递归;

注意写法：
   注意1 必粗写成result.add(new ArrayList<>(path))。 不能写成ans.add(perm); 因为每次都会remove掉。
   注意2 非必要写，只有无序元素，才写Arrays.sort(nums)排序;
   注意3 非必要写，只有重复元素，才这样写
         if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
             continue;
         }
*/

    // 推荐方法四
    // 推荐方法四
    // 推荐方法四
    // 推荐方法四
    // 推荐方法四
    // 推荐方法四


    // 此题一般是 不重复元素

    // 待看：回溯-剪枝 的优化 https://leetcode.cn/problems/permutation-sequence/solution/dfs-by-pure-4o-i7zh/


    /**
     * 方法一  for循环+一次递归
     *
     * 原题意中是： 集合 [1,2,3,...,n]  所以递归传递num:  num * 10 + i
     */
    public static String getPermutation11(int n, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        boolean[] used = new boolean[n + 1];

        dfs11(arrayList, n, 0, used, 0);
        Collections.sort(arrayList); // 好像不需要,因为这种排序本来就是有序的
        return String.valueOf(arrayList.get(k - 1));
    }
    public static void dfs11(ArrayList<Integer> arrayList, int n, int current, boolean[] visited, int num){
        if (current == n){
            arrayList.add(num);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]){
                continue;
            }
            visited[i] = true;
            dfs11(arrayList, n, current + 1, visited, num * 10 + i);
            visited[i] = false;
        }
    }

    /**
     * 方法二  和方法一 一样
     *
     * 原题意中是： 集合 [1,2,3,...,n]  所以递归传递str:  str + String.valueOf(i)
     *
     * 所以，集合 [1,2,3,...,n] 这种
     * 【数字：num * 10 + i】 就等价于 【字符串拼接：str + String.valueOf(i)】
     *
     */
    public static String getPermutation12(int n, int k) {
        List<String> res = new ArrayList<>();
        boolean [] used = new boolean[n + 1];

        dfs12(n,k,used,res,"");
        return res.get(k - 1);
    }
    private static void dfs12(int n,int k, boolean [] used, List<String> res, String str){
        if(str.length() == n){
            res.add(str);
            return;
        }
        for(int i = 1;i <= n;i++){
            if(used[i])
                continue;
            used[i] = true;
            dfs12(n,k,used, res,str + String.valueOf(i));
            used[i] = false;
        }
    }

    /**
     * 方法三  for循环+一次递归
     *
     * 是按固定集合写的： 集合 [1,2,3]
     */
    public static List<Integer> getPermutation13(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();// 放所有的子排列
        List<Integer> perm = new ArrayList<Integer>();//每个子排列
        boolean[] visited = new boolean[nums.length];//每个位置的元素是否访问过  boolean默认值是false

        Arrays.sort(nums); //排序 本来有序就无需排序
        backtrack(nums,0, visited, ans, perm);
        return ans.get(k - 1);
    }
    public static void backtrack(int[] nums, int idx, boolean[] visited, List<List<Integer>> ans, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm)); // !!!注意：不能写成ans.add(perm);
            return;
        }
        for (int i = 0; i < nums.length; ++i) { // 此时每次都是从i=0开始的
            // 剪枝条件 去重
            if (visited[i]) {
                continue;
            }
            // 注意:不重复不要这样写
            /*if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }*/

            perm.add(nums[i]);
            visited[i] = true;
            backtrack(nums, idx + 1, visited, ans, perm);
            visited[i] = false;
            perm.remove(idx);
        }
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 方法四  for循环+一次递归
     *
     * 剪枝 https://leetcode.cn/problems/permutation-sequence/solution/dfs-by-pure-4o-i7zh/
     *
     *  无序排序：初始化皆为有序
     *
     *  // ！！！已经有第k个元素了,就不在添加了，直接返回了
     *  if (k == res.size())
     *     return;
     *
     */
    public String getPermutation(int n, int k) {
        // 初始化元素
        int[] num = new int[n];
        boolean[] flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;// 初始化皆为有序
        }
        Arrays.fill(flag, false);

        // dfs
        dfs(num, 0, flag, k);

        // list转String 返回
        list = res.get(k - 1);
        StringBuilder sbd = new StringBuilder();
        for (Integer i : list) {
            sbd.append(i);
        }
        return sbd.toString();
    }
    //dfs
    private void dfs(int[] num, int h, boolean[] flag, int k){
        if (k == res.size()) // ！！！已经有第k个元素了,就不在添加了，直接返回了
            return;
        if(h == num.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (!flag[i]){
                list.add(num[i]);
                flag[i] = true;
                dfs(num, h + 1, flag, k);
                flag[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }


    public static void main(String[] args) {

        int[] a = {1,2,3,4,5};
        System.out.println(getPermutation11(5,3));
        System.out.println(getPermutation12(5,3));
        System.out.println(getPermutation13(a,3));
        System.out.println("============================");
    }
}
