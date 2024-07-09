package com.tian.algorithm.leedcode;

import java.util.*;

/**
 * 滑动窗口中位数
 */
public class a480_MedianSlidingWindow {

    //推荐 方法一 和 方法五
    //推荐 方法一 和 方法五
    //推荐 方法一 和 方法五

    // 思路一样的：方法一,方法二,方法六 （方法一,方法二是用了PriorityQueue, 方法六是纯运算）
    //           方法四,方法五       （暴力）
    //           方法三

    /**
     * 方法一
     *   gpt
     *
     *   PriorityQueue 默认从小到大
     */
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 最大堆，用于存储较小的一半
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 最小堆，用于存储较大的一半

    public double[] medianSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1]; //!!!易写错 double类型

        for (int i = 0; i < n; i++) {
            // 将当前元素添加到适当的堆中
            addNum(nums[i]);

            // 如果当前窗口大小大于等于 k，计算并存储中位数
            if (i >= k - 1) {
                result[i - k + 1] = findMedian();

                // 从堆中移除滑出窗口的元素
                removeNum(nums[i - k + 1]); //!!!易写错  移出的是队列第一个
            }
        }

        return result;
    }

    /**
     *
     * 最大堆，用于存储较小的一半
     * 最小堆，用于存储较大的一半
     */
    private void addNum(int num) {
        // !!!或者关系   maxHeap.isEmpty() || num <= maxHeap.peek()
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        balanceHeaps();
    }

    // 感觉不对，万一有相同元素就都删除了
    private void removeNum(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        // 平衡两个堆的大小，使得最大堆的大小不超过最小堆大小加1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) { //!!!易写错
            maxHeap.add(minHeap.poll());
        }
    }

    private double findMedian() {
        // 注意：是用 maxHeap.size() > minHeap.size() 作比较
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

//    public static void main(String[] args) {
//        SlidingWindowMedian swm = new SlidingWindowMedian();
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int k = 3;
//        double[] medians = swm.medianSlidingWindow(nums, k);
//
//        for (double median : medians) {
//            System.out.print(median + " ");
//        }
//    }


    /**
     * 方法二 （和方法一 一样）
     *      巧妙利用：PriorityQueue
     *  https://leetcode.cn/problems/sliding-window-median/description/
     *
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        /*
        滑窗+对顶堆:
        我们创建两个堆left和right,其中left是大顶堆存储小的一半元素,right为小顶堆存储大的一半元素
        假定right存储的元素数目总是>=left存储的元素数目
        1.当窗口元素总数为奇数时:中位数为排序k/2的数字,此时直接right堆顶就是答案
        2.当窗口元素总数为偶数时:中位数为排序k/2与(k-1)/2的均值,此时将left堆顶与right堆顶取均值即可\
        还要注意的是:窗口滑动过程中我们加入与删除元素后记得调整堆使得堆平衡
         */
        int len = nums.length;
        int cnt = len - k + 1;  // 滑窗个数
        double[] res = new double[cnt];
        // Integer.compare(b, a)逻辑为:(x < y) ? -1 : ((x == y) ? 0 : 1) 只比较不会加减
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 大顶堆(注意不要b-a防止溢出)
        PriorityQueue<Integer> right = new PriorityQueue<>((a, b) -> Integer.compare(a, b)); // 小顶堆
        // 初始化堆:[0,k-1] 使得right>=left
        for (int i = 0; i < k; i++) {
            right.add(nums[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            left.add(right.poll()); // 弹出最小的数字给left
        }
        // 首个中位数加入res
        res[0] = getMid(left, right);
        // 这里的i代表即将加入窗口的右端元素
        for (int i = k; i < len; i++) {
            int a = nums[i], b = nums[i - k];   // a为即将加入窗口的元素,b为即将退出窗口的元素
            if (a >= right.peek()) {
                right.add(a);
            } else {
                left.add(a);
            }
            if (b >= right.peek()) {
                right.remove(b);
            } else {
                left.remove(b);
            }
            // 调整堆
            adjust(left, right);
            // 该窗口中位数加入结果
            res[i - k + 1] = getMid(left, right);
        }
        return res;
    }

    // 调整堆使得堆平衡
    private void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());  // 左边比右边多,左边必定不符合条件,往右边搬
        while (right.size() > left.size() + 1) left.add(right.poll());  // 右边比左边多1以上,右边必定多了,往左边搬
    }

    // 根据left与right两个堆返回中位数
    private double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) return left.peek() / 2.0 + right.peek() / 2.0; // 范围不知道防止溢出
        else return (double) right.peek();
    }


    /**
     * 方法三
     *      滑动+二分
     *
     */
    public double[] medianSlidingWindow3(int[] nums, int k) {
        double[] res = new double[nums.length+1-k];
        List<Integer> list = new ArrayList<>();
        int index, pos;

        // 对前k个数字使用插入排序，并使用二分查找找到最佳插入点
        for(int i=0; i<k; i++){
            pos = binarySearch(list, nums[i]);
            list.add(pos, nums[i]);
        }
        res[0] = ((double)list.get((k-1)/2)+(double)list.get(k/2))/2.0;

        for(int end=k; end<nums.length; end++){
            // 使用二分查找找到需要删除数字的下标并删除
            index = binarySearch(list, nums[end-k]);
            list.remove(index);
            // 使用二分查找找到最佳插入点
            pos = binarySearch(list, nums[end]);
            list.add(pos, nums[end]);
            res[end+1-k] = ((double)list.get((k-1)/2)+(double)list.get(k/2))/2.0;
        }

        return res;
    }

    // 使用二分查找寻找下标
    private int binarySearch(List<Integer> data, int target){
        int l=0, r=data.size()-1;
        while(l <= r){
            int m=l+(r-l)/2;
            if(data.get(m) > target)
                r = m-1;
            else if(data.get(m) < target)
                l = m+1;
            else
                return m;
        }
        return l;
    }


    /**
     * 方法四  （和方法五一样）
     * 暴力
     */
    public double[] medianSlidingWindow4(int[] nums, int k) {
        double[] ret = new double[ nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int[] temp = Arrays.copyOfRange(nums, i, i + k);
            ret[i] = helper(temp);
        }
        return ret;

    }

    private double helper(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        double ret = 0;
        if (len % 2 == 0) {
            //可以有效防止数据溢出
            ret = nums[len / 2] / 2.0 + nums[len / 2 - 1] / 2.0;
        } else {
            ret = nums[len / 2];
        }
        return ret;
    }

    /**
     * 方法五 （和方法四一样）
     * 暴力
     */
    public double[] medianSlidingWindow5(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        int[] ans = new int[k];

        for(int i=0;i<nums.length-k+1;i++){
            // !!!j永远从0开始     每次都是新的k个的数组
            for(int j=0;j<k;j++){
                ans[j] = nums[i+j];
            }

            Arrays.sort(ans); //!!! 可以将sort换成排序方法

            if(k%2==1){
                res[i] = ans[k/2];
            }else {
                res[i] = (ans[k/2]/2.0+ans[k/2-1]/2.0);
            }
        }

        return res;
    }


    /**
     * 方法六 （和方法一 ，方法二 一样，只是没有用PriorityQueue）
     */
    public double[] medianSlidingWindow6(int[] nums, int k) {
        double[] res = new double[nums.length-k+1];
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<k;i++){
            list.add(nums[i]);
        }
        Collections.sort(list);
        int index = 0;
        res[index] = getMid(list);
        for(int i=k;i<nums.length;i++){
            index++;
            rem(list, nums[i-k]);
            ins(list, nums[i]);
            res[index] = getMid(list);
        }
        return res;
    }

    //获取list的中位数
    public double getMid(List<Integer> list){
        int size = list.size();
        if((size&1)==0){//偶数
            return ((double)list.get(size/2-1)+(double)list.get(size/2))/2;
        }else{
            return (double)list.get(size/2);
        }
    }

    //窗口滑动时，删除前面的一个元素
    public void rem(List<Integer> list, int n){
        int left = 0;
        int right = list.size()-1;
        while(left<right){
            int mid = (left + right) / 2;
            if(list.get(mid)>n){
                right = mid-1;
            }else if(list.get(mid)<n){
                left = mid + 1;
            }else{
                left = mid;
                right = mid;
            }
        }
        list.remove(left);
    }

    //窗口滑动时，添加元素
    public void ins(List<Integer> list, int n){
        if(list.size()==0){
            list.add(n);
            return;
        }
        int left = 0;
        int right = list.size()-1;
        while(left<right){
            int mid = (left + right) / 2;
            if(list.get(mid)>n){
                right = mid-1;
            }else if(list.get(mid)<n){
                left = mid + 1;
            }else{
                left = mid;
                right = mid;
            }
        }
        if(n>list.get(left)){
            list.add(left+1, n);
        }else{
            list.add(left, n);
        }
    }

}
