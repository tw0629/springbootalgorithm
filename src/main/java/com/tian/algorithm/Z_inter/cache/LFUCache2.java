package com.tian.algorithm.Z_inter.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * 方法二：哈希表 + 平衡二叉树
 *
 * @author David Tian
 * @desc  TreeSet + Node implements Comparable<Node>
 *
 *     最近最不经常使用的元素即为count最小,time最小的元素     time???这样对吗？变成最近使用过的了
 *     !!!!! time:表示该缓存创建以来经过的时间  因为time是全局的,只要最近使用过,time就是最大的
 *     即：先比较 使用频率；再比较 time；
 *     即：先看 使用频率最小的，再看 最近使用过的；
 *
 *     https://blog.csdn.net/u014106644/article/details/105338273
 *
 * https://leetcode-cn.com/problems/lfu-cache/solution/lfuhuan-cun-by-leetcode-solution/
 *
 * @since 2021/8/8 19:17
 */
public class LFUCache2 {

    // 缓存容量，时间戳
    public int capacity;
    public int time;
    public Map<Integer, Node> key_table;
    public TreeSet<Node> freq_table;

    public LFUCache2(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        key_table = new HashMap<Integer, Node>();
        freq_table = new TreeSet<Node>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 如果哈希表中没有键 key，返回 -1
        if (!key_table.containsKey(key)) {
            return -1;
        }
        // 从哈希表中得到旧的缓存
        Node node = key_table.get(key);
        // 从平衡二叉树中删除旧的缓存
        freq_table.remove(node);
        // 将旧缓存更新
        node.cnt += 1;
        node.time = ++time;
        // 将新缓存重新放入哈希表和平衡二叉树中
        freq_table.add(node);
        key_table.put(key, node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!key_table.containsKey(key)) {
            // 如果到达缓存容量上限
            if (key_table.size() == capacity) {
                // 从哈希表和平衡二叉树中删除最近最少使用的缓存
                key_table.remove(freq_table.first().key);
                freq_table.remove(freq_table.first());
            }
            // 创建新的缓存
            Node node = new Node(1, ++time, key, value);
            // 将新缓存放入哈希表和平衡二叉树中
            key_table.put(key, node);
            freq_table.add(node);
        } else {
            // 这里和 get() 函数类似
            Node node = key_table.get(key);
            freq_table.remove(node);
            node.cnt += 1;
            node.time = ++time;
            // !!!
            node.value = value;
            freq_table.add(node);
            key_table.put(key, node);
        }
    }
}

class Node implements Comparable<Node> {
    int cnt, time, key, value;

    Node(int cnt, int time, int key, int value) {
        this.cnt = cnt;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Node) {
            Node rhs = (Node) anObject;
            return this.cnt == rhs.cnt && this.time == rhs.time;
        }
        return false;
    }

    @Override
    public int compareTo(Node rhs) {
        /**
         * 最近最不经常使用的元素即为count最小,time最小的元素
         * 先比较 使用频率；再比较 time；
         * https://blog.csdn.net/u014106644/article/details/105338273
         */
        return cnt == rhs.cnt ? time - rhs.time : cnt - rhs.cnt;
    }

    @Override
    public int hashCode() {
        return cnt * 1000000007 + time;
    }
    
}
