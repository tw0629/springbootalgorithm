package com.tian.algorithm.leedcode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 *  滑动窗口最大值
 */
public class a239_MaxSlidingWindow {

    //推荐 方法二
    //推荐 方法二
    //推荐 方法二

    /**
     * 方法一
     * ！！！目前我看见的最好理解的   巧妙利用：PriorityQueue
     *
     * https://blog.csdn.net/nandao158/article/details/117841636
     * 思路：用一个大顶堆，保存当前滑动窗口中的数据。滑动窗口每次移动一格，就将前面一个数出堆，后面一个数入堆。
     */
    //!!!易写错
    public static PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>((o1, o2)->o2-o1);//大顶堆
    public static ArrayList<Integer> result = new ArrayList<Integer>();//保存结果的集合

    private static ArrayList<Integer> maxSlidingWindow11(int[] num, int size) {
        if(num == null || num.length < 1 || size < 1 || size > num.length){
            return result;
        }
        int count; // 注意
        //初始化滑动窗口
        for(count = 0;count < size;count++){
            maxQueue.offer(num[count]);
        }
        //对每次操作，找到最大值（用优先队列的大顶堆），然后向后滑动（出堆一个，入堆一个）
        while (count < num.length){
            result.add(maxQueue.peek());
            maxQueue.remove(num[count - size]);// 出堆第一个
            maxQueue.offer(num[count]);// 入堆最后一个
            count++;
        }

        result.add(maxQueue.peek());//最后一次入堆后没保存结果，这里额外做一次即可

        return result;

    }

    /**
     * 方法二
     * 答案里最多的解法   利用：双向队列 LinkedList
     * 注意：代码实现中，双端队列中存的是数组值的下标，并不是值本身
     *
     * 记住： 1 双端队列永远只放一个最大的；2 双端队列放的是 数组值的下标index
     *
     * https://blog.csdn.net/a1439775520/article/details/104636069
     * https://blog.csdn.net/weixin_43218500/article/details/105621894
     *
     */
    public static int[] maxSlidingWindow21(int[] nums, int k) {
        if(nums==null||nums.length<2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数按从大到小排序
        LinkedList<Integer> list = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length-k+1];
        for(int i=0;i<nums.length;i++){
            // 保证从大到小 如果前面数小 弹出   // 前面小的全部弹出去  // 这样的话，双向队列头部永远为最大的
            while(!list.isEmpty()&&nums[list.peekLast()]<=nums[i]){ //!!!易写错
                list.pollLast(); //!!!易写错
            }
            // 添加当前值对应的数组下标
            list.addLast(i); //!!!易写错  addLast 和 入队的是i
            // 初始化窗口 等到窗口长度为k时 下次移动在删除过期数值 // 只要i移动到k-1以后，就入队队列中的最大值
            if(list.peek()<=i-k){ //控制窗口宽度 即：i >= list.peekFirst + k   //!!!易写错
                list.poll();
            }
            // 当i>=k-1时,向右每移动一位，都保存当前窗口中最大值
            if(i-k+1>=0){//控制从啥时候加最大值  从i>=k-1开始记录滑动窗口中最大的   //i>=k-1时就往里装  //!!!易写错
                result[i-k+1] = nums[list.peek()];
            }
        }
        return result;
    }

