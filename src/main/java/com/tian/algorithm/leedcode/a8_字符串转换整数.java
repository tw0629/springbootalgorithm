package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/18 10:20
 */
public class a8_字符串转换整数 {

    /**
     *     //int转char
     *     int a = 9;
     *     char c1= (char) (a+'0');
     *
     *     //char转int
     *     char c2='1';
     *     int b=c2-'0';
     */
    public static int myAtoi(String s) {
        char[] str = s.toCharArray();

        int res = 0;
        int i = 0;
        int flag = 0;
        while(str[i] == ' ') {i++;} //char[] chars = str.trim().toCharArray();
        if(str[i]=='-'){flag++;}
        if(str[i]=='+' || str[i]=='-'){i++;}

        while(i<str.length && isdigit(str[i])){
            int r = str[i] - '0';
            // r>7   Integer.MAX_VALUE/10, int最大值为 2147483647，所以当chars[j] > 7 时会越界
            if(res>Integer.MAX_VALUE/10 || (res>Integer.MAX_VALUE/10 && r>7)){
                return flag>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }

            res = res*10 + r; // 涉及推入数字
            i++;
        }

        /*  r>7
            这里这个条件的意思为，因为题目要求不能超过int范围，所以需要判断结果是否越界
            因为res每次都会 * 10 ，所以外面定义了一个int最大值除以10的数字
            此时只需要保证本次循环的res * 10 + chars[j] 不超过 int 即可保证不越界
            res > number 意思是，此时res已经大于number了，他 * 10 一定越界
            res == number && chars[j] > '7' 的意思是，当res == number时，即：214748364

            此时res * 10 变成 2147483640 此时没越界，但是还需要 + chars[j]，
            而int最大值为 2147483647，所以当chars[j] > 7 时会越界
        */


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
