package com.gc.g1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description G1CollectorImpl
 * @author 张子宽
 * @date 2022/06/22
 */
public class G1CollectorImpl implements G1Collector {
    /**
     * 空闲的region列表,可以被用来分配对象
     */
    private List<Region> freeRegionList;
    /**
     * 扫描栈
     */
    private Stack<G1Object> markingStack;
    /**
     * 卡页的bitmap
     */
    private BitMap cardBitMap;
    /**
     * collectionSet，将要被复制的region
     */
    private HashSet<Region> collectionSet;

    /**
     * 以SATB write barrier为例，
     * 每个Java线程有一个独立的、定长的SATBMarkQueue，
     * mutator在barrier里只把old_value压入该队列中。
     * 一个队列满了之后，它就会被加到全局的SATB队列集合SATBMarkQueueSet里等待处理，
     * 然后给对应的Java线程换一个新的、干净的队列继续执行下去。
     *
     * 并发标记（concurrent marker）会定期检查全局SATB队列集合的大小。
     * 当全局集合中队列数量超过一定阈值后，concurrent marker就会处理集合里的所有队列：
     * 把队列里记录的每个oop都标记上，并将其引用字段压到标记栈（marking stack）
     * 上等后面做进一步标记。
     */
    private Queue<G1Object> SATBMarkQueue;


    @Override
    public void globalConcurrentMark() {
        initMark();
        rootRegionScanning();
        concurrentMarking();
        remark();
        cleanUp();
    }

    /**
     * 当年老代内存占比超过 -XX:InitiatingHeapOccupancyPercent指定的阈值时会触发并发标记，
     * 并发标记的第一个阶段为Initial Mark，该阶段会STW，其只扫描GCRoot直接引用的对象，
     * 由于Young GC时也要扫描GCRoot直接引用的对象，Young GC时会顺便完成Initial Mark的工作。
     * GC日志通常会有GC pause (G1 Evacuation Pause) (young) (initial-mark) 的关键信息，
     * 其中的initial-mark 代表在进行Young GC时顺便完成的初始化标记工作。
     */
    private void initMark() {
        markingStack.addAll(listGcRootDirectReferences());
    }

    /**
     * 实际扫描的是新生代Survivor Region引用的对象，该阶段必须在下次GC暂停前完成，
     * 因为Heap要扫描存活对象的话，Survivor Region引用的对象必须先被识别。
     */
    private void rootRegionScanning() {
        List<Region> regions = listRegion(RegionType.SURVIVOR);
        for (Region region : regions) {
            markingStack.addAll(region.objects);
        }
    }

    /**
     * 不需要STW。不断从扫描栈取出引用递归扫描整个堆里的对象。
     * 每扫描到一个对象就会对其标记，并将其字段压入扫描栈。
     * 重复扫描过程直到扫描栈清空。
     * 过程中还会扫描SATB write barrier所记录下的引用。
     * Concurrent Marking 可以被YGC中断
     */
    private void concurrentMarking() {
        // 递归扫描整个堆里的对象
        while (!markingStack.empty()) {
            G1Object obj = markingStack.pop();
            List<G1Object> G1Objects = listReference(obj);
            markingStack.addAll(G1Objects);
            // 标记扫描到的对象
            for (G1Object markObj : G1Objects) {
                mark(markObj);
            }
        }
        // 标记SATB中的对象
        while (!SATBMarkQueue.isEmpty()) {
            mark(SATBMarkQueue.poll());
        }
    }

    /**
     * STW操作。在完成并发标记后，每个Java线程还会有一些剩下的SATB write barrier
     * 记录的引用尚未处理。这个阶段就负责把剩下的引用处理完。
     * 同时这个阶段也进行弱引用处理（reference processing）。
     */
    private void remark() {
        // 标记剩余SATB中的对象
        while (!SATBMarkQueue.isEmpty()) {
            mark(SATBMarkQueue.poll());
        }
        // 处理软,弱,虚引用
        //......
    }
    /**
     * 1）STW操作，清点出有存活对象的Region和没有存活对象的Region(Empty Region)
     * 2) STW操作，更新Rset
     * 3) Concurrent操作，把Empty Region收集起来到可分配Region队列。
     */
    private void cleanUp() {
        List<Region> allRegions = Arrays
                .stream(RegionType.values())
                .map(this::listRegion)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<Region> freeRegions = new ArrayList<>();
        for (Region region : allRegions) {
            boolean hasActive = region.objects.stream().anyMatch(obj -> !obj.active);
            // 此region没有活跃对象,全是垃圾,直接加入空闲region列表,后续直接清空
            if (!hasActive){
                freeRegions.add(region);
            }
        }
        // 按对象活性排序,对象活性越低越优先回收
        allRegions.sort((o1, o2) -> (int) (o1.getObjectActiveRatio()-o2.getObjectActiveRatio()));
        // 清理freeRegions
        for (Region freeRegion : freeRegions) {
            freeRegion.free();
            freeRegionList.add(freeRegion);
        }
    }

