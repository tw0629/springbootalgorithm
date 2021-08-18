package com.tian.algorithm.Z_inter.array;

import java.util.*;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/2 02:16
 */
public class ArrayOp {

    private static int sum=0; //全排列个数
    private static int digui=0; //全排列个数

    /**
     * 合并两个有序的数组 方法1
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

    /**
     * 连续子数组的最大和
     *
     * 三种递推方程类似:
     * Math.max(temp + a[i], a[i]);
     * == Math.max(dp[i-1] + a[i], a[i]);
     * == Math.max(0,dp[i-1]) + arr[i];
     *
     */
    public static int maxsumofSubarray (int[] arr) {
        // write code here
        //dp[i]代表到第i位的时侯,以arr[i]结尾的连续子数组最大累加和
        int[] dp = new int[arr.length];//开辟dp

        dp[0] = arr[0];//初始化
        int maxSum = arr[0];//保存最终的结果

        for(int i = 1;i < arr.length;i ++){
            /**
             * Math.max(temp + a[i], a[i]);
             * = Math.max(dp[i-1] + a[i], a[i]);
             * = Math.max(0,dp[i-1]) + arr[i];
             */
            // 维护dp[i]
            dp[i] = Math.max(0,dp[i-1]) + arr[i];
            // 每更新一个dp值就更新一下maxSum
            maxSum = Math.max(maxSum,dp[i]);
        }
        return maxSum;
    }

    /**
     * 两数之和
     *
     * 下面为什么 +1 是因为题意要求返回
     * 输入：
     *      [3,2,4],6
     * 返回值：
     *      [2,3]
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] result = new int[2];
        //map里面放 键为target-每个数的结果 值为下标
        //每次放入的时候看是否包含 当前值
        //有的话说明当前值和已包含的值下标的那个元素为需要的结果
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<n;i++){
            if(map.containsKey(numbers[i])){
                // +1 是因为题意要求返回index+1
                result[0] = map.get(numbers[i])+1;
                result[1] = i+1;
                break;
            }
            else{
                map.put(target - numbers[i], i);
            }
        }
        return result;
    }
    /**
     * 两数之和2
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * https://wolfmua.blog.csdn.net/article/details/104479418
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    /**
     * 非递归
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


    /**
     * 递归  所有子集
     */
    public List<List<Integer>> childSets2(int[] nums) {
        List<List<Integer>> rs = new ArrayList<>();
        builderSubSet(nums,rs);
        rs.add(new ArrayList<Integer>());//最后添加一个空集
        return rs;
    }

    //求数组nums的所有非空子集，存于rs中
    public void builderSubSet(int[] nums,List<List<Integer>> rs){
        //递归终结条件，只有一个元素，它的非空子集就是自己，直接添加到rs
        if(nums.length == 1){
            rs.add(Arrays.asList(nums[0]));
        }else if(nums.length>1){
            //递归求解前n-1个元素的非空子集
            builderSubSet(Arrays.copyOf(nums,nums.length-1),rs);
            //前n-1个元素的非空子集求解完毕，处理第n个元素
            int size = rs.size();//获取当前子集的个数
            //将第n个子集也添加到rs
            rs.add(Arrays.asList(nums[nums.length-1]));
            //依次复制出当前子集，并为每一子集添加第n个元素，
            //之后再将这些子集添加到rs中
            List<Integer> clone;
            for(int i = 0;i<size;i++){
                clone = new ArrayList<>();
                for(Integer it : rs.get(i)) {
                    clone.add(it);
                }
                clone.add(nums[nums.length-1]);
                rs.add(clone);
            }
        }
    }

    /**
     * 所有子集
     * @param array
     */
    public static void printAllSubsets(int[] array) {
        if (null == array || 0 == array.length) {
            throw new IllegalArgumentException("数组不能为Null，至少有一个元素");
        }
        // 数组长度
        int len = array.length;
        // 根据数据的长度，得出所有二进制的个数
        // 如len =2；
        // 0 = 00 = {}
        // 1 = 01 = {1}
        // 2 = 10 = {2}
        // 3 = 11 = {1, 2}
        int allMasks = 1 << len; // (注意：这是1向右移动len位，而不是len向右移动1位)
        // 遍历所有的二进制表示方式
        for (int i = 1; i < allMasks; i++) {
            for (int j = 0; j < len; j++) {
                //当且仅当 i==2^j 的时候，i 按位与 1<<j 得到的结果不为 0
                // (i & (1 << j) ---> if(i==2^j) 即 1 << j 为 2^j  ???
                if ((i & (1 << j)) > 0) {
                    System.out.print(array[j] + " ");
                }
            }

            System.out.println();
        }
    }

