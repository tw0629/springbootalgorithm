package com.tian.algorithm.classical.pow;

/**
 * @author David Tian
 * @desc
 * @since 2021-06-09 20:41
 */
public class pow {

    /**
     * n次方： 递归实现
     *
     * n次方根：二分法实现
     *
     */

    // n次方
    public static double pow1(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n==1) {
            return x;
        }
        if(n==-1) {
            return 1/x;
        }

        double half=pow1(x,n/2);
        double rest=pow1(x,n%2); //起正负号的作用
        return half*half*rest;
    }

    // n为负数时有点问题
    public static double pow2(double x, int n) {
        if(n<0) {
            x=1/x;
            n=-n;
        }
        int pow = 1;
        while (n!=0) {
            if ((n&1)==1) {
                pow*=x;
            }
            x*=x;
            n>>=1;
        }
        return pow;
    }


    public static void swap(int a,int b){

        System.out.println("======交换前=====> a:"+a+"b:"+b);

        /*b=a^b;
        a=a^b;
        b=a^b;*/
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("======交换后=====> a:"+a+"b:"+b);

    }

    /**
     * 平方根
     */
    public static double sqrt(double number) {
        double left = 0;
        double right = number;
        double mid = (left + right) / 2;
        double epsilon = 0.00001;
        while (Math.abs(mid * mid - number) > epsilon) {// !!!!!!
            if (mid * mid > number) { // !!!!!!
                right = mid;
            } else {
                left = mid;
            }
            mid = (left + right) / 2;
        }
        return mid;
    }

    /**
     * n次方根
     */
    public static double Nsqrt(double number, int n) {
        double left = 0;
        double right = number;
        double mid = (left + right) / 2;

        double epsilon = 0.00001;

        while (Math.abs(pow1(mid,n) - number) > epsilon) {
            if (pow1(mid,n) > number) { // !!!!!!
                right = mid;
            } else {
                left = mid;
            }
            mid = (left + right) / 2;
        }
        return mid;
    }

    public static double Nsqrt2(double number, int n) {
        double left = 0;
        double right = number;
        double mid = (left + right) / 2;
        double epsilon = 0.00001;
        double pow = pow1(mid,n);

        while (Math.abs(pow - number) > epsilon) {
            if (pow > number) { // !!!!!!
                right = mid;
            } else {
                left = mid;
            }
            mid = (left + right) / 2;
            pow = pow1(mid,n);
        }
        return mid;
    }


    public static void main(String[] args) {

//        System.out.println("===========>"+Nsqrt2(800,3));
//        System.out.println("===========>"+Math.round(Nsqrt2(800,3)));
//        System.out.println("===========>"+Math.round(Nsqrt2(800,3) * 100) / 100.0);


        System.out.println("===========>"+pow1(2,3));
        System.out.println("===========>"+pow1(5,3));
        System.out.println("===========>"+pow1(2,-3));
        System.out.println("===========>"+pow1(2,0));
        System.out.println("===========>"+pow1(2,10));

        System.out.println("======================");

        System.out.println("===========>"+pow2(2,10));
        System.out.println("===========>"+pow2(2,-3));
        System.out.println("===========>"+pow2(2,0));

        System.out.println("======================");

        swap(111111,88);

        System.out.println("======================");

        System.out.println("===========>"+7);
        System.out.println("===========>"+pow2(2,-3));
        System.out.println("===========>"+pow2(2,0));


    }



}
