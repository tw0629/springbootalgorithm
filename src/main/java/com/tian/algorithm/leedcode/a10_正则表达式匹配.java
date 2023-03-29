package com.tian.algorithm.leedcode;

import java.util.Arrays;

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
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素 !!!!! (匹配*前面的那一个元素)
     * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
     *
     * 链接：https://leetcode.cn/problems/regular-expression-matching
     *
     */
    /**
     * 动态规划，令match[i][j]表示p前i个字符能与s前j个字符匹配结果，则可以分为三种情况：
     *
     * 1 p[i]==’.’时，点匹配任意一个字符，所以match[i][j]=match[i-1][j-1]
     * 2 p[i]==’*’时，也可分为三种情况，结果为三者取并集：
     *  ’*’匹配0个元素： (match[i-1][j])
     *  ’’匹配多个元素，只要’’前元素能与s中对应元素匹配的上：match[i][j-1]&&(p.charAt(i-2)=='.' ||p.charAt(i-2)==s.charAt(j-1))
     *  ’*’删除前一元素（题目有些描述不清，可以理解为删除）：match[i-2][j];
     * 所以match[i][j]=(match[i-1][j])||(match[i][j-1]&&(p.charAt(i-2)=='.'||p.charAt(i-2)==s.charAt(j-1)))||match[i-2][j];
     *
     * 3 p[i]为字符时，只要对应之前段已匹配且对应字符同，match[i][j]=match[i-1][j-1]&&p.charAt(i-1)==s.charAt(j-1)
     *
     * 链接：https://leetcode.cn/problems/regular-expression-matching/solution/ja-by-agitated-7ovelace8kf-uub8/
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

        // s: 空
        // p: xxxxa*   a*a*a*a*a*a*(a*可以为0个)
        for (int j=2;j<n;j++){
            // ？？ s为空时候，只要该位置等于"*",dp[0][j] = dp[0][j-2]
            if (p.charAt(j-1)=='*'){     //  charAt(0): 第一个
                // i都为0,即:s为空时候
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int r=1;r<m;r++){
            int i = r-1;
            for (int c=1;c<n;c++){
                int j = c-1;

                //  1 字符串                           2 '.'
                if (p.charAt(j)==s.charAt(i) || p.charAt(j)=='.'){
                    dp[r][c] = dp[r-1][c-1];
                //  3 '*'
                }else if (p.charAt(j)=='*'){
                    // x=x*  x=.*
                    if (p.charAt(j-1)==s.charAt(i) || p.charAt(j-1)=='.'){
                        //dp[r][c] = dp[r-1][c] || dp[r][c-2];
                        dp[r][c] = dp[r-1][c] || dp[r][c-2] || dp[r][c-1];
                    }else{
                        // a* 0个
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

//'*' 代表可以匹配零个或多个前面的那一个元素
//'*' 代表可以匹配零个或多个前面的那一个元素
//'*' 代表可以匹配零个或多个前面的那一个元素
//'*' 代表可以匹配零个或多个前面的那一个元素
//'*' 代表可以匹配零个或多个前面的那一个元素

/*
给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
        '.' 匹配任意单个字符
        '*' 匹配零个或多个前面的那一个元素
        所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。

        说明:

        s可能为空，且只包含从a-z的小写字母。
        p可能为空，且只包含从a-z的小写字母，以及字符.和*。
        示例 1:

        输入:
        s = "aa"
        p = "a"
        输出: false
        解释: "a" 无法匹配 "aa" 整个字符串。
        示例 2:

        输入:
        s = "aa"
        p = "a*"
        输出: true
        解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
        示例3:

        输入:
        s = "ab"
        p = ".*"
        输出: true
        解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
        示例 4:

        输入:
        s = "aab"
        p = "c*a*b"
        输出: true
        解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
        示例 5:

        输入:
        s = "mississippi"
        p = "mis*is*p*."
        输出: false

        https://blog.51cto.com/u_15322177/3266855

*/
/*
假设：
        s = # # a
        i
        p = # # a *
        j

        此时 p 的 a * 部分可以考虑用：（0次，1次，多次）

        1. 如果用0次：
        p 的 a* 部分可以直接抛弃（想象成消消乐，并没有消去s中的字符，自己损失两位）。
        s = # # a
        i
        p = # # a *
        j

        有：dp[i][j] = dp[i][j-2]


        2. 如果用1次：
        p 的 a* 部分消去了s的一个字符（p中的a*功能用完了，也应该丢弃--左移2位）
        s = # # a
        i
        p = # # a *
        j
        有：dp[i][j] = dp[i-1][j-2]

        这种情况作者写的是：dp[i][j] = dp[i][j-1]，其实是一样的，因为此时s[i] == p[j-1]，即下一步就是，dp[i][j-1] == dp[i-1][j-2]
        其实把 a* 理解为一个整体，要消去就全部消去，比较直观一点

        3. 如果用多次：
        p的a*部分消去了s的末尾字符后，还要继续起作用，继续消除s前面的字符（不移动p的指针）

        s = # # a
        i
        p = # # a *
        j

        有：dp[i][j] = dp[i-1][j]*/
