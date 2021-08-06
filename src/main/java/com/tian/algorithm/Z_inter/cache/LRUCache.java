package com.tian.algorithm.Z_inter.cache;

import java.util.HashMap;

/**
 * @author David Tian
 * @desc  有并发要考虑加锁
 * @since 2021/8/1 21:14
 */
public class LRUCache {

    private HashMap<Integer,LinkedNode> cache = new HashMap();//方便通过key快速定位结点
    private int size;
    private int capacity;
    private LinkedNode head;
    private LinkedNode tail;

    class LinkedNode{
        int key;
        int value;
        LinkedNode pre;
        LinkedNode next;
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 移除节点
     * @param node
     */
    private void removeNode(LinkedNode node) {
        LinkedNode preNode = node.pre;
        LinkedNode nextNode = node.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    /**
     * 添加节点到头部
     * @param node
     */
    private void addNode(LinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    /**
     * 将节点移动到头部
     * @param node
     */
    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public int get(int key) {
        LinkedNode node = cache.get(key);
        if(node != null) {
            moveToHead(node);
        }else{
            return -1;
        }
        return node.value;
    }

    /**
     * 写入数据
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        LinkedNode node = cache.get(key);
        //存在
        if(node != null) {
            node.value = value;//可能更新数据
            moveToHead(node);
        }
        //不存在
        else{
            LinkedNode newNode = new LinkedNode();
            newNode.key = key;
            newNode.value = value;

            /**
             * 采取先加后删
             */
            cache.put(key,newNode);//更新Map
            addNode(newNode);//添加结点到头部
            size++;//更新结点数
            if(size > capacity) {//如果结点数超过容量大小
                LinkedNode tailPre = tail.pre;
                cache.remove(tailPre.key);//更新Map
                removeNode(tailPre);//删除最后一个结点（尾结点的前一个结点）
                size--;
            }

            /**
             * 采取先删后加  未调试-有bug
             */
            /*if(size == capacity){
                cache.remove(tail.key);

                // 方案一
                //removeNode(tail);

                // 方案二
                LinkedNode prevNode = tail.pre;
                //修改last所指的位置
                if (prevNode != null) {
                    prevNode.next = null;
                    tail = prevNode;
                }

                size--;//更新结点数
            }
            cache.put(key,newNode);//更新Map
            addNode(newNode);//添加结点到头部
            size++;//更新结点数*/

        }
    }

    public static void main(String args[]) throws Exception {

        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);    // 返回 1
        cache.put(3, 3);  // 该操作会使得密钥 2 作废
        cache.get(2);    // 返回 -1 (未找到)
        cache.put(4, 4);  // 该操作会使得密钥 1 作废
        cache.get(1);    // 返回 -1 (未找到)
        cache.get(3);    // 返回 3
        cache.get(4);    // 返回 4
    }
}

