package com.gc.g1;
/**
 * @description RegionType
 * @author 张子宽
 * @date 2022/06/22
 */
public enum RegionType {

    /**
     * 新生代-新分配
     */
    EDEN,
    /**
     * 新生代-幸存者区
     */
    SURVIVOR,
    /**
     * 老年代
     */
    OLD,
    /**
     * 巨形对象
     * 超过Region大小的一半的对象，会直接进入老年代由多个连续的Region存储。
     */
    HUMONGOUS
}
