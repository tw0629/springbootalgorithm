package com.tian.algorithm.base_op.cache_lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/8 12:42
 */
public class LRUCache3 {

    private int cap;
    private Map<Integer, Integer> map = new LinkedHashMap<>();  // 保持插入顺序

    public LRUCache3(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (map.keySet().contains(key)) {
            int value = map.get(key);
            map.remove(key);
            // 保证每次查询后，都在末尾
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.keySet().contains(key)) {
            map.remove(key);
        } else if (map.size() == cap) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            iterator.next();
            iterator.remove();

            // int firstKey = map.e***ySet().iterator().next().getValue();
            // map.remove(firstKey);
        }
        map.put(key, value);
    }
}
