package com.distribution.lock;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.Pathable;
import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;

public class ZookeeperLock implements DistributionLock {
    CuratorFramework curatorFramework;

    private static final String LOCK_PATH = "/lock";
    private static final String LOCK = LOCK_PATH + "/distributionLock";
    private static final byte[] LOCK_DATA = "1".getBytes();

    @Override
    public void lock() {
        try {
            // 创建父节点
            curatorFramework.create().forPath(LOCK_PATH);
            // 创建临时有序节点
            String nodePath = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(LOCK, LOCK_DATA);
            List<String> list = curatorFramework.getChildren().forPath(LOCK_PATH);
            list.sort(String::compareTo);
            // 如果最小的节点就是当前我们创建的节点,代表获取锁成功
            if (list.get(0).equals(nodePath)){
                // 获取锁成功
            }else {
                // 不是最小的节点,监听前一个节点
                String prevNode = null;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(nodePath)) {
                        break;
                    }else {
                        prevNode = list.get(i);
                    }
                }
                // 追加监听,直到前一个节点断开
                AtomicBoolean lockSuccess = new AtomicBoolean(false);
                curatorFramework.watchers().add()
                        .withMode(AddWatchMode.PERSISTENT)
                        .usingWatcher((CuratorWatcher) watchedEvent -> {
                            if (watchedEvent.getType()== Watcher.Event.EventType.NodeDeleted) {
                                // 获取锁成功
                                lockSuccess.set(true);
                            }
                        }).forPath(prevNode);
                while (!lockSuccess.get()){
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
