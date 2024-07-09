package com.tian.algorithm.leedcode;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/6 16:02
 *
 *  关键点：排序; 去重; for (int i = start
 *
 */
public class a1_两数之和 {

    /**
     * 局限性：不适用于 打印所有和target的组合
     *
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 两数之和
     * 思路：排序加双指针
     *  两数之积 也类似
     */
    public static List<List<Integer>> twoSumAll(int[] nums, int target) {
        // !!!
        Arrays.sort(nums);

        int len = nums.length;
        int i = 0, j = len - 1;
        List<List<Integer>> all = new ArrayList<>();

        while(i < j){
            if(nums[i] + nums[j] == target){
                //all.add(Arrays.asList(nums[i], nums[j]));
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                all.add(list);
                ++i;
                --j;

                // 以下两步去重
                while(i < j && nums[j] == nums[j + 1]) --j;
                while(i < j && nums[i] == nums[i - 1]) ++i;
            }else if(nums[i] + nums[j] < target){
                ++i;
            } else{
                --j;
            }
        }
        return all;
    }

    /**
     * 原文链接：https://blog.csdn.net/qq_35655602/article/details/116170755
     * twoSumAll2--->twoSumAll局部微调
     */
    public List<List<Integer>> twoSumAll2(int[] nums,int target) {
        // !!! nums数组必须有序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        /* 双指针 */
        int low = 0, high = nums.length - 1;
        /* 不能重复取元素，所以是小于号 */
        while (low < high) {
            int sum = nums[low] + nums[high];
            /* 记录索引low和high最初对应的值，方便跳过重复元素 */
            int left = nums[low], right = nums[high];

            if (sum == target) {
                /* 把二元组{left, right}添加到res中 */
                List<Integer> tmp = new LinkedList<>();
                tmp.add(left);
                tmp.add(right);
                res.add(tmp);
                /* 两边跳过重复元素 */
                while (low < high && nums[low] == left) low++;
                while (low < high && nums[high] == right) high--;

            } else if (sum < target) {
                /* 左指针跳过重复元素 */
                while (low < high && nums[low] == left) low++;

            } else {
                /* 右指针跳过重复元素 */
                while (low < high && nums[high] == right) high--;
            }
        }
        return res;
    }

    /**
     * 三数之和
     *
     * 排序 + 最外层去重 + 内层去重
     */
    public static List<List<Integer>> threeSum3All(int[] nums, int target) {
        List<List<Integer>> threeSum = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0 ; i<nums.length; i++){
            // twoSumAll
            List<List<Integer>> lists = twoSumAll(nums, target - nums[i]);// todo 可以优化为 多加一个入参,twoSumAll中从i+1开始for循环
            if(CollectionUtils.isEmpty(lists)){
                continue;
            }
            for(List<Integer> list: lists){
                //list=new ArrayList<>(list); // 不加此步骤会报错：java.lang.UnsupportedOperationException
                list.add(nums[i]);
                threeSum.add(list);
            }

            /** !!!
             * 跳过第一个数字重复的情况，否则会出现重复结果
             * 跳过重复元素，最终i会停在该重复元素的最后一个
             */
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }

        return threeSum;
    }

    /**
     *  原文链接：https://blog.csdn.net/qq_35655602/article/details/116170755
     *  n数之和
     *  nSum框架，从nums[start]开始，计算和为target的n元组
     *
     *  注意：start为起始位置，0开始, 容易漏掉,n是n个数之和
     */
    private static List<List<Integer>> nSumEqualsTarget(int[] nums, int n, int start, int target) {
        int sz = nums.length;
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);//??

        /* 至少是2Sum，且数组大小不应该小于n */
        if (n < 2 || sz < n) return res;
        /* 2Sum是base case */
        if (n == 2) {
            return twoSumAll(nums, target);
        } else {
            /* n>2时，递归计算(n-1)Sum的结果 */     // ！！！threeSum3All 说明 3个数, start从0开始
            for (int i = start; i < sz; i++) {   //易错❌ !!!!!! i = start
                /* 对target-nums[i]计算(n-1)Sum */  // !!! 变化为 n - 1, i + 1
                List<List<Integer>> sub = nSumEqualsTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    /* 如果存在满足条件的(n-1)元组，则再加上nums[i]就是nSum */
                    arr.add(nums[i]);
                    res.add(arr);
                }

                /*  !!!
                跳过重复元素，最终i会停在该重复元素的最后一个
                */
                while (i < sz - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }


    public static void main(String[] args) {

        int[] a = {1,3,2,3,4,3,4,2};
        System.out.println(threeSum3All(a,7));
        List<List<Integer>> lists = nSumEqualsTarget(a, 3, 0, 7);
        System.out.println(lists);
        System.out.println();
    }



}