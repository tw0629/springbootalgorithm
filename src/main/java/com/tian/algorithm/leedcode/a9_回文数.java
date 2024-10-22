package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/18 11:01
 */
public class a9_回文数 {

    public static boolean judgeHuiWen(String str) {
        int len = str.length();
        for(int i = 0 ; i < len/2 ;i++){
            if(str.charAt(i)!=str.charAt(len-1-i)){
                return false;
            }
        }
        return true;
    }
    private boolean judgeHuiWen2(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

}
