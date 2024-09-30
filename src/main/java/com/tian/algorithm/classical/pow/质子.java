package com.tian.algorithm.classical.pow;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author David Tian
 * @desc
 * @since 2023/7/21 16:59
 */
public class 质子 {

    // 功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）
    public static void main(String[] args) {
        // Scanner number = new Scanner(System.in);
        // int v = number.nextInt();
        int v =180;
        for (long i = 2; i <= v;) { //!!!!!! 注意: i <= v 不能写成 i <= 180
            if (v % i == 0) {
                System.out.print(i+" ");
                v /= i;
            } else {
                ++i;
            }
        }
    }

    public static void test2() {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        for (long i = 2; i <= num; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println();
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        System.out.println(s1);

        String s2 = scanner.nextLine();
        String[] a = s2.split(" ");
        System.out.println(a);


        while(scanner.hasNext()){
            int s3 = scanner.nextInt();
            System.out.println(s3);
        }


        System.out.println();
    }
}
