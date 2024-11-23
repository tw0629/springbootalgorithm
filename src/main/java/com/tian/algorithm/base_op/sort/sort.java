package com.tian.algorithm.base_op.sort;

/**
 * @author David Tian
 * @desc  快堆归
 * @since 2021/8/2 14:49
 */
public class sort {
}
/*
    static int[] quickSort(int a[], int l, int r) {
        if (l >= r) {
            return a;
        }
        int i = l;
        int j = r;
        int key = a[l];//选择第一个数为key

        while (i < j) {
            while (i < j && a[j] >= key)//从右向左找第一个小于key的值
                j--;
            if (i < j) {
                a[i] = a[j];//!!! 不是交换
                i++;
            }

            while (i < j && a[i] < key)//从左向右找第一个大于key的值
                i++;

            if (i < j) {
                a[j] = a[i];//!!! 不是交换
                j--;
            }
        }
        //i == j
        a[i] = key;
        quickSort(a, l, i - 1);//递归调用
        quickSort(a, i + 1, r);//递归调用
        return a;
    }

    ==================================================
    ==================================================

    // 寻找topN，该方法改变data，将topN排到最前面
    public void findTopN(int n, int[] data) {
        // 先构建n个数的小顶堆
        buildHeap(n, data);
        // n往后的数进行调整
        for(int i = n; i < data.length; i++) {
            adjust(i, n, data);
        }
    }
    // for+while
    private void buildHeap(int n, int[] data) {
        for(int i = 1; i < n; i++) { //i = 1 开始
            int t = i;
            // 调整堆
            while(t != 0 && data[parent(t)] > data[t]) { //大就交换  !!!!!! t!=0
                int temp = data[t];
                data[t] = data[parent(t)];
                data[parent(t)] = temp;

                t = parent(t);
            }
        }
    }

    // 调整data[i]   ----> 先构建n个数的小顶堆,n往后的数进行调整 注意：构建初始堆的时候，是和它的左右子调换
    private void adjust(int i, int n, int[] data) {
        if(data[i] <= data[0]) {
            return;
        }
        // 置换堆顶
        int temp = data[i];
        data[i] = data[0];
        data[0] = temp;

        // 调整堆顶
        int t = 0;
        while( (left(t) < n && data[t] > data[left(t)])
                || (right(t) < n && data[t] > data[right(t)]) ) {
            if(right(t) < n && data[right(t)] < data[left(t)]) {
                // 右孩子更小，置换右孩子
                temp = data[t];
                data[t] = data[right(t)];
                data[right(t)] = temp;

                t = right(t);
            } else {
                // 否则置换左孩子
                temp = data[t];
                data[t] = data[left(t)];
                data[left(t)] = temp;

                t = left(t);
            }
        }
    }

    ==================================================
    ==================================================

    public static void mergeSort(int[] arr,int low,int high,int[] tmp){
        if(low<high){
            int mid = (low+high)/2;
            mergeSort(arr,low,mid,tmp);     //对左边序列进行归并排序
            mergeSort(arr,mid+1,high,tmp);  //对右边序列进行归并排序
            merge(arr,low,mid,high,tmp);    //合并两个有序序列
        }
    }

    public static void merge(int[] arr,int low,int mid,int high,int[] tmp){
        int i = 0;
        //左边序列和右边序列起始索引
        int j = low;
        int k = mid+1;

        while(j <= mid && k <= high){
            if(arr[j] < arr[k]){
                tmp[i++] = arr[j++];
            }else{
                tmp[i++] = arr[k++];
            }
        }
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while(j <= mid){
            tmp[i++] = arr[j++];
        }

        while(k <= high){
            tmp[i++] = arr[k++];
        }

        for(int t=0;t<i;t++){
            arr[low+t] = tmp[t]; // 关键：low + t
        }
    }

*/
