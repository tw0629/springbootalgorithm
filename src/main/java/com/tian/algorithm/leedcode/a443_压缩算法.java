package com.tian.algorithm.leedcode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author David Tian
 * @desc
 * @since 2024/9/23 21:37
 */
public class a443_压缩算法 {

    // 推荐 方法一、方法三

    // 方法一
    /*
     都可以通过方法一变形得到
     leecode中，443. 压缩字符串 1531. 压缩字符串II 3163. 压缩字符串III
     https://leetcode.cn/search/?q=%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%8E%8B%E7%BC%A9
     */
    public static String compress1(String str) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 0; i < str.length(); i++) {
            if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) { // !!!!!!  &&:且
                count++;
            } else { // 等价== if(i+1==str.length() || str.charAt(i) != str.charAt(i + 1))
                compressed.append(str.charAt(i));
                compressed.append(count);
                count = 1; // !!!!!!
            }
        }
        System.out.println("compress1 inner ======> " + compressed);
        return compressed.toString();
    }

    // 方法二 (方法一的包装)
    public static int compress2(char[] chars) throws UnsupportedEncodingException {
        if(chars==null || chars.length==0){
            return 0;
        }

        return compress1(String.valueOf(chars)).length();
    }

    // 方法三
    // 由 【字符】和【字符个数】组成
    public static int compress3(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                // 相同字符个数
                int num = read - left + 1;

                // 相同字符个数：数字处理
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) { // 相同字符个数非常大时，十几个，二十几个，几百个
                        chars[write++] = (char) (num % 10 + '0'); // 数字转字符
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1); // 例如：相同字符为12个 将【字符个数】21 转成 12 ，a12
                }

                // 下一个字符起始
                left = read + 1;
            }
        }

        System.out.println("compress3 inner ======> " + Arrays.toString(chars));

        return write;
    }

    public static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }




    public static void main(String[] args) throws UnsupportedEncodingException {

        // 方法一
        String str1 = "aaabbccc";
        String compress1 = compress1(str1);
        System.out.println("compress1 ======> "+compress1);

        // 方法二 (未去除1的)
        //char[] chars = {'a','a','a','b','b','c','c','c'};
        char[] chars2 = {'a','c','a','b','b','c','c','w'};
        int compress2 = compress2(chars2);
        System.out.println("compress2 ======> "+compress2);

        // 方法三
        //char[] chars3 = {'a','c','a','b','b','c','c','w'}; // a1c1a1b2c2w1
        char[] chars3 = {'a','c','a','b','b','d','d','d','d','d','c','c','w','w','w','w','w','w','w','w','w','w'};
        int compress3 = compress3(chars3);
        System.out.println("compress13 ======> "+compress3);
        //compress3 inner ======> [a, c, a, b, 2, d, 5, c, 2, w, 1, 0, w, w, w, w, w, w, w, w, w, w]

        System.out.println("======>");


    }
}
