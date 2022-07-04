package com.gc.g1;

import java.util.BitSet;
import java.util.Map;
import java.util.Set;
/**
 * @description RSet
 * 核心是得到其他region种哪些卡页引用了当前region
 * @author 张子宽
 * @date 2022/06/22
 */
public class RSet {
    /**
     * key 是引用当前region的其他region
     * value 是引用当前region的其他region里的具体的卡页在全局卡表中的索引
     * region B-> [1]
     * region D-> [122]
     * region C -> [2544,2545,2546]
     */
    private Map<Region, Set<Integer>> otherRegionCardTableIndex;
    /**
     * !!!!引用Region数超过4个时,启用此结构!!!!!!!
     * key 是引用当前region的其他region
     * value 是引用当前region的其他region的具体的卡页在全局卡表中的引用情况,如果位图中是1，则代表引用了
     * 例如：
     * region B-> bitmap （01000…）
     * region D-> bitmap (000…1000…)
     * region C -> bitmap (000…111000…)
     */
    private Map<Region, BitMap> regionBitMap;
    /**
     * 粗粒度，只是记录了哪些region引用了当前region
     */
    private BitMap heapBitMap;

    public Map<Region, Set<Integer>> getOtherRegionCardTableIndex() {
        return otherRegionCardTableIndex;
    }

    public RSet setOtherRegionCardTableIndex(Map<Region, Set<Integer>> otherRegionCardTableIndex) {
        this.otherRegionCardTableIndex = otherRegionCardTableIndex;
        return this;
    }

    public Map<Region, BitMap> getRegionBitMap() {
        return regionBitMap;
    }

    public RSet setRegionBitMap(Map<Region, BitMap> regionBitMap) {
        this.regionBitMap = regionBitMap;
        return this;
    }

    public BitMap getHeapBitMap() {
        return heapBitMap;
    }

    public RSet setHeapBitMap(BitMap heapBitMap) {
        this.heapBitMap = heapBitMap;
        return this;
    }
}
