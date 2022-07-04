package com.gc.g1;
/**
 * @description G1Collector
 * @author 张子宽
 * @date 2022/06/22
 */
public interface G1Collector {

    /**
     * @description 全局并发标记
     * @author 张子宽
     * @date 2022/06/22
     */
    void globalConcurrentMark();
    /**
     * @description 拷贝存活对象
     * @author 张子宽
     * @date 2022/06/22
     */
    void evacuation();

    /**
     * 选定所有年轻代里的Region。
     * 通过控制年轻代的region个数，即年轻代内存大小，来控制young GC的时间开销。
     */
    void youngGc();
    /**
     * 选定所有年轻代里的Region，
     * 外加根据global concurrent marking统计得出收集收益高的若干老年代Region。
     * 在用户指定的开销目标范围内尽可能选择收益高的老年代Region。
     */
    void mixedGc();

}
