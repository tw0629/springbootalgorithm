package com.tian.algorithm.Z_newcowder;

import java.util.Scanner;

/**
 * @author David Tian
 * @desc
 * @since 2023/7/23 15:10
 */
public class 最小公倍数 {


    public Integer get(Integer num1, Integer num2) {
        Integer max = num1 > num2 ? num1 : num2;
        Integer min = num1 < num2 ? num1 : num2;

        for (int i = 1; i < min; i++) {
            if (i * max % min == 0) {
                return i * max;
            }
        }
        return num1 * num2;
    }
}
