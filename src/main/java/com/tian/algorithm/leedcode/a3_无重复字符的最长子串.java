package com.tian.algorithm.leedcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/6 21:09
 */
public class a3_无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("123145"));
        System.out.println();
    }

    /**
     * 方法一
     * 思路是：滑动窗口（不断地改变左指针的位置）
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0; //最长子串长度
        int left = 0; //滑动窗口左下标，i相当于滑动窗口右下标  ---> [left,i]
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                // 举例：  1，2，3，4，5，2，6，7，8，2，9，10
                // 举例：  1，2，3，4，5，2，6，7，8，3，9，10
                left = Math.max(left,map.get(s.charAt(i)) + 1); // ！！！指向重复元素的下一个位置
                // ？？? 有必要去最大的吗 left = map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i),i); // 重要！！！
            max = Math.max(max,i-left+1);// 改变最长子串的长度
        }
        return max;
    }

    /**
     * 方法二
     * 滑动窗口解法 双指针法 (此时有点效率低)
     */
    public int lengthOfLongestSubstring2(String s) {
        //维护当前最长不重复字符子串
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while(right<s.length()){
            if(!set.contains(s.charAt(right))){ // 重要！！！
                //未查到重复字符就一直加，right右移
                set.add(s.charAt(right));
                right++;
            }else{
                //right查到重复字符先不动，left右移，set删left经过的字符，直到重复的这个字符删掉为止
                set.remove(s.charAt(left));
                left++;
            }
            //每一次计算当前set子串的长度
            max = Math.max(max, set.size());
        }
        return max;
    }

}
