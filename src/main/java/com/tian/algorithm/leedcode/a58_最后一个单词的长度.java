package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/27 15:46
 */
public class a58_最后一个单词的长度 {

    // 1 注意是：最后一个单词的长度
    // 反向遍历
    public static int lengthOfLastWord(String s) {
        int and = s.length() - 1;
        while (s.charAt(and) == ' ') {
            and--;
        }

        int start = 0;
        while (and >= 0 && s.charAt(and) != ' ') {
            start++;
            and--;
        }
        return start;
    }

    // 自己想的
    // 2 最长一个单词的长度
    public static int lengthOfLongestWord(String s) {
        int cur = 0;

        int start = 0;
        int and = s.length() - 1;

        while (s.charAt(start) == ' ') {
            cur++;
            start++;
        }
        while (s.charAt(and) == ' ') {
            and--;
        }

        int longest = 0;

        while(cur<=and){

            while (cur<=and && s.charAt(cur) != ' ' ) {
                cur++;
            }
            longest = Math.max(longest,cur-start);

            // 跳过空格,到下一个单词的起始
            while (cur<=and && s.charAt(cur) == ' ') {
                cur++;
            }
            start = cur;
        }

        return longest;
    }

    public static void main(String[] args) {

        String s = "  aa  bbb  cccc  ddddd  f   ";

        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLongestWord(s));
        System.out.println();
    }

}


