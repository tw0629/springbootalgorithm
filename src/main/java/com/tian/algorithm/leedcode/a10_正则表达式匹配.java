package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/18 11:28
 */
public class a10_正则表达式匹配 {

    // 困难  不懂
    //    链接：https://leetcode.cn/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-chen-wei-6h9ap/

    /**
     *
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     *
     */
    public boolean isMatch(String s, String p) {
        if (p==null){
            if (s==null){
                return true;
            }else{
                return false;
            }
        }

        if (s==null && p.length()==1){
            return false;
        }

        int m = s.length()+1;
        int n = p.length()+1;

        boolean[][]dp = new boolean[m][n];

        dp[0][0] = true;

        for (int j=2;j<n;j++){
            if (p.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int r=1;r<m;r++){
            int i = r-1;
            for (int c=1;c<n;c++){
                int j = c-1;

                if (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'){
                    dp[r][c] = dp[r-1][c-1];
                }else if (p.charAt(j)=='*'){
                    if (p.charAt(j-1)==s.charAt(i) || p.charAt(j-1)=='.'){
                        dp[r][c] = dp[r-1][c] || dp[r][c-2];
                    }else{
                        dp[r][c] = dp[r][c-2];
                    }
                }else{
                    dp[r][c] = false;
                }
            }
        }
        return dp[m-1][n-1];
    }
}
