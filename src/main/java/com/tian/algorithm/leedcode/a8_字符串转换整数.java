package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/18 10:20
 */
public class a8_字符串转换整数 {

    public static int myAtoi(String s) {
        char[] str = s.toCharArray();

        int res = 0;
        int i = 0;
        int flag = 0;
        while(str[i] == ' ') {i++;}
        if(str[i]=='-'){flag++;}
        if(str[i]=='+' || str[i]=='-'){i++;}

        while(i<str.length && isdigit(str[i])){
            int r = str[i] - '0';
            if(res>Integer.MAX_VALUE/10 || (res>Integer.MAX_VALUE/10 && r>7)){
                return flag>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }

            res = res*10 + r; // 涉及推入数字
            i++;
        }
        return flag>0?-res:res;
    }

    private static boolean isdigit(char c) {
        if('0' <= c && c <= '9'){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(myAtoi("123 qwe qerq"));
        System.out.println(myAtoi("-123 qwe qerqddd"));
        System.out.println(myAtoi("+123 qwe qerqddd"));
        System.out.println(myAtoi("  +123 qwe qerqddd"));
        System.out.println(myAtoi("  -123 qwe qerqddd"));
        System.out.println(myAtoi("qwe qerqddd  -123"));
        System.out.println(myAtoi("qwe-123a4456qerqddd"));
        System.out.println(myAtoi("qwe -123 a4456qerqddd"));
        System.out.println(myAtoi("-123a4456qerqddd")); // -123
    }

}
