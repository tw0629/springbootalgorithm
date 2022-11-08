package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/27 15:46
 */
public class a58_最后一个单词的长度 {

    // 反向遍历
    public int lengthOfLastWord(String s) {
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
}


