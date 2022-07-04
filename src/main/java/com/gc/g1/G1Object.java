package com.gc.g1;

/**
 * @description G1Object
 * @author 张子宽
 * @date 2022/06/22
 */
public class G1Object {
    /**
     * 是否活跃
     */
    boolean active;
    /**
     * 对象的起始地址
     */
    long address;

    /**
     * 返回对象所在的全局卡表的索引
     */
    public int cardTableIndex() {
        return (int) ((address - Context.heapStartAddress) / Const.CARD_PAGE_BYTES);
    }
}
