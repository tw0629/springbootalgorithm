package com.tian.algorithm.base_op.array;

import org.springframework.util.CollectionUtils;

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
     * 思路有点类似： 递推有点类似
     *     积水问题 com.tian.algorithm.Z_inter.other.DynamicProgram#waterSum2(int[])
     *     判断字符串可否由列表中的单词组成 com.tian.algorithm.Z_inter.dict.DictOperate#wordBreak(java.lang.String, java.util.List)
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
            //dp[i] = Math.max(0,dp[i-1]) + arr[i];
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);;
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
    public static int[] twoSum(int[] numbers, int target) {
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
    // twoSum1优化
    public static int[] twoSum11(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                System.out.println("twoSum2: "+nums[i]+"和"+(target - nums[i]));
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 两数之和变种------找到所有满足条件的数对
     * https://blog.csdn.net/chk_plusplus/article/details/119417432
     * 两数之和、三数之和、四数之和、N数之和
     * https://blog.csdn.net/qq_35655602/article/details/116170755
     */

    /**
     * 两数之和3
     * 思路：排序加双指针
     */
    public static List<List<Integer>> twoSum3(int[] nums, int target) {
        Arrays.sort(nums);

        int len = nums.length;
        int i = 0, j = len - 1;
        List<List<Integer>> all = new ArrayList<>();

        while(i < j){
            if(nums[i] + nums[j] == target){
                /*List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                all.add(list);*/
                all.add(Arrays.asList(nums[i], nums[j]));
                ++i;
                --j;

                // 以下两步去重处理
                while(i < j && nums[j] == nums[j + 1]) --j;
                while(i < j && nums[i] == nums[i - 1]) ++i;
            }else if(nums[i] + nums[j] < target){
                ++i;
            } else{
                --j;
            }
        }
        return all;
    }

    /**
     * 三数之和
     *
     * 排序+ 最外层去重 + 内层去重
     */
    public static List<List<Integer>> threeSum3(int[] nums, int target) {
        List<List<Integer>> threeSum = new ArrayList<>();
        //排序
        Arrays.sort(nums);

        for(int i = 0 ; i<nums.length; i++){
            // 最外层去重
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }

            List<List<Integer>> lists = twoSum3(nums, target - nums[i]);
            if(CollectionUtils.isEmpty(lists)){
                continue;
            }
            for(List<Integer> list: lists){
                list.add(nums[i]);
                threeSum.add(list);
            }
        }

        return threeSum;
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
            //System.out.println("  Swap "+i+" "+start+" "+Arrays.toString(chs));

            permutation2(chs,start+1);

            Swap(chs,i,start);
            //System.out.println("  Swap回去 "+i+" "+start+" "+Arrays.toString(chs));// 要关注这个打印

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
     * |leftMax-rightMax|绝对值最大
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
                // 每次无重复字符串的起始位置
                j = Math.max(j, map.get(arr[i]) + 1);// ??
            }
            map.put(arr[i], i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * 请参考一下： com.tian.algorithm.leedcode.a39a40_组合总和#combinationSum1(int[], int)
     * 此题写法有问题
     */
    static int[] arr = new int[100];
    static int index = 0;// 记录当前
    public static void numGroup(int[] arr, int start, int length, int sum) {
        if (sum == 0) {
            for (int j = 0; j < index; j++) {
                System.out.print(arr[j]+" ");
            }
            //System.out.println();
        } else {
            for (int i = start; i < length; i++) {
                arr[index++] = arr[i];
                numGroup(arr, i + 1, length-1, sum - arr[i]);
            }
        }
        index--;
    }

    // ？？
    public static int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    /**
     * 一 数组中和为定值的连续子集 （连续）
     */

    /**
     * 从数组中找出和为指定值的任意组合（任意的）
     * 方法一
     *
     * https://blog.csdn.net/dzh0622/article/details/112407803
     *
     * ### System.arraycopy
     * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * 代码解释:
     * 　　Object src : 原数组
     *    int srcPos : 从元数据的起始位置开始
     * 　　Object dest : 目标数组
     * 　　int destPos : 目标数组的开始起始位置
     * 　　int length  : 要copy的数组的长度
     *
     * ### Arrays.stream(array).sum()
     *
     *  关键：可以通过打印出结果，思考这种【 for循环 + 递归 】的过程
     *  这种过程类似：全排列 的过程
     */
    public static void printCombinations(int[] array, int pos, int sum, int[] acc) {
        if (Arrays.stream(acc).sum() == sum) {
            System.out.println("======> "+Arrays.toString(acc));
        }
        for (int i = pos + 1; i < array.length; i++) {
            int[] newAcc = new int[acc.length + 1];
            System.arraycopy(acc, 0, newAcc, 0, acc.length);
            newAcc[acc.length] = array[i];

            //System.out.println("前："+Arrays.toString(newAcc));
            printCombinations(array, i, sum, newAcc);
            //System.out.println("后："+Arrays.toString(newAcc));

        }
    }

    /**
     * 方法二
     * 从数组中找出和为指定值的任意组合
     *
     * 自己成功摸索出来的
     *
     * 思路类似：https://www.cnblogs.com/zhaoshujie/p/11555586.html,但写法不一样
     * https://blog.nowcoder.net/n/08521a63d59946f1af9ed5a19ffc65b6
     */
    private static void findNums(int[] candidates,List<Integer> list, int remainder, int loop)
    {
        if (remainder == 0)//找到一组
        {
            //Console.WriteLine($"第{++groupNum}组：{string.Join(" + ", list.Where(x => x.Used).Select(x => x.Number).ToArray())}");
            println(candidates,list);
            return;
        }
        for (int i = loop; i >= 0; i--)
        {
            if (!list.contains(i) && (remainder - candidates[i]) >= 0)
            {
                // 放的是下标,因为可能元素重复
                list.add(i);
                findNums(candidates,list, remainder - candidates[i], i - 1);
                // 注意：是出去元素
                list.remove(Integer.valueOf(i));
            }
        }
    }
    public static void println(int[] candidates,List<Integer> list){

        System.out.print("【");
        for(Integer integer : list){
            System.out.print(candidates[integer]+",");
        }
        System.out.println("】");
    }


    /**
     * 方法三
     * 从数组中找出和为指定值的任意组合
     * https://blog.nowcoder.net/n/08521a63d59946f1af9ed5a19ffc65b6
     **/
    static List<List<Integer>> lists = new ArrayList<>();
    //这个list存放所有数组
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }
        List<Integer> list = new ArrayList<>();
        dfs(list,candidates,target,0);
        //第一个list存放小数组，
        return lists;
    }

    private static void dfs(List<Integer> list, int[] candidates,int target,int start) {
        //递归的终止条件
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                //*********************************************************
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                //*********************************************************
                System.out.println("前: "+list.toString());
                dfs(list, candidates, target - candidates[i], i);
                System.out.println("后: "+list.toString());

                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 方法四
     * 变形：从数组中找出和为指定值的连续子组合
     *
     * https://blog.csdn.net/qq_33775774/article/details/111305288
     **/
    public static List<Integer>  getSubSequenceEqualTargetSum(int[] nums, int target){
        List<Integer>  result = new ArrayList<>();
        int sum=0;
        int  start = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];

            if(sum == target){
                for(int j=start;j<=i;j++){
                    result.add(nums[j]);
                    System.out.print(nums[j]+" ");
                }
                break;
            } else  if(sum>target){
                sum -= nums[start++];
            }
        }

        System.out.println("result: "+result.toString());

        return result;

    }

    /**
     * 方法二
     * 从数组中找出和为指定值的任意组合
     * http://blog.chinaunix.net/uid-25979788-id-3276802.html
     *
     * function getSquence($n){
     *     $low=1;
     *     $high=2;
     *     $mid=($n+1)/2;
     *     $sum=$low+$high;
     *     while($low<$mid){
     *         if($sum==$n){
     *             echo $low.'-'.$high.' ';
     *             $sum-=$low;
     *             $low++;
     *         }else if($sum>$n){
     *             $sum-=$low;
     *             $low++;
     *         }else{
     *             $high++;
     *             $sum+=$high;
     *         }
     *     }
     * }
     */

    // ???
    static int solve(int A[], int N, int val)
    {
        int count = 0, beg = 1, end = 0, sum = A[0];
        while (beg < end && end < N) {
            if (sum == val) {
                count++;
                // A[beg] -> A[end]
                sum += A[++end];
            } else if (sum < val) {
                sum += A[++end];
            } else {
                sum -= A[beg++];
            }
        }
        return count;
    }

    /**
     * 数组中两个字符串的最小距离
     *
     * https://blog.csdn.net/zuochao_2013/article/details/78991000
     */
    public static int getMinDistance(String[]strs,String str1,String str2){
        if(str1==null||str2==null||strs==null){
            return -1;
        }
        //模式串的值相同
        if(str1.equals(str2)){
            return 0;
        }
        int last1=-1; //模式串1初始化
        int last2=-1; //模式串2初始化
        int min=Integer.MAX_VALUE; // 不存在时

        for(int i=0;i!=strs.length;i++){
            /*if(strs[i].equals(str1)) {
                min=Math.min(min,last2==-1?min:i-last2);
                last1=i;
            }
            if(strs[i].equals(str2)) {
                min=Math.min(min,last1==-1?min:i-last1);
                last2=i;
            }*/
            // 好理解
            if(strs[i].equals(str1)) {
                last1=i;   // 因为是下标从小到大遍历,所以此时 last1一定大于last2
                min=Math.min(min,last2==-1?min:last1-last2);
            }
            if(strs[i].equals(str2)) {
                last2=i;   // 因为是下标从小到大遍历,所以此时 last2一定大于last1
                min=Math.min(min,last1==-1?min:last2-last1);
            }
        }

        return min==Integer.MAX_VALUE?-1:min;
    }


    public static void main(String[] args){

        //int[] array5 = { -2, -3, 4, -1, -2, 1, 5, 7 };
        int[] array5 = { 1, 8, 3, 6, 5, 2, 9, 7, 4 ,4, 6};
        //System.out.println("twoSum is " + twoSum3(array5,10));
        int[] array51 = { 2, 4, 2, 1, 4, 2, 2, 1, 4 };
        //System.out.println("twoSum is " + threeSum3(array51,6));

        int[] arr = { 1, 3, 2, 4, 5, 6, 7, 8, 9 };

        //int[] arr = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        int[] arr2 = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        int[] arr3 = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        Arrays.sort(arr);
        int sum = 7;
        //numGroup(arr, 0, arr.length, sum);
        int i = subarraySum(arr, sum);
        //printCombinations(arr3, -1, 7, new int[]{});

        int[] array6 = { -2, -3, 4, 3, -2, 1, 5, 7 };
        System.out.println("Maximum contiguous sum is " + maxsumofSubarray(array6));
        System.out.println("====");


        /*int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        //int maxleftright = maxleftright(a);

        int[] array={1,2,3};
        //permutation(array,3,0);

        int[] array2={1,2,3,4,5,6};
        permutation2(array2,0);

        int[] array3={1,3,7,13};
        //printAllSubsets(array3);

        List<Integer> list = Arrays.asList(1,2,3);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int level = 0;
        getArr(list, result, temp, level);
        System.out.println("===="+result.toString());

        int[] array5 = { -2, -3, 4, -1, -2, 1, 5, 7 };
        System.out.println("Maximum contiguous sum is " + maxsumofSubarray(array5));
        System.out.println("twoSum is " + twoSum2(array5,5));*/


        //int[] arr = { 1, 3, 2, 4, 5, 6, 7, 8, 9 };
        /*
        int[] arr = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        int[] arr2 = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        int[] arr3 = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        Arrays.sort(arr);
        int sum = 7;
        numGroup(arr, 0, arr.length, sum);
        int i = subarraySum(arr2, sum);
        printCombinations(arr3, -1, 7, new int[]{});
        */

        //int[] q = {10,1,2,7,6,1,5}; 因为这个宿数组有重复元素
        int[] q = { 1, 8, 3, 6, 5, 2, 9, 7, 4 };
        int tar = 8;
        List<List<Integer>> lists = combinationSum(q, tar);
        System.out.println("combinationSum: "+lists.toString());

        List<Integer> list = new ArrayList<>();
        Arrays.sort(q);
        findNums(q,list,tar,q.length-1);

        /*String[] strs = {"rr","ab","ee","123","ww","ab","1234","qq"};
        String str1 = "ab";
        String str2 = "123";
        int minDistance = getMinDistance(strs, str1, str2);
        System.out.println("GetMinDistance: "+minDistance);*/

        int[] nums = {3,2,1,5,4,3,7,9};
        //getSubSequenceEqualTargetSum(nums,12);
        int count = solve(nums, nums.length, 12);

        //System.out.println(Arrays.toString(twoSum(nums,7)));
        //System.out.println(Arrays.toString(twoSum11(nums,7)));
        //System.out.println(Arrays.toString(twoSum2(nums,7)));

        /*int[][] arr = new int[3][]; //定义个一维数组,长度为3, 每个位置放入一个数组
        arr[0] = new int[]{1,2,3}
        arr[1] = new int[]{4,5}
        arr[2] = new int[]{6...}*/
        //int[][] arr = {{12,33},{33,45},{23,55},{66,77}};
        //int[][] arr = new int[][] {{12,33,45},{15,23,55},{66,77}};


        //Arrays.sort(arr, (v1, v2) -> v1[0] - v2[0]);

        System.out.println(Arrays.stream(arr).toArray());
    }
}

