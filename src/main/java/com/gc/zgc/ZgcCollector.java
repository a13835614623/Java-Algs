package com.gc.zgc;
/**
 * @description ZgcCollector
 * @author 张子宽
 * @date 2022/06/19
 */
public interface ZgcCollector {

    /**
     * 初始化,地址视图初始化为Remapped
     */
    void init();

    /**
     * 初次标记,
     * 将GCRoots直接引用的对象标记为M0视图,代表是活跃对象
     */
    void mark();
    /**
     * 并发标记
     * 1) 递归初始标记的对象引用的对象,标记为M0视图,加到活跃对象列表
     * 2) 对象重定位,把上一次标记的对象的引用使用转发表更新为正确的引用
     */
    void parallelMark();
    /**
     * 再标记
     * 1）由于上一次是并发标记,用户线程也在执行,可能会修改引用,
     * 导致初始标记判断为活跃的对象变为垃圾，也可能产生新的活跃对象
     * 2) 此步骤就是修复步骤1 的问题
     */
    void remark();

    /**
     * 并发转移准备
     * 分析region是否需要转移
     */
    void parallelMoveReady();

    /**
     * 初始转移，
     * 1）转移GcRoots直接引用的对象
     */
    void initMove();
    /**
     * 并发转移
     * GC线程
     * 1) 如果地址视图为M0且在活跃表中,说明未转移,需要转移,转移完的对象地址视图会被更新为Remapped
     * 2) 如果地址视图是Remapped且在活跃表中,说明已转移了,不处理
     * 应用线程
     * 1) 创建新对象,地址视图设置为Remapped
     * 2) 访问对象：
     * if(对象在活跃表中){
     *     if(地址视图为Remapped){
     *         // 说明已转移,使用读屏障中的转发表定位到新的地址访问
     *     }else if(地址视图为M0){
     *         // 说明对象还没转移,转移对象,视图更新为Remapped
     *     }
     * }else{
     *     // 不在活跃表,还用旧的地址
     * }
     */
    void parallelMove();
}
