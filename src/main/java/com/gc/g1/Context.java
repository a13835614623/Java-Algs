package com.gc.g1;

import java.util.List;
import java.util.Queue;

public class Context {
    /**
     * 全局卡表
     */
    public static List<CardPage> cardTable;
    /**
     * 脏卡页
     */
    public static Queue<CardPage> dirtyCardQueue;

    /**
     * 堆的大小,单位 B
     */
    public static long heapSize;
    /**
     * 堆的起始内存地址
     */
    public static long heapStartAddress;

}
