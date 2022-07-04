package com.gc.zgc;
/**
 * @description 读屏障
 * @author 张子宽
 * @date 2022/06/19
 */
public interface ILoad {

    /**
     * @description 读取对象地址
     * @param address 地址
     * @return java.lang.Object
     * @author 张子宽
     * @date 2022/06/19
     */
    Object getFromHeap(String address);
}