    /**
     * 所有子集  中序遍历
     *
     * 每一层代表一个元素，左子树代表不使用该节点，右子树代表使用该节点，
     * 使用中序遍历来获得全部叶子结点，那么就能获得该数组的子集。
     *
     * https://blog.csdn.net/weixin_43940001/article/details/102874458
     * https://blog.csdn.net/u012118523/article/details/24884803
     */
    private static void getArr(List<Integer> list, List<List<Integer>> result, List<Integer> temp, int level)
    {
        if(level == list.size())
        {
            result.add(temp);
        }
        else
        {
            getArr(list, result, new ArrayList<>(temp), level + 1);
            temp.add(list.get(level));
            getArr(list, result, new ArrayList<>(temp), level + 1);
        }
    }


    /**
     * 全排列
     * @param chs
     * @param start
     */
    public static void permutation2(int[] chs,int start ) {
        if(start==chs.length-1) {
            System.out.println(Arrays.toString(chs));
            //如果已经到了数组的最后一个元素，前面的元素已经排好，输出。
        }
        for(int i=start;i<=chs.length-1;i++) {
            //把第一个元素分别与后面的元素进行交换，递归的调用其子数组进行排序
            Swap(chs,i,start);
            System.out.println("  Swap "+i+" "+start+" "+Arrays.toString(chs));

            permutation2(chs,start+1);

            Swap(chs,i,start);
            System.out.println("  Swap回去 "+i+" "+start+" "+Arrays.toString(chs));// 要关注这个打印

            //System.out.println("======");

            //子数组排序返回后要将第一个元素交换回来。
            //如果不交换回来会出错，比如说第一次1、2交换，第一个位置为2，子数组排序返回后如果不将1、2
            //交换回来第二次交换的时候就会将2、3交换，因此必须将1、2交换, 使1还是在第一个位置;
        }

        //System.out.println("######");

    }

    public static void Swap(int[] chs,int i,int j) {
        int temp;
        temp=chs[i];
        chs[i]=chs[j];
        chs[j]=temp;
    }

    /**
     * 最大的leftMax和rightMax之差的最大值
     *
     * 思路：
     * 差绝对值最大，即找出最大的数减去一个数
     * 首先找出所有数中的最大值，找出来之后分两种情况
     * 1.该最大值属于左部分，那怎么能使右部分的最大值最小呢，因为右部分必定包含了最后一个元素，
     *   所以右部分的最大值肯定>=最后一个元素，所以就把右部分切的只剩下最后一个元素，
     *   这样左部分的最大值依然使max，右部分的最大值就是最后一个元素
     * 2.最大值属于右部分，同理
     * 最后即求max-arr[0]和max-arr[arr.length-1]的绝对值最大值
     * https://www.jianshu.com/p/a028d69d7dd6
     */
    public static int maxleftright(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }


    /**
     * 最长无重复子数组  （和两个数的和 写法有点相似）
     * https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4?tpId=117&&tqId=37816&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
     *
     * 想象：
     * 固定j移动i; 当出现j和i指的数一样时候，将j移动到i位置
     */
    public int maxLength(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < arr.length; ++i) {
            if (map.containsKey(arr[i])) {
                j = Math.max(j, map.get(arr[i]) + 1);
            }
            map.put(arr[i], i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }


    public static void main(String[] args) {

        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        //int maxleftright = maxleftright(a);

        int[] array={1,2,3};
        //permutation(array,3,0);

        int[] array2={1,2,3};
        permutation2(array2,0);

        int[] array3={1,3,7,13};
        //printAllSubsets(array3);

        List<Integer> list = Arrays.asList(1,2,3);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int level = 0;
        getArr(list, result, temp, level);
        System.out.println("===="+result.toString());

        int[] array5 = { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println("Maximum contiguous sum is " + maxsumofSubarray(array5));

    }


}
