package com.jmm;
/**
 * @description Java 内存模型的操作
 * @author 张子宽
 * @date 2022/06/18
 */
public interface Jmm {

    /**
     * 从主内存读取数据到工作内存
     */
    void read();
    /**
     * 将变量的值载入到工作内存的变量副本
     */
    void load();
    /**
     * 将更改后的变量值传送到主内存
     */
    void store();
    /**
     * 在store之后执行，将store得到的值写入到主内存
     */
    void write();
    /**
     * 将工作变量的副本传递到执行引擎
     */
    void use();
    /**
     * 把一个从执行引擎获取到的值赋值给工作内存的变量
     */
    void assign();
    /**
     * 将变量加锁
     */
    void lock();
    /**
     * 对变量解锁
     */
    void unlock();

}
