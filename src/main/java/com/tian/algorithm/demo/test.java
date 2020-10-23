package com.tian.algorithm.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author David Tian
 * @desc
 * @since 2020-06-01 16:48
 */
public class test {

    public static void main(String[] args) {

        /*数值*/
        Scanner reader = new Scanner(System.in) ;
        while(reader.hasNext())
        {
            int m = reader.nextInt() ;
            int [] numbers = new int[m] ;
            for(int index=0;index<m;index++)
            {
                numbers[index] = reader.nextInt();
            }
            System.out.println(Arrays.toString(numbers));
        }
        reader.close() ;

        /*字符串*/
        Scanner in = new Scanner(System.in);
        String c = in.next() ;
        System.out.println(c);
        in.close();


        /*格式化输出*/
       // System.out.printf("%8.2f",float)
    }


    /**
     * 给你一个整数数组 arr 和两个整数 k 和 threshold 。
     * 请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
     */
    @Test
    public void test(){

        int[] arr = {2,3,5,4,4,3};
        int k = 3;
        int threshold = 4;
        int sum = numOfSubarrays(arr,k,threshold);
        System.out.println("==========>"+sum);
    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        int flag = 0;
        //边界条件
        while (flag < arr.length - k) {
            //存放数组和
            int temp = 0;
            //从flag开始取一个k大小的窗口
            for (int i = flag; i <flag+ k; i++) {
                temp += arr[i];
            }
            if (temp / k >= threshold) {
                sum++;
            }
        }
        return sum;
    }

    public int numOfSubarrays2(int[] arr, int k, int threshold) {
        if(arr.length==0) {
            return 0;
        }
        int count=0,len=0,sum=0,number=k;
        while(k<=arr.length){
            count=0;
            for(int i=sum;i<k;i++) {
                count += arr[i];
            }
            if(count/number>=threshold) {
                len++;
            }
            k++;
            sum++;
        }
        return len;
    }

    @Test
    public void test2(){
        int[] arr = {2,3,5,4,4,3};
        int k = 1;
        int threshold = 5;
        int[] sortarr = quickSort(arr,k,threshold);

        System.out.println("==========>"+Arrays.toString(sortarr));
    }


    /**
     * @Description: 快速排序 平均时间复杂度：O(N*logN)
     * @Date: 14:13 2019/8/8
     * @Param: [arr]
     * @return: int[]
     */
    private  int[] quickSort(int a[], int l, int r) {
        if (l >= r) {
            return a;
        }
        int i = l;
        int j = r;
        //选择第一个数为key
        int key = a[l];

        while (i < j) {
            //从右向左找第一个小于key的值
            while (i < j && a[j] >= key) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }

            //从左向右找第一个大于key的值
            while (i < j && a[i] < key) {
                i++;
            }

            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        //递归调用
        quickSort(a, l, i - 1);
        //递归调用
        quickSort(a, i + 1, r);
        return a;
    }

}

