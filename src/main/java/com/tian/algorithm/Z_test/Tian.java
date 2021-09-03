package com.tian.algorithm.Z_test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 判断一个列表中的单词是否在字符串中
 *
 * 输入一个列表的单词，及一个长字符串，判断字符串可否由列表中的单词组成。
 * 比如：
 * 输入： 单词列表 dict：I, love, byte, bytedance 字符串 s：Ilovebytedance
 * 输出： True
 *
 * loveIlove true
 * bytedancebyte true
 * loveabytedancebyted false
 * bytedancebyte true
 * bytelovedance false
 */
public class Tian {

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("I", "love", "byte", "bytedance");

        List<String> ss = Arrays.asList("Ilovebytedance", "loveIlove", "bytedancebyte", "loveabytedancebyted", "bytedancebyte","bytelovedance");

        for(String s: ss){
            System.out.println(""+s+" "+wordBreak(s, wordDict));

            System.out.println("");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("");
        }


    }

    /*bool wordBreak(string s, unordered_set<string> &dict) {
        int n = s.size();
        vector<bool>  dp(n+1, false);
        dp[0] = true;
        for(int i= 0; i < n; ++i){
            for(int j = i; dp[i]&&j<n; ++j){
                if(dict.find(s.substr(i, j - i+1)) != dict.end())
                    dp[j+1] = true;
            }
        }
        return dp[n];
    }*/

    /*boolean wordBreak(String s, Set<String> dict) {
        int l = s.length();
        //vector<bool>  dp(n+1, false);
        int[] dp = new int[l];

        dp[0] = 1;
        for(int i= 0; i < l; ++i){
            for(int j = i; dp[i]!=0&&j<l; ++j){
                if(dict.contains(s.substring(i, j - i+1)) != dict.end()) {
                    dp[j+1] = 1;
                }
            }
        }
        return dp;
    }*/

    public static boolean wordBreak(String s, List<String> wordDict){
        if(s==null||s.length()==0) {
            return false;
        }
        //dp[i]表示字符串的前i个字符是否能由词典中的单词表示。
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                System.out.println("dp[j]:"+dp[j]+"  "+j+"-"+i+": s.substring(j,i):"+s.substring(j,i));
                // dp[j]代表上一个分片在字典表中, s.substring(j,i)代表下一个分片在字典表中
                if( dp[j] && wordDict.contains(s.substring(j,i)) ){
                    dp[i]=true;
                    System.out.println("break "+Arrays.toString(dp));
                    break;
                }
                System.out.println(Arrays.toString(dp));
            }
        }

        return dp[s.length()];
    }

}
