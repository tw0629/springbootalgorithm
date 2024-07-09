package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/11 11:40
 */
public class a5_最长回文子串 {

    /**
     * 方法一 暴力
     * 最大回文子字符串
     */
    // com.tian.algorithm.Z_inter.string.StringOp.getLongestPalindrome

    /**
     * 方法二 动态规划
     * 最大回文子字符串
     */
    public int getLongestPalindrome1(String A, int n) {

        // 第 i 个字符到第 j 个字符是否是回文串
        boolean[][] dp = new boolean[n][n];
        int max = 0;
        // 字符串首尾字母长度差 (d = j-i)
        for (int d = 0; d < n; d++) { // !!!  d可以理解为字符串的长度
            // 字符串起始位置 i
            for (int i = 0; i < n-d; i++) {
                // 字符串结束位置 j
                int j = i+d;
                // 如果字符串 i 到 j 的首尾相等，再根据字符串 i-1 到 j-1 来确定，即得到递推公式
                if(A.charAt(i) == A.charAt(j)) {
                    if(d == 0 || d == 1) { // !!!  因为回文个数至少得3个所以d=0,1时候,dp[i][j] = true
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                    if(dp[i][j]) {
                        // 更新最大长度
                        max = Math.max(max, d+1); // d从0开始的，长度为d+1
                    }
                }
            }
        }
        return max;
    }

    /**
     * 方法三 中心扩散法
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);

    }

}
