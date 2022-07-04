package com.gc.g1;

import java.util.List;

/**
 * @description Region
 * G1中每个Region大小是固定相等的，Region的大小可以通过参数-XX:G1HeapRegionSize设定，
 * 取值范围从1M到32M，且是2的指数。如果不设定，那么G1会根据Heap大小自动决定。
 * @author 张子宽
 * @date 2022/06/22
 */
public class Region {
    /**
     * 大小
     */
    int size;
    /**
     * G1的RSet是在Card Table的基础上实现的：
     * 每个Region会记录下别的Region有指向自己的指针，
     * 并标记这些指针分别在哪些Card的范围内。
     * 这个RSet其实是一个Hash Table，Key是别的Region的起始地址，Value是一个集合，
     * 里面的元素是Card Table的Index。每个Region都有一个对应的Rset。
     */
    RSet rSet;
    /**
     *
     * |<-- (1) -->|<-- (2) -->|<-- (3) -->|<-- (4) -->|
     * bottom   prevTAMS    nextTAMS      top        end
     *
     * top是该region的当前分配指针，
     * [bottom, top)是当前该region已用（used）的部分，
     * [top, end)是尚未使用的可分配空间（unused）。
     *
     * (1) [bottom, prevTAMS): 这部分里的对象存活信息可以通过prevBitmap来得知
     * (2) [prevTAMS, nextTAMS): 这部分里的对象在第n-1轮concurrent marking是隐式存活的
     * (3) [nextTAMS, top): 这部分里的对象在第n轮concurrent marking是隐式存活的
     */
    long bottom;
    long prevTAMS;
    long nextTAMS;
    long top;
    long end;
    /**
     * 记录 前一轮 concurrent mark的对象存活信息
     * 由于 前一轮 concurrent mark已经完成，这个bitmap的信息可以直接使用。
     */
    BitMap prevBitMap;
    /**
     * 记录当前正在进行的 concurrent mark的对象存活信息
     * 这个bitmap是当前将要或正在进行的concurrent mark的结果，尚未完成，所以还不能使用。
     */
    BitMap nextBitMap;
    /**
     * 类型
     */
    RegionType type;
    /**
     * 本region里的所有对象
     */
    List<G1Object> objects;

    /**
     * 对象活性 = 存活对象数量/总对象数量
     * 默认低于35%即垃圾对象数量占比超过65%才会被回收
     */
    double getObjectActiveRatio(){
        int total = objects.size();
        int active = (int) objects.stream().filter(obj->obj.active).count();
        return active/total;
    }


    /**
     * 释放内存
     */
    public void free(){

    }
}
