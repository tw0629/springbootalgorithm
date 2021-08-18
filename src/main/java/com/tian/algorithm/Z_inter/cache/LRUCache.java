package com.tian.algorithm.Z_inter.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Tian
 * @desc https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 * 这里有图：https://blog.csdn.net/sunnnnh/article/details/116523048
 *
 *
 * 其实就是：双向链表的结构
 * LRUCache 数据结构： head <一> 1 <一> 2 <一> 3 <一> 4 <一> tail
 *
 * 标准写法
 *
 * @since 2021/8/8 11:41
 */
public class LRUCache {

    public class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;
        public LinkedNode() {}
        public LinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, LinkedNode> cache = new HashMap<Integer, LinkedNode>();
    private int size;
    private int capacity;
    private LinkedNode head;
    private LinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
    }


    private void addToHead(LinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private LinkedNode removeTail() {
        LinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }



    public int get(int key) {
        LinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            LinkedNode newNode = new LinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                LinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     *  其实就是：双向链表的结构
     *  LRUCache 数据结构： head <一> 1 <一> 2 <一> 3 <一> 4 <一> tail
     *
     *  1.如果是新节点 newNode = 2
     *  之前： head ↔ 1 ↔ tail
     *  之后： head ↔ 2 ↔ 1 ↔ tail
     *
     *  2.如果是removeNode(node)之后的节点：比如当前操作的节点是 node = 2
     *  之前：head ↔ 1 ↔ 3 ↔ 4 ↔ tail
     *               ↖2↗
     *  之后：head ↔ 2 ↔ 1 ↔ 3 ↔ 4 ↔ tail
     */
    public static void main(String args[]) throws Exception {

        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        LRUCache.LinkedNode head0 = cache.head;
        LRUCache.LinkedNode tail0 = cache.tail;


        cache.put(1, 1);
        cache.put(2, 2);
        //cache.put(3, 3);
        //cache.put(4, 4);

        LRUCache.LinkedNode head = cache.head;
        LRUCache.LinkedNode tail = cache.tail;
        cache.get(1);
        LRUCache.LinkedNode head2 = cache.head;
        LRUCache.LinkedNode tail2 = cache.tail;


        cache.get(3);
        cache.get(4);
    }
}