    /**
     * 方法
     */
    public static int[] maxSlidingWindow22(int[] arr,int k) {
        if (arr.length == 0 || arr.length == 1) return arr;
        int[] res = new int[arr.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            //添加数据时，满足2、3状态时，弹出队尾
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[i]) {
                list.pollLast();
            }
            list.addLast(i);
            //队头下标越界弹出队头
            if (i - k >= list.peekFirst()) {
                list.pollFirst();
            }
            if (i + 1 >= k) {
                res[i - k + 1] = arr[list.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 方法
     * https://www.cnblogs.com/nicaicai/p/12293753.html
     */
    public int[] maxSlidingWindow23(int[] nums, int k) {
        if(nums.length<=0)
            throw new RuntimeException("nums是空的!");
        //创建双端队列
        Deque<Integer> deque = new LinkedList<>();
        //创建一个结果的ArrayList
        ArrayList<Integer> resut_array = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            //如果双端队列不为空，而且到了窗口长度
            if(!deque.isEmpty() && deque.peek() == i-k)
                //移除第一个元素
                deque.pollFirst();
            //保证nums【双端队列中的索引】是一个递减数列
            while(!deque.isEmpty() && nums[deque.getLast()] < nums[i])
                deque.pollLast();

            //把当前元素的索引加到双端队列中
            deque.offer(i);
            //如果是窗口大小
            if(i >= k-1)
                resut_array.add(nums[deque.peek()]);
        }

        //ArrayList转换成整型数组
        int[] res = resut_array.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }

    /**
     * https://blog.csdn.net/weixin_42069523/article/details/93320335
     * 思路
     * 使用双端队列，在队列的头保存滑动窗口的最大值，队列头后保持次大值，依次保存比较大的值，
     * 使用for循环遍历，当前进入窗口的值与队列中的值比较，大于最大，删除队列所有的值，小于加入末尾，
     * 还要判断队列的头是否有效（在窗口中即为有效，滑出为无效，移除），每次循环都把队列的头加入到存放最大值的列表中。
     *
     * 使用while循环，可以从队列的尾部开始与当前的元素进行比较，安防在合适的位置。处理完后判断队列头是否过期，把头元素保存到结果中。
     */
    public ArrayList<Integer> maxSlidingWindow24(int [] num, int size){
        ArrayList<Integer> res = new ArrayList<>();
        if(num.length < size ||size < 1 ||num == null){
            return res;
        }
        LinkedList<Integer> indexDeque = new LinkedList<>();
        for (int i = 0; i < size - 1; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }

        for (int i = size - 1; i < num.length; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            res.add(num[indexDeque.getFirst()]);
        }
        return res;
    }


    /**
     * 方法
     * https://blog.csdn.net/qq_36903261/article/details/108908081
     *
     */
    public int[] maxSlidingWindow25(int[] nums, int k) {
        //单调队列
        //下面是要注意的点：
        //队列按从大到小放入
        //如果首位值（即最大值）不在窗口区间，删除首位
        //如果新增的值小于队列尾部值，加到队列尾部
        //如果新增值大于队列尾部值，删除队列中比新增值小的值，如果在把新增值加入到队列中
        //如果新增值大于队列中所有值，删除所有，然后把新增值放到队列首位，保证队列一直是从大到小
        if (nums.length == 0)   return nums;

        Deque<Integer> deque = new LinkedList<>();
        int[] arr = new int[nums.length - k + 1];
        int index = 0;  //arr数组的下标
        //未形成窗口区间
        for (int i = 0; i < k; i++) {
            //队列不为空时，当前值与队列尾部值比较，如果大于，删除队列尾部值
            //一直循环删除到队列中的值都大于当前值，或者删到队列为空
            while (!deque.isEmpty() && nums[i] > deque.peekLast())  deque.removeLast();
            //执行完上面的循环后，队列中要么为空，要么值都比当前值大，然后就把当前值添加到队列中
            deque.addLast(nums[i]);
        }
        //窗口区间刚形成后，把队列首位值添加到队列中
        //因为窗口形成后，就需要把队列首位添加到数组中，而下面的循环是直接跳过这一步的，所以需要我们直接添加
        arr[index++] = deque.peekFirst();
        //窗口区间形成
        for (int i = k; i < nums.length; i++) {
            //i-k是已经在区间外了，如果首位等于nums[i-k]，那么说明此时首位值已经不再区间内了，需要删除
            if (deque.peekFirst() == nums[i - k])   deque.removeFirst();
            //删除队列中比当前值大的值
            while (!deque.isEmpty() && nums[i] > deque.peekLast())  deque.removeLast();
            //把当前值添加到队列中
            deque.addLast(nums[i]);
            //把队列的首位值添加到arr数组中
            arr[index++] = deque.peekFirst();
        }
        return arr;
    }


    /**
     * https://blog.csdn.net/lzhcoder/article/details/114381670
     *
     * 解题思路
     * 为什么要用单调队列
     * 对于每个滑动窗口，我们可以使用 O(k)的时间遍历其中的每一个元素，找出其中的最大值。对于长度为 n 的数组nums 而言，窗口的数量为 n-k+1，
     * 因此该算法的时间复杂度为O((n−k+1)k)=O(nk)，会超出时间限制，因此我们需要进行一些优化。
     *
     * 引入一个单调递减的队列（左边对头数据最大，右边队尾数据最小）的目的是为了存储每次滑动窗口中最大值。
     *
     * 为什么用单调队列而不是单调栈
     * 因为我们需要保持队列的两端都可以访问和修改。当新元素加入时，我们需要从队列后端移除所有比新元素小的元素，以保持队列的单调递减；同时，当窗口移动时，我们可能需要从队列前端移除旧的元素（即不再在当前窗口内的元素）。
     *
     *
     *
     * 详细的思路
     * 1.想将我们第一个窗口的k个值存入单调双端队列中，单调队列里面的值为单调递减的。如果发现队尾元素小于等于要加入的元素，则将队尾元素出队，直到队尾元素大于新元素时，再让新元素入队，目的就是维护一个单调递减的队列。
     * 2.我们将第一个窗口的所有值，按照单调队列的规则入队之后，因为队列为单调递减，所以队头元素必为当前窗口的最大值，则将队头元素添加到数组中。
     * 3.然后把剩余的nums.length()-k个数按照规则进行入队，维护单调递减队列，同时剔除已经不在滑动窗口里的数据。
     * 4.每次将队头元素存到返回数组里。
     * 5.返回数组
     *
     */
    public static int[] maxSlidingWindow26(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return nums;
        }
        int[] arr = new int[len - k + 1];
        int arr_index = 0;
        // 我们需要维护一个单调递增的双向队列
        Deque<Integer> deque = new LinkedList<>();
        // 先将第一个窗口的值按照规则入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(nums[i]);
        }
        // 存到数组里，队头元素
        arr[arr_index++] = deque.peekFirst();
        // 移动窗口
        for (int j = k; j < len; j++) {

            //窗口的前一个(即不在窗口里的元素)元素等于队头元素
            if (nums[j - k] == deque.peekFirst()) {
                deque.removeFirst();
            }


            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.offerLast(nums[j]);
            arr[arr_index++] = deque.peekFirst();
        }
        return arr;
    }


