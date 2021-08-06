package com.tian.algorithm.Z_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/2 14:57
 */
public class Tian {

    public void childNums(Integer[] a){
        List<String> list = new ArrayList<>();
        int length = a.length;
        if(length<=0){
            list.add("");
            return;
        }
        list.add("");
        list.add(a.toString());


        if(length==1){
            return;
        }
        List<String> temp = new ArrayList<>();
        for(int i=1; i<length-1;i++){
            for(int j=0; i<length;j++){
                Integer child = a[j];
                temp.add(child.toString());
            }
        }

        Integer[] tp = new Integer[0];
        int low = 0;
        int high = a.length-1;
        mergeSort(a,low,high,tp);

    }

    public void mergeSort(Integer[] a,Integer low,Integer high,Integer[] temp){

        if(low<=high){
            int mid = (low + high)/2;
            mergeSort(a,low,mid,temp);
            mergeSort(a,mid,high,temp);
            merge(a,low,mid,high,temp);
        }

    }
    public void merge(Integer[] a,int low,int mid,int high ,Integer[] temp){

        /*if(){
            temp[i++] =  a
        }*/

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        System.out.println("=======> " + childSets(nums));
    }

    /**
     * https://wolfmua.blog.csdn.net/article/details/104479418
     * @param nums
     * @return
     */
    public static List<List> childSets(int[] nums) {
        List<List> result = new ArrayList<>();

        List anws = new ArrayList<>();
        result.add(anws);

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List temp = new ArrayList<>(result.get(j));
                temp.add(num);
                result.add(temp);
            }
        }

        return result;
    }

}
