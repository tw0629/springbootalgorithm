package com.tian.algorithm.leedcode;

/**
 * @author David Tian
 * @desc
 * @since 2022/10/23 11:49
 */
public class a88_合并两个有序数组 {

    /**
     * 合并两个有序的数组 方法1  推荐
     *
     * 这种写法省去了空间复杂度；相对于归并写法中的合并
     */
    public void merge(int A[], int m, int B[], int n) {
        // 因为题目明确说了A数组足够大，所以直接在A数组操作
        // 倒序的
        int i = m - 1;
        int j = n - 1;
        //AB合并后最后一个元素所在位置
        int index = m + n - 1;

        // AB合并，谁大就先放谁
        while(i >= 0 && j >= 0)
        {
            A[index --] = A[i] > B[j] ? A[i --] : B[j --];
        }
        // 如果B没有遍历完，那么之间丢在A数组里面
        while(j >= 0)
        {
            A[index --] = B[j --];
        }
    }

    /**
     * 合并两个有序的数组 方法2
     *
     * 归并写法
     */
    public void merge2(int A[], int m, int B[], int n) {
        int[] C = new int[m+n];
        int index1 = 0;
        int index2 = 0;

        int i = 0;
        while(i < C.length){
            while((index1<=m-1) && (index2<=n-1)){

                if(A[index1]<=B[index2]){
                    C[i++] = A[index1++];
                }else{
                    C[i++] = B[index2++];
                }
            }

            if(index1>m-1){
                while(index2<=n-1){
                    C[i++] = B[index2++];
                }
            }

            if(index2>n-1){
                while(index1<=m-1){
                    C[i++] = A[index1++];
                }
            }

        }

        for(int j=0; j< C.length; j++){
            A[j] = C[j];
        }
    }

}
