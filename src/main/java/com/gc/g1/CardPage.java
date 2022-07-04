package com.gc.g1;

import java.util.List;

/**
 * @description 卡页
 * 每个CardPage会对应堆中的512B空间
 * @author 张子宽
 * @date 2022/06/22
 */
public class CardPage {
    /**
     * 在全局卡表中的索引
     */
    int index;
    /**
     * 此卡页中的对象
     */
    List<G1Object> objects;
    /**
     * 此卡页所处的region
     */
    Region region;
}
