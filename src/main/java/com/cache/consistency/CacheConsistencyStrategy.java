package com.cache.consistency;

import java.util.concurrent.TimeUnit;

/**
 * 缓存一致性策略
 * @author 张子宽
 * @date 2022/07/05
 */
public class CacheConsistencyStrategy {

    /**
     * 先删除缓存 再更新数据库
     * 问题：缓存删除成功,数据库还没更新完成,别的线程此时从数据库读取到旧数据,导致缓存更新为旧值
     * 解决：延迟双删
     */
    public void strategy1(){
        deleteCache();
        // 此时其他线程读数据库会获取到旧的值,导致缓存更新到旧值
        updateDb();
    }

    /**
     * 延时双删
     * 为了避免更新数据库的时候，其他线程从缓存中读取不到数据，就在更新完数据库之后，再sleep一段时间，然后再次删除缓存。
     */
    public void delayTwoDelete(){
        deleteCache();
        // 此时其他线程读数据库会获取到旧的值,导致缓存更新到旧值
        updateDb();
        // 休眠一会儿,再删除缓存,防止换成一直不准确
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {

        }
        deleteCache();
    }

    /**
     * 先更新数据库 再删除缓存
     * 问题：数据库更新成功,缓存还没删或者删除失败,此时别的线程进行读取,还是读取到了旧值
     * 解决方案：
     * 1）消息队列：更新数据库后，发消息到消息队列，利用消息队列的重试机制保证删除成功
     * 2）进阶消息队列：通过订阅binlog日志来是更新缓存，延迟相对较高
     */
    public void strategy2(){
        // 缓存:旧值 数据库:旧值
        updateDb();
        // 数据库:新值 缓存:旧值
        // 此时其他线程会从缓存读取到旧值
        deleteCache();
    }

    /**
     * 先更新缓存 再更新数据库
     * 问题：
     * 如果数据库1小时内更新了1000次，那么缓存也要更新1000次，但是这个缓存可能在1小时内只被读取了1次，那么这1000次的更新有必要吗？
     * 反过来，如果是删除的话，就算数据库更新了1000次，那么也只是做了1次缓存删除，只有当缓存真正被读取的时候才去数据库加载。
     */
    public void strategy3(){
        // 缓存:旧值 数据库:旧值
        updateCache();
        // 缓存:新值 数据库:旧值
        updateDb();
    }
    /**
     * 先更新数据库 再更新缓存
     * 问题：更新数据库成功,还没更新缓存,此时其他线程会读取到旧值
     */
    public void strategy4(){
        // 缓存:旧值 数据库:旧值
        updateDb();
        // 数据库:新值 缓存:旧值
        // 此时其他线程读取依然是旧值,依然数据不一致
        updateCache();
    }


    private void updateDb() {

    }

    private void deleteCache() {

    }

    private void updateCache() {

    }
}
