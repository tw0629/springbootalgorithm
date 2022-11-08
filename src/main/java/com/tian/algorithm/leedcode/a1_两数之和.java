package com.tian.algorithm.leedcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/6 16:02
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

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        boolean twoSum = false;
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                twoSum = true;
                System.out.print("twoSum2: "+nums[i]+"和"+complement);
                System.out.println(" twoSum2: "+i+"和"+map.get(complement));
                System.out.println("");
                //return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        if(!twoSum){
            throw new IllegalArgumentException("No two sum solution");
        }
        return null;
    }

    public static void main(String[] args) {

        int[] a = {3,2,3,4,3,4,2};
        System.out.println(twoSum2(a,6));
    }


}
