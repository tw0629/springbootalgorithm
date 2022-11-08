package com.tian.algorithm.leedcode;


import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2022/9/23 11:13
 */
public class 子集 {

    /**
     * https://blog.csdn.net/qq_38783664/article/details/113478186
     * stopwatch: https://blog.csdn.net/qfzhaohan/article/details/121379893
     */

    /**
     * 方法1
     */
    /**
     * 思路：
     * 每增加一个元素让之前所有的结果集中加入这个元素
     *
     *  第一个for循环是遍历每个元素；第二for循环是遍历每个子集合，并拷贝；
     */
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>(); // 解集
        lists.add(new ArrayList<Integer>()); // 首先将空集加入解集中

        for(int i = 0; i < nums.length; i++){
            int size = lists.size(); // 当前子集数
            for(int j = 0; j < size; j++){
                List<Integer> newList = new ArrayList<>(lists.get(j));// 拷贝所有子集
                newList.add(nums[i]); // 向拷贝的子集中加入当前数形成新的子集
                lists.add(newList); // 向lists中加入新子集
            }
        }
        return lists;
    }

    /**
     * 子集（java实现）--LeetCode
     * https://blog.csdn.net/qq_38783664/article/details/113478186
     */

    /**
     * 解法1：递归
     *
     * 思路：
     * 获取每个数，每次递归都是当前数+1，找到其所有的组合，每次都加入结果集
     * 递归结束后删除这个数
     * []
     * 1的所有可能：1,12,123,12,13,1
     * 2的所有可能：2,23,2
     * 3的所有可能：3
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        result.add(new ArrayList<>());
        recursive(result,queue,nums,0);
        return result;
    }
    private static void recursive(ArrayList<List<Integer>> result, LinkedList<Integer> queue, int[] nums, int lattice) {
        for (int i=lattice;i<nums.length;i++){
            queue.offer(nums[i]);
            result.add(new ArrayList<>(queue)); // 含有拷贝
            recursive(result, queue, nums, i+1);
            System.out.println("---> "+queue);
            queue.removeLast();
        }
    }
    /**
     * 方法22
     * https://blog.csdn.net/qq_42900213/article/details/124312274
     * 注意：和上面一样，只是res.add(new ArrayList<>(path))位置放的不一样
     */
    /*public static List<List<Integer>> subsets22(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        recuit(res, path, nums,0);
        return res;
    }

    public static void recuit(List<List<Integer>> res, ArrayList<Integer> path, int[] nums,int start){
        res.add(new ArrayList<>(path));
        for(int i = start;i < nums.length;i++){
            path.add(nums[i]);
            recuit(res, path, nums,i+1);
            path.remove(path.size()-1);
        }
    }*/


    /**
     * 解法2：递归（放格子）
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets3(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        recursive2(result,queue,nums,0);
        return result;
    }

    private static void recursive2(ArrayList<List<Integer>> result, LinkedList<Integer> queue, int[] nums, int lattice) {
        if (lattice==nums.length){
            result.add(new ArrayList<>(queue));
            return;
        }
        recursive2(result, queue, nums, lattice+1);
        queue.offer(nums[lattice]);
        recursive2(result, queue, nums, lattice+1);
        queue.removeLast();
    }


    /**
     *
     * 解法4：位运算
     *
     * 思路：
     * 每个数有两个选择，放入格子，或者不放入。
     * 因此结果集的长度是2^n
     * 比如数组1，2，3
     * 有8种可能性，用0表示不放，1表示放。正好可以用1-8的二进制数表示放或不放
     * (res_index >> index) & 1查看当前位是否放元素
     *
     */
    public static List<List<Integer>> subsets4(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int result_index = 0; result_index < 1 << nums.length; result_index++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int index = 0; index < nums.length; index++) {
                if (((result_index >> index) & 1) == 1) list.add(nums[index]);
            }
            result.add(list);
        }
        return result;
    }


    public static void main(String[] args){
        int[] a = {1,2,3};

        //System.out.println("subsets1: "+subsets1(a));
        System.out.println("subsets2: "+subsets2(a));
        //System.out.println("subsets22: "+subsets22(a));
        //System.out.println("subsets3: "+subsets3(a));
        //System.out.println("subsets4: "+subsets4(a));

        // stopWatchPrintln();
    }

    public static void stopWatchPrintln(){
        int[] a = {1,2,3};
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("subsets1 task");
        System.out.println(subsets1(a));
        stopWatch.stop();
        System.out.println("subsets1 task 耗时:" + stopWatch.getLastTaskTimeMillis() + "ms");

        stopWatch.start("subsets2 task");
        System.out.println(subsets2(a));
        stopWatch.stop();
        System.out.println("subsets2 task 耗时:" + stopWatch.getLastTaskTimeMillis() + "ms");

        stopWatch.start("subsets22 task");
        //System.out.println(subsets22(a));
        stopWatch.stop();
        System.out.println("subsets22 task 耗时:" + stopWatch.getLastTaskTimeMillis() + "ms");

        stopWatch.start("subsets3 task");
        System.out.println(subsets3(a));
        stopWatch.stop();
        System.out.println("subsets3 task 耗时:" + stopWatch.getLastTaskTimeMillis() + "ms");

        stopWatch.start("subsets4 task");
        System.out.println(subsets4(a));
        stopWatch.stop();
        System.out.println("subsets4 task 耗时:" + stopWatch.getLastTaskTimeMillis() + "ms");

        System.out.println("");
        System.out.println("任务数用时：" + stopWatch.getTotalTimeMillis() + " ms");
        System.out.println("任务数：" + stopWatch.getTaskCount());
        System.out.println("任务执行的百分比：" + stopWatch.prettyPrint());
    }






}
