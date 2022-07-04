package com.gc.g1;

import java.lang.reflect.Field;
import java.util.Queue;

/**
 * @description 用户线程
 * @author 张子宽
 * @date 2022/06/22
 */
public class Mutator {
    /**
     * SATB 队列
     */
    ThreadLocal<Queue<G1Object>> SATBQueue = new ThreadLocal<>();
    /**
     * 脏页
     */
    ThreadLocal<Queue<CardPage>> dirtyCardQueue = new ThreadLocal<>();

    /**
     * 修改引用的时候调用
     * 例如:
     * A obj = new A();
     * obj.a = b;// 执行此代码的时候调用
     */
    public void putField(G1Object target, Field field, G1Object newVal) throws IllegalAccessException {
        // 旧值
        G1Object oldVal = (G1Object) field.get(target);
        // 前置写屏障,将引用修改前的值加入到 当前线程的 STAB 队列
        preWriteBarrier(target, field, oldVal, newVal);
        field.set(target, newVal);
        // 后置写屏障
        postWriteBarrier(target, field, oldVal, newVal);
    }

    /**
     * 修改引用前调用
     */
    private void preWriteBarrier(G1Object target, Field field, G1Object oldVal, G1Object newVal) {
        if (oldVal == null) {
            return;
        }
        SATBQueue.get().add(oldVal);
    }

    /**
     * 在修改引用后调用
     */
    private void postWriteBarrier(G1Object target, Field field, G1Object oldVal, G1Object newVal) {
        if (oldVal == null) {
            return;
        }
        /**
         * 不记录新生代到新生代的引用 或者 新生代到老年代的引用
         * 过滤一个分区内部的引用
         * 过滤空引用
         * 未实现
         */
        dirtyCardQueue.get().add(Context.cardTable.get(target.cardTableIndex()));
    }

}
