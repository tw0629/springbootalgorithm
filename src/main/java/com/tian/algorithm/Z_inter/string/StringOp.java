package com.tian.algorithm.Z_inter.string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 23:12
 */
public class StringOp {

    /**
     * 判断回文
     * @param str
     * @return
     */
    public static boolean judgeHuiWen(String str) {
        int len = str.length();
        for(int i = 0 ; i < len/2 ;i++)
        {
            if(str.charAt(i)!=str.charAt(len-1-i)){
                return false;
            }
        }
        return true;
    }

    //判断是否是回文串
    private boolean judgeHuiWen2(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最大回文子字符串
     *
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     */
    public static int getLongestPalindrome(String A, int n) {
        //边界条件判断
        if (n < 2){
            return A.length();
        }

        int maxLen = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                //截取所有子串，如果截取的子串小于等于之前
                //遍历过的最大回文串，直接跳过。因为截取
                //的子串即使是回文串也不可能是最大的，所以
                //不需要判断
                if (j - i < maxLen){
                    continue;
                }
                if (j<0 || j>=n){
                    continue;
                }

                // 注意：截取是不包括尾, 左开右闭
                String temp = A.substring(i,j);
                if (judgeHuiWen(temp)) {
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        System.out.println("===> "+i+" "+j);
                    }
                }
            }
        }
        return maxLen;
    }

    /**
     * 最大回文子字符串
     */
    public int getLongestPalindrome2(String A, int n) {

        // 第 i 个字符到第 j 个字符是否是回文串
        boolean[][] dp = new boolean[n][n];
        int max = 0;
        // 字符串首尾字母长度差 (d = j-i)
        for (int d = 0; d < n; d++) {
            // 字符串起始位置 i
            for (int i = 0; i < n-d; i++) {
                // 字符串结束位置 j
                int j = i+d;
                // 如果字符串 i 到 j 的首尾相等，再根据字符串 i-1 到 j-1 来确定，即得到递推公式
                if(A.charAt(i) == A.charAt(j)) {
                    if(d == 0 || d == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                    if(dp[i][j]) {
                        // 更新最大长度
                        max = Math.max(max, d+1);
                    }
                }
            }
        }
        return max;
    }

    /**
     * 反转字符串
     * @param str string字符串
     * @return string字符串
     */
    public String solve (String str) {
        // write code here
        char[] s=str.toCharArray();
        char[] res=new char[s.length];
        int j=0;
        for(int i=s.length-1;i>=0;i--){
            res[j++]=s[i];
        }
        return String.valueOf(res);
    }


    public String LCS(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                //递推公式，两个字符相等的情况
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    //如果遇到了更长的子串，要更新，记录最长子串的长度，
                    //以及最长子串最后一个元素的位置
                    if (dp[i + 1][j + 1] > maxLenth) {
                        maxLenth = dp[i + 1][j+1];
                        maxLastIndex = i;
                    }
                } else {
                    //递推公式，两个字符不相等的情况
                    dp[i + 1][j+1] = 0;
                }
            }
        }
        //最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
        return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
    }



    /**
     * 最长公共子串
     *
     */
    public String LCS1(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        int[] dp = new int[str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            //注意这里是倒叙
            for (int j = str2.length() - 1; j >= 0; j--) {
                //递推公式，两个字符相等的情况
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[j + 1] = dp[j] + 1;
                    //如果遇到了更长的子串，要更新，记录最长子串的长度，
                    //以及最长子串最后一个元素的位置
                    if (dp[j + 1] > maxLenth) {
                        maxLenth = dp[j + 1];
                        maxLastIndex = i;
                    }
                } else {
                    //递推公式，两个字符不相等的情况
                    dp[j + 1] = 0;
                }
            }
        }
        //最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
        return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
    }

    public String LCS2(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        int[] dp = new int[str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            //注意这里是倒叙
            for (int j = str2.length() - 1; j >= 0; j--) {
                //递推公式，两个字符相等的情况
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[j + 1] = dp[j] + 1;
                    //如果遇到了更长的子串，要更新，记录最长子串的长度，
                    //以及最长子串最后一个元素的位置
                    if (dp[j + 1] > maxLenth) {
                        maxLenth = dp[j + 1];
                        maxLastIndex = i;
                    }
                } else {
                    //递推公式，两个字符不相等的情况
                    dp[j + 1] = 0;
                }
            }
        }
        //最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
        return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
    }


    /**
     * 括号序列
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        // 使用foreach循环
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');

            // 说明是 非括号左边
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static int sumKh(String s) {
        Stack<Character> stack = new Stack<Character>();
        // 使用foreach循环
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            }
            if (stack.isEmpty() || stack.pop() != c) {
                return 0;
            }
        }

        return stack.size();
    }

    /**
     * 最长的括号子串
     */
    public static int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 最长的括号子串 2
     */
    public static int longestValidParentheses2(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        //Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * 最长的括号子串 3
     *
     * 自己实现的 ??? 有问题
     */
    public static int longestValidParentheses3(String s) {
        int l = s.length();
        if(l<=1){
            return 0;
        }

        Stack<Character> stack = new Stack<Character>();
        // stack2主要是为了处理极值情况的，如开头以'('开始  或者  非'(',')'的其他符号
        Stack<Character> stack2 = new Stack<Character>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == '(') {
                stack.push(')');
            }
            if(stack.isEmpty() && chars[i] == ')'){
                stack2.push(chars[i]);
            }
            if(chars[i] != '('&& chars[i] != ')'){
                stack2.push(chars[i]);
            }

            if (!stack.isEmpty() && stack.peek()==chars[i]){
                stack.pop();
            }
        }
        return l-stack.size()-stack2.size();
    }

    public static void main(String[] args) {
        String s = "abc1234321ab";
        System.out.println("=======>"+getLongestPalindrome(s,s.length()));

        String s1 = "abc1234321ab";
        String substring = s1.substring(0, 0);
        String substring2 = s1.substring(0, 1);
        System.out.println("=======>"+substring);
        System.out.println("=======>"+substring2);
        System.out.println("");


        String s2 = "[]{})()";
        String s3 = "([{}])";
        String s4 = "(()(()()()(()";
        String s5 = "))((";
        String s6 = "(()";
        String s7 = "())))";
        boolean valid = isValid(s3);
        int i = longestValidParentheses3(s2);
        System.out.println("=======>"+i);
        System.out.println("");
    }



}
