package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/17 23:22
 */
public class a7_整数反转 {

    /**
     * 记 rev 为翻转后的数字，为完成翻转，我们可以重复「弹出」xx 的末尾数字，将其「推入」rev 的末尾，直至 xx 为 00。
     * 要在没有辅助栈或数组的帮助下「弹出」和「推入」数字，我们可以使用如下数学方法：
     *
     * 1 弹出 x 的末尾数字 digit
     * digit = x % 10
     * x /= 10
     *
     * 2 将数字 digit 推入 rev 末尾
     * rev = rev * 10 + digit
     *
     * 复杂度分析
     *  时间复杂度：O(log|x|)。翻转的次数即 xx 十进制的位数。
     *  空间复杂度：O(1)O(1)。
     *
     * !!!!!! 考虑整数运算是否溢出
     *  32 位有符号整数的范围： −2(31)≤rev⋅10+digit≤2(31)−1
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
     *
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            // 防止溢出
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            // 弹出
            int digit = x % 10;
            x /= 10;
            // 推入
            rev = rev * 10 + digit;
        }
        return rev;
    }



    static int getMaxInt(){
        return (1 << 31) - 1;//2147483647， 由于优先级关系，括号不可省略
    }

    static int getMinInt(){
        return 1 << 31;//-2147483648
    }

    public static void main(String[] args) {

        System.out.println(getMaxInt());
        System.out.println(getMinInt());
        System.out.println("=========");
    }


}
