package com.tian.algorithm.base_op.cache_lru;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Tian
 * @desc https://blog.csdn.net/sunnnnh/article/details/116523048
 *
 * 麻烦不好理解
 *
 * @since 2021/8/7 15:26
 */
public class LRUCache2 {

    // 节点结构
    static class Node{
        int key,val;
        Node pre,next;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();
    // 头
    private Node head = new Node(-1,-1);
    // 尾
    private Node tail = new Node(-1,-1);
    private int k;

    /**
     *  一、设计LRU缓存结构。 k表示缓存结构的容量
     *
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // write code here
        this.k = k;
        // 1. 初始化缓存结构（指头头和尾）
        head.next = tail;
        tail.pre = head;

        /**
         * 终于看懂了：
         * 初始化：int[][] operators
         *      key:    operators[i][1]
         *      value:  operators[i][2]
         *      operators[i][j]   1表示set(x, y); 2表示get(x)
         *
         */

        // 2. 获取get操作的次数(即第一列数字为2)
        int len = (int)Arrays.stream(operators).filter(x->x[0] == 2).count();
        //结果集合
        int[] res = new int[len];
        // 3. 遍历数组operators，执行相应的操作
        for(int i=0,j=0;i<operators.length;i++){
            //set操作
            if(operators[i][0]==1){
                set(operators[i][1],operators[i][2]);
            }else{
                // get操作
                res[j++] = get(operators[i][1]);
            }
        }
        return res;
    }

    /**
     * 二、get操作
     */
    public int get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            // 1. 先将节点从当前位置移除
            node.pre.next = node.next;
            node.next.pre = node.pre;
            // 2. 将节点插入头部
            moveToHead(node);
            // 3. 返回获取的值
            return node.val;
        }
        // 4. 不存在
        return -1;
    }

    /**
     * 三、set操作
     */
    public void set(int key, int val){
        // 1. 该键已存在，只需更新其值
        if(get(key)>-1){
            map.get(key).val = val;
        }
        else{
            // 2. 不存在该键
            // 2.1 没有存储空间，移除最后一个
            // 已满，移除最后一个
            if(map.size()== k){
                //最后一个节点的key值
                int nodeK = tail.pre.key;
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
                //从map集合移除
                map.remove(nodeK);
            }
            // 2.2 加入新的元素
            Node newNode = new Node(key,val);
            map.put(key,newNode);
            // 将加入的元素放到第一个位置
            moveToHead(newNode);
        }

    }

    /**
     * 四、将节点移到头部的操作
     */
    public void moveToHead(Node node){
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    public static void main(String args[]) throws Exception {

    }
}
