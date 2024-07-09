package com.tian.algorithm.base_op.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LFU算法： 1 TreeSet （哈希表 + 平衡二叉树）
 *          2 PriorityQueue （哈希表 + 优先队列）
 *          3 List：若用list,得每次遍历得到最小的node
 *          4 HashMap （ 双哈希表 即两个HashMap）
 *              (一个用来存放node的key-value,一个用来存放 频率-相同频率node的List)
 */

/**
 *
 * 方法一：双哈希表（两个HashMap）
 *
 * @author David Tian
 *
 * 最近最不经常使用的元素即为count最小；
 * 即：只比较 使用频率；
 *
 * https://leetcode-cn.com/problems/lfu-cache/solution/lfuhuan-cun-by-leetcode-solution/
 *
 * @since 2021/8/7 16:18
 */
public class LFUCache {

    public class Node {
        public int key;
        public int val;
        public int freq;

        public Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    /**
     * key_table:   key:node的key  value:node的value
     * freq_table:  key:使用频率    value:该频率下元素的List
     *
     *
     * !!!!minfreq：之所以要维护minfreq，是当map满了之后，要从minfreq的list删除一个node
     * minfreq: 若一直有新的元素添加进来，minfreq几乎永远为1;
     *          没有元素添加进来，freq_table没删除掉一个list，minfreq都会加1;
     * minfreq要用来方位freq; 只要访问,freq是一直是+1的。
     *
     *
     * ！！！
     * 注意：在freq_table中的LinkedList<Node>, 插入都是头部, 删除都是尾部
     *
     * list.offerFirst
     * list.peekFirst
     * list.pollLast
     * list.peekLast
     *
     */
    public Map<Integer, Node> key_table;
    public Map<Integer, LinkedList<Node>> freq_table;
    public int minfreq;
    public int capacity;


    public LFUCache(int capacity) {
        this.minfreq = 0;
        this.capacity = capacity;
        key_table = new HashMap<Integer, Node>();;
        freq_table = new HashMap<Integer, LinkedList<Node>>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!key_table.containsKey(key)) {
            return -1;
        }
        Node node = key_table.get(key);
        int val = node.val;
        int freq = node.freq;

        freq_table.get(freq).remove(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freq_table.get(freq).size() == 0) {
            freq_table.remove(freq);
            if (minfreq == freq) {
                minfreq += 1;
            }
        }
        // 插入到 freq + 1 中
        LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<Node>());
        list.offerFirst(new Node(key, val, freq + 1));
        freq_table.put(freq + 1, list);
        key_table.put(key, freq_table.get(freq + 1).peekFirst());

        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!key_table.containsKey(key)) {
            // 缓存已满，需要进行删除操作
            if (key_table.size() == capacity) {
                // 通过 minFreq 拿到 freq_table[minFreq] 链表的末尾节点
                Node node = freq_table.get(minfreq).peekLast();
                key_table.remove(node.key);
                freq_table.get(minfreq).pollLast();
                if (freq_table.get(minfreq).size() == 0) {
                    freq_table.remove(minfreq);
                }
            }
            LinkedList<Node> list = freq_table.getOrDefault(1, new LinkedList<Node>());
            list.offerFirst(new Node(key, value, 1));
            freq_table.put(1, list);
            key_table.put(key, freq_table.get(1).peekFirst());
            minfreq = 1;
        } else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node node = key_table.get(key);
            int freq = node.freq;
            freq_table.get(freq).remove(node);
            if (freq_table.get(freq).size() == 0) {
                freq_table.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            LinkedList<Node> list = freq_table.getOrDefault(freq + 1, new LinkedList<Node>());
            list.offerFirst(new Node(key, value, freq + 1));
            freq_table.put(freq + 1, list);
            key_table.put(key, freq_table.get(freq + 1).peekFirst());
        }
    }

}
