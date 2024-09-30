package com.tian.algorithm.classical.pow;

import java.util.Scanner;

/**
 * @aut
 */
public class 最小公倍数 {


    public static Integer get(Integer num1, Integer num2) {
        Integer max = num1 > num2 ? num1 : num2;
        Integer min = num1 < num2 ? num1 : num2;

        for (int i = 1; i < min; i++) {
            if (i * max % min == 0) { // 注意：执行顺序是 先乘后%
                return i * max;
            }
        }
        return num1 * num2;
    }

    public static void main(String[] args) {

        System.out.println("===========>"+ 2 * 8 % 5);
        System.out.println("===========>"+get(5,8));
        System.out.println("===========>");
        System.out.println("===========>");

    }
}
