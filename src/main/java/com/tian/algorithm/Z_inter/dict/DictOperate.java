package com.tian.algorithm.Z_inter.dict;

import java.util.Arrays;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/19 01:28
 */
public class DictOperate {

    /**
     * 字节跳动 面试算法
     *
     * 单词拆分
     *
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
                    System.out.println("break "+ Arrays.toString(dp));
                    break;
                }
                System.out.println(Arrays.toString(dp));
            }
        }

        return dp[s.length()];
    }
}