    /**
     * 复制存活对象
     */
    @Override
    public void evacuation() {

    }

    private void mark(G1Object markObj) {

    }

    private List<G1Object> listReference(G1Object markObj) {
        return new ArrayList<>();
    }

    @Override
    public void youngGc() {
        initMark();
        // 获取所有年轻代的region
        List<Region> regions = listRegion(RegionType.EDEN,RegionType.SURVIVOR);
        // 扫描gcRoots加入根集合
        Deque<G1Object> roots = new ArrayDeque<>(listGcRootDirectReferences());
        // 添加RSet中的跨代引用的对象,也加入到根集合
        for (Region region : regions) {
            RSet rSet = region.rSet;
            // 其他region的引用
            Map<Region, Set<Integer>> otherRegionCardTableIndex = rSet.getOtherRegionCardTableIndex();
            otherRegionCardTableIndex.forEach((otherRegion,cardTableIndexSet)->{
                for (Integer cardTableIndex : cardTableIndexSet) {
                    CardPage card = Context.cardTable.get(cardTableIndex);
                    roots.addAll(card.objects);
                }
            });
        }
        // 更新RSet
        Queue<CardPage> dirtyCardQueue = getDirtyCardQueue();
        for (Region region : regions) {
            RSet rSet = region.rSet;
            Map<Region, Set<Integer>> otherRegionCardTableIndex =
                    rSet.getOtherRegionCardTableIndex();
            for (CardPage dirtyCard : dirtyCardQueue) {
                otherRegionCardTableIndex.get(dirtyCard.region)
                        .add(dirtyCard.index);
            }
        }
        // 处理RSet
        // 识别被老年代对象指向的Eden中的对象,这些被指向的Eden中的对象被认为是存活的对象
        for (G1Object root : roots) {
            mark(root);
        }
        // 复制对象,将存活的对象复制的suvivour区
        copyLiveObj(roots,regions);
        // 处理引用
        handleReference();
    }

    private Queue<CardPage> getDirtyCardQueue() {
        return Context.dirtyCardQueue;
    }

    /**
     * 此阶段,对象树被遍历,Eden区 内存段中存活的对象会被复制到Survivor区中空的内存分段,
     * Survivor区内存段中存活的对象如果年龄未达阈值,年龄会加1,
     * 达到阀值会被会被复制到old区中空的内存分段。
     * 如果Survivor空间不够,Eden空间的部分数据会直接晋升到老年代空间
     */
    private void copyLiveObj(Deque<G1Object> roots, List<Region> regions) {
        List<G1Object> allLiveObjs = new ArrayList<>(roots);
        // 遍历对象树,获取到所有存活对象
        while (!roots.isEmpty()) {
            G1Object obj = roots.pop();
            List<G1Object> objects = listReference(obj);
            roots.addAll(objects);
            allLiveObjs.addAll(objects);
        }
        // 处理年龄等东西
        // 复制到survivor,清理原region
        copyToSurvivor(allLiveObjs);
    }

    private void copyToSurvivor(List<G1Object> allLiveObjs) {

    }

    /**
     * 处理Soft,Weak, Phantom, Final, JNI Weak等引用。
     * 最终Eden空间的数据为空,GC停止工作,而目标内存中的对象都是连续存储的,没有碎片,所以复制过程可以达到内存整理的效果,减少碎片
     */
    private void handleReference() {

    }

    private List<G1Object> listGcRootDirectReferences() {
        return new ArrayList<>();
    }

    /**
     * @description 获取所有年轻代的region
     * @return java.util.List<com.gc.g1.Region>
     * @author 张子宽
     * @date 2022/06/22
     */
    private List<Region> listRegion(RegionType... regionType) {
        return new ArrayList<>();
    }

    @Override
    public void mixedGc() {
        globalConcurrentMark();
        evacuation();
    }
}


