package com.my;

/**
 * 布隆过滤器简单实现
 * 大概思路：
 * 1.
 *
 * @Author: zzk
 * @Date: 2020-04-27 10:59
 */
public class MyBloomFilter<T> {
    private int size;
    private double errorRate;
    private boolean[] bits;

    /**
     * @param size      初始容量
     * @param errorRate 错误率
     */
    public MyBloomFilter(int size, double errorRate) {
        this.size = size;
        this.errorRate = errorRate;
        bits = new boolean[size*2];
    }

    public void add(T t) {
        int[] indexs = getIndexArray(t);
        for (int i = 0, len = indexs.length; i < len; i++) {
            bits[indexs[i]]=true;
        }
    }

    public boolean exist(T t) {
        int[] indexs = getIndexArray(t);
        for (int i = 0, len = indexs.length; i < len; i++) {
            if (!bits[indexs[i]])return false;
        }
        return true;
    }

    private int[] getIndexArray(T t) {
        int index1 = indexForHash(hash1(t));
        int index2 = indexForHash(hash2(t));
        int index3 = indexForHash(hash3(t));
        return new int[]{index1, index2, index3};
    }

    private int indexForHash(int hash) {
        return Math.abs(hash % size);
    }

    private int hash1(T t) {
        return t.hashCode() * t.hashCode();
    }

    private int hash2(T t) {
        return t.hashCode() * t.hashCode() >> 16;
    }

    private int hash3(T t) {
        return ~t.hashCode() >> 5;
    }

    public static void main(String[] args) {
        MyBloomFilter<String> myBloomFilter = new MyBloomFilter<>(10, 0.1);
        myBloomFilter.add("hhh");
        myBloomFilter.add("bbb");
        System.out.println(myBloomFilter.exist("hhh"));
        System.out.println(myBloomFilter.exist("ccc"));
    }
}
