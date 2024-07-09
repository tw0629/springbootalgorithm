package com.tian.algorithm.base_op.dict;

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
     * 判断字符串可否由列表中的单词组成
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
    /**
     * 方法一
     * dp[i] = dp[j] && wordDict.contains(s.substring(j,i))
     *
     * 注意：双for循环的写法,先谁后谁
     *  每次都是先控制尾位置,防止被覆盖
     */
    public static boolean wordBreak(String s, List<String> wordDict){
        if(s==null||s.length()==0) {
            return false;
        }
        //dp[i]表示字符串的前i个字符是否能由词典中的单词表示。
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        // 注意：双for循环的写法，先谁后谁
        for(int i=1;i<=s.length();i++){        //!!!!!!
            for(int j=0;j<i;j++){ // 注意j<i    //!!!!!!

                System.out.println("dp[j]:"+dp[j]+"  "+j+"-"+i+": s.substring(j,i):"+s.substring(j,i));
                // dp[j]代表上一个分片在字典表中, s.substring(j,i)代表下一个分片在字典表中
                if( dp[j] && wordDict.contains(s.substring(j,i)) ){
                    dp[i]=true;
                    System.out.println("======> break "+ Arrays.toString(dp));
                    break; // 注意:一定是break,否则会覆盖
                }else {
                    System.out.println(Arrays.toString(dp));
                }
            }
        }

        return dp[s.length()];
    }

    // 方法二
    // !!!!!!  有问题 ???  会被覆盖 正确是第一种方式
    // 这种写法：每次都是先控制首位置,不控制尾位置,会被覆盖;
    //     应该：每次都是先控制尾位置,防止被覆盖  方法一
    public static boolean wordBreak2(String s, List<String> wordDict){
        if(s==null||s.length()==0) {
            return false;
        }
        //dp[i]表示字符串的前i个字符是否能由词典中的单词表示。
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=0;i<s.length();i++){        //!!!!!!
            for(int j=i+1;j<=s.length();j++){ // 注意j<i    //!!!!!!   注意：j最后为s.length()

                System.out.println(s+"  "+"dp[i]:"+dp[i]+"  "+i+"-"+j+": s.substring(i,j):"+s.substring(i,j));
                // dp[j]代表上一个分片在字典表中, s.substring(j,i)代表下一个分片在字典表中
                if( dp[i] && wordDict.contains(s.substring(i,j)) ){
                    dp[j]=true;
                    System.out.println("======> break "+ Arrays.toString(dp));
                    break; // 注意:一定是break,否则会覆盖
                }else {
                    System.out.println(Arrays.toString(dp));
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {

        List<String> list = Arrays.asList("I", "love", "byte", "bytedance");
        String s = "Ilovebytedance";
        System.out.println(wordBreak(s,list));
        //System.out.println(wordBreak2(s,list));
        System.out.println(s.substring(1,5));
        System.out.println();
    }
}
