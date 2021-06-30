package com.tian.algorithm.pow;

/**
 * @author David Tian
 * @desc
 * @since 2021-06-09 20:41
 */
public class pow {

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
        double rest=pow1(x,n%2);
        return half*half*rest;
    }

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

    public static void main(String[] args) {

        System.out.println("===========>"+pow1(2,3));
        System.out.println("===========>"+pow1(2,-3));
        System.out.println("===========>"+pow1(2,0));

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