    /**
     * 方法三 暴力求解
     * 通过遍历所有的滑动窗口，找到每一个窗口的最大值，来进行暴力求解。那一共有L-k+1 个滑动窗口;
     *
     * https://blog.csdn.net/weixin_40446252/article/details/104285242
     */
    public int[] maxSlidingWindow31(int[] nums, int k) {
       int len = nums.length;
       if (len * k == 0) return new int[0];
       int [] win = new int[len - k + 1];
       //遍历所有的滑动窗口
       for (int i = 0; i < len - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            //找到每一个滑动窗口的最大值
            for(int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            win[i] = max;
       }
       return win;
    }

    /**
     * 方法
     *  暴力求解
     *  https://blog.csdn.net/qq_36903261/article/details/108908081
     */
    public int[] maxSlidingWindow32(int[] nums, int k) {
        if (nums.length == 0)   return nums;

        int[] arr = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i <= nums.length - k; i++) {
            int max = Integer.MIN_VALUE;
            // 找出区间的最大值
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max)  max = nums[j];
            }
            arr[index++] = max;
        }
        return arr;
    }

    /**
     * 方法
     * 思路：属于暴力破解方法的优化版本
     * https://blog.csdn.net/qq_36903261/article/details/108908081
     */
    public int[] maxSlidingWindow33(int[] nums, int k) {
        if (nums.length == 0)   return nums;

        int[] arr = new int[nums.length - k + 1];
        int index = 0;
        int max = Integer.MIN_VALUE, maxIndex = -1;
        for (int i = 0; i <= nums.length - k; i++) {
            //判断上一个max是否还在区间内，并且是否大于新的值
            if (maxIndex >= i && maxIndex < i+k && max > nums[i+k - 1]) {
                arr[index++] = max;
                continue;
            }
            //上面判断不成功，重新找出区间的最大值
            max = Integer.MIN_VALUE;
            maxIndex = -1;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                    maxIndex = j;
                }
            }
            arr[index++] = max;
        }
        return arr;
    }


    public static void main(String[] args) {

        testList();

        int[] num = {2,3,5,2,6,2,4,1};
        int size = 3;

        ArrayList<Integer> maxList = maxSlidingWindow11(num, size);
        System.out.println(maxList.toString());

        int[] maxArray21 = maxSlidingWindow21(num, size);
        System.out.println(maxArray21);

        int[] maxArray22 = maxSlidingWindow22(num, size);
        System.out.println(maxArray22);

    }

    public static void testList() {

        ArrayList<Integer> init = Lists.newArrayList(2, 3, 5, 2, 6, 2, 4, 1);
        System.out.println(init);


        // LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>(init);
        System.out.println(linkedList);
        System.out.println(linkedList.peek());
        System.out.println(linkedList.peekFirst());
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());

        linkedList.add(1);// 返回为void   ## 加到尾部了
        System.out.println(linkedList);
        linkedList.addFirst(1);// 返回为void  ## 加到头部了
        System.out.println(linkedList);
        linkedList.addLast(10);// 返回为void
        System.out.println(linkedList);

        linkedList.offer(2);// 返回为boolean   ## 加到尾部了
        System.out.println(linkedList);
        linkedList.offerFirst(3);// 返回为boolean
        System.out.println(linkedList);
        linkedList.offerLast(11);// 返回为boolean
        System.out.println(linkedList);

        System.out.println(linkedList.poll());// ## 弹出头部
        System.out.println(linkedList.pollFirst());
        System.out.println(linkedList.pollLast());

        System.out.println(linkedList.pop());// ## 弹出尾部
        linkedList.push(12);// ## 加到头部
        System.out.println(linkedList);

        System.out.println(linkedList.remove());// ##  删除头部
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeLast());

        // Deque   (LinkedList继承了Deque)   public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable
        Deque<Integer> deque = new LinkedList<>(init);
        System.out.println(deque);
        System.out.println(deque);
    }

}
