package com.tian.algorithm.base_op.string;

import java.util.Deque;
import java.util.LinkedList;
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
        boolean[][] dp = new boolean[n][n]; //
        int max = 0;
        // 字符串首尾字母长度差 (d = j-i)
        for (int d = 0; d < n; d++) { // !!!  d可以理解为字符串的长度
            // 字符串起始位置 i
            for (int i = 0; i < n-d; i++) {
                // 字符串结束位置 j
                int j = i+d;
                // 如果字符串 i 到 j 的首尾相等，再根据字符串 i-1 到 j-1 来确定，即得到递推公式
                if( A.charAt(i) == A.charAt(j) ) {
                    // 注意：前提是A.charAt(i) == A.charAt(j)
                    // 所以才d == 0 || d == 1
                    if(d == 0 || d == 1) { // !!!  因为回文个数至少得3个所以d=0,1时候,dp[i][j] = true
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                    if(dp[i][j]) {
                        // 更新最大长度
                        max = Math.max(max, d+1);
                    }
                    // 最大回文字符串 起始位置
                    /*if (dp[i][j] && j - i + 1 > max) {
                        begin = i;
                    }*/
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
//        String temp = "";
        for(int i=s.length-1;i>=0;i--){
            res[j++]=s[i];
//            temp=temp+String.valueOf(s[i]);
        }
        return String.valueOf(res);
    }



    /**
     * 最长公共子串 和 最长公共子序列  类似
     * https://blog.csdn.net/ly0724ok/article/details/119876759
     */

    /**
     * 最长公共子串
     *      最长公共子串 （一维）: dp[j + 1] = dp[j] + 1;
     *                          dp[j + 1] = 0;
     *      最长公共子串 （二维）: dp[i + 1][j + 1] = dp[i][j] + 1;
     *                          dp[i + 1][j+1] = 0;
     *
     * 用一个二维数组dp[i][j]表示 第一个字符串前 i 个字符 和 第二个字符串前 j 个字符 组成的最长公共字符串的长度。
     *
     * 那么我们在计算dp[i][j]的时候，我们首先要判断s1.charAt(i)是否等于s2.charAt(j)：
     *      如果不相等，说明当前字符无法构成公共子串，所以dp[i][j]=0。
     *      如果相等，说明可以构成公共子串，我们还要加上他们前一个字符构成的最长公共子串长度，也就是dp[i-1][j-1]。所以我们很容易找到递推公式
     * 原文链接：https://blog.csdn.net/ly0724ok/article/details/119876759
     *
     * //8中基本数据类型的默认值：
     * //byte short int long 这四种基本数据类型数组默认值为0
     * //float double 这两种数组默认值是0.0
     * //char这种类型数组默认值为空格
     * //boolean类型数组默认值为false
     * //Integer类不是基本的数据类型，默认值不是0，是null；
     *
     */
    public String LCS1(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        // 当 i 或 j 等于 0 时，初始化子串就是为0，初始化数组也是为0
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        //Arrays.fill(dp,0);

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
        // 最字符串进行截取，substring(a,b)中a和b分别表示截取的开始和结束位置
        return str1.substring(maxLastIndex - maxLenth + 1, maxLastIndex + 1);
    }

    /**
     * 最长公共子串  （一维）
     *
     */
    public String LCS2(String str1, String str2) {
        int maxLenth = 0;//记录最长公共子串的长度
        //记录最长公共子串最后一个元素在字符串str1中的位置
        int maxLastIndex = 0;
        int[] dp = new int[str2.length() + 1];  // dp该位置之前的相同元素个数
        //Arrays.fill(dp,0);
        for (int i = 0; i < str1.length(); i++) {
            //注意这里是倒序
            for (int j = str2.length() - 1; j >= 0; j--) { //?? 倒序会不会有问题
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
     *  最长公共子序列
     *      最长公共子序列 （一维）: dp[j] = dp[j-1] + 1;
     *                           dp[j] = Math.max(cur[j-1],pre[j]);
     *      最长公共子序列 （二维）: dp[i][j] = dp[i - 1][j - 1] + 1;
     *                           dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
     *
     *  一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        if (text1 == null || text2 == null || len1 < 1 || len2 < 1)
            return 0;

        //定义 dp[i][j] 表示 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。 所以需要 + 1
        //之所以 dp[i][j] 的定义不是 text1[0:i] 和 text2[0:j] ，是为了方便当 i = 0 或者 j = 0 的时候，
        //dp[i][j]表示的为空字符串和另外一个字符串的匹配，这样 dp[i][j] 可以初始化为 0。
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                //由于当 i 和 j 取值为 0 的时候，dp[i][j] = 0，而 dp 数组本身初始化就是为 0，所以，直接让 i 和 j 从 1 开始遍历
                //遍历的结束应该是字符串的长度为 len(text1) 和 len(text2)。

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) { //注意：charAt(i - 1)比的时候，dp[i][j]
                    //两个子字符串的最后一位相等，所以最长公共子序列又增加了 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //两个子字符串的最后一位不相等，那么此时的状态 dp[i][j] 应该是 dp[i - 1][j] 和 dp[i][j - 1] 的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    // dp 状态压缩   ？？？
    public int longestCommonSubsequence4(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] pre = new int[m+1];// (i-1)层
        int[] cur = new int[m+1];// i 层

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1))
                    cur[j] = pre[j-1]+1;
                else
                    cur[j] = Math.max(cur[j-1],pre[j]);
            }
            // 更新 (i-1) 层 为 i 层
            for (int j = 0; j < m + 1; j++) {
                pre[j] = cur[j];
            }
        }
        return cur[m];
    }
    
    /**
     *  优化  最长公共子序列
     *  一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     */
    public static int longestCommonSubsequence3(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0)
            return 0;
        int max = 0;
        int[] dp = new int[str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            //使用的倒序的方式，这是因为dp数组后面的值会依赖前面的值，而前面的值不依赖后面的值，所以后面的值先修改对前面的没影响，但前面的值修改会对后面的值有影响，所以这里要使用倒序的方式。
            for (int j = str2.length(); j >= 1; j--) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[j] = dp[j - 1] + 1;
                else
                    dp[j] = 0;
                max = Math.max(max, dp[j]);
            }
        }
        return max;
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
    public static int longestValidParentheses1(String s) {
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
     * 最长的括号子串 2   就是最长的括号子串的 前后下标相减
     *
     * 堆栈操作
     * https://www.cnblogs.com/duiyuedangge/p/15643495.html
     */
    public static int longestValidParentheses2(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        //Stack<Integer> stack = new Stack<>();
        stack.push(-1); //!!!!!!
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();// )))))()
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // i 减去 去处有效括号的第一个
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

        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.peek());


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
        String s6 = ")))";
        String s7 = "())))";
        boolean valid = isValid(s3);
        int i  = longestValidParentheses1(s4);
        int i2 = longestValidParentheses2(s6);
        int i3 = longestValidParentheses3(s4);
        System.out.println("=======>"+i);
        System.out.println("");
    }


    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
     *
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
