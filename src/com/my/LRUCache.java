package com.my;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存实现
 *
 * @Author: zzk
 * @Date: 2020-04-27 10:23
 */
public class LRUCache extends LinkedHashMap<String, String> {
    private int capacity;

    private LRUCache(int initialCapacity) {
        super(initialCapacity, 0.75f, true);
        capacity = initialCapacity;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
        // 数量大于容量的时候移除最久未使用的元素
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        for (int i = 0; i <5; i++) {
            lruCache.put("aaa"+i,"aaa"+i);
        }
        for (int i = 0; i <4; i++) {
            System.out.println(lruCache.get("aaa"+i));;
        }
        lruCache.put("666", "666");
        System.out.println(lruCache);
    }
}
