package com.tian.algorithm.base_DataStructure;

/**
 * @author David Tian
 * @desc
 * @since 2022/9/23 11:19
 */
public class DataStructure_Init {

    public static void Array_Init() {

        // 一维数组
        // 1 静态初始化
        int array10[] = {1,2,3,4,5,6,7,8,9};
        // 2 动态初始化
        int[] array11 = new  int[10];
        for(int i=0;i<array11.length;i++){
            array11[i]=i;
        }

        // 二维数组
        // 1 静态初始化
        int[][] array20={{1,2,3},{4,5,6},{7,8,9}};
        // 2 动态初始化
        int[][] array21=new  int[3][3];
        for(int i=0;i<array21.length;i++){
            for(int j=0;j<array21[i].length;j++)
                array21[i][j]=i+j;
        }
    }

}

