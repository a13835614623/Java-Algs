package com.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class MyLock extends AbstractOwnableSynchronizer implements Lock {
    /**
     * 0 无线程使用
     * 1 线程独占
     * 1以上 当前线程重入
     */
    private volatile int state = 0;

    private static Unsafe UNSAFE;

    private Thread exclusiveThread;

    private Node head;

    private Node tail;

    public static long STATE;
    public static long HEAD;
    public static long TAIL;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            STATE = UNSAFE.objectFieldOffset
                    (MyLock.class.getDeclaredField("state"));
            HEAD = UNSAFE.objectFieldOffset
                    (MyLock.class.getDeclaredField("head"));
            TAIL = UNSAFE.objectFieldOffset
                    (MyLock.class.getDeclaredField("tail"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static class Node {

        volatile Thread thread;

        volatile Node next;

        volatile Node prev;

        public Node() {

        }


        public Node(Thread thread) {
            this.thread = thread;
        }

    }

    @Override
    public void lock() {
        Thread currentThread = Thread.currentThread();
        // 没有其他线程持有锁
        if (state == 0) {
            if (!hasWaiters() && casState(0, 1)) {
                setExclusiveOwnerThread(currentThread);
                return;
            }
        } else if (getExclusiveOwnerThread() == currentThread) {
            // 如果是当前线程持有锁的话,重入,state+1
            state++;
            return;
        }
        // 其他线程持有锁
        Node node = new Node(currentThread);
        // 加到等待队列
        addWaiter(node);
        LockSupport.park(currentThread);
    }

    private boolean hasWaiters() {
        Node t = tail; // Read fields in reverse initialization order
        Node h = head;
        if (t == h) {
            return false;
        }
        Node next = h.next;
        // next==null 说明有线程正在进行追加waiter,
        return next == null || next.thread != Thread.currentThread();
    }

    @Override
    public void unlock() {
        if (getExclusiveOwnerThread() != Thread.currentThread()) {
            throw new UnsupportedOperationException();
        }
        // 只加锁了一次
        if (state == 1) {
            setExclusiveOwnerThread(null);
            state = 0;
            // 第一个等待的线程
            Node firstWaiter = head;
            if (firstWaiter != null) {
                Thread thread = firstWaiter.thread;
                if (thread == null) {
                    firstWaiter = firstWaiter.next;
                    thread = firstWaiter.thread;
                }
                if (thread != null) {
                    setExclusiveOwnerThread(thread);
                    LockSupport.unpark(thread);
                }
            }
        } else {
            // 重入状态-1
            state--;
        }
    }

    private void addWaiter(Node waiter) {
        while (tail == null) {
            // 如果tail为null,代表还没有初始化,此时将head和tail都初始化为一个空节点
            if (casHead(null, new Node())) {
                tail = head;
                break;
            }
        }
        Node oldTail = tail;
        while (true) {
            waiter.prev = oldTail;
            if (casTail(oldTail, waiter)) {
                oldTail.next = waiter;
                return;
            }
        }
    }

    public boolean casHead(Object expect, Object update) {
        return UNSAFE.compareAndSwapObject(this, HEAD, expect, update);
    }

    public boolean casTail(Object expect, Object update) {
        return UNSAFE.compareAndSwapObject(this, TAIL, expect, update);
    }

    public boolean casRef(long offset, Object expect, Object update) {
        return UNSAFE.compareAndSwapObject(this, offset, expect, update);
    }

    public boolean casState(long expect, long update) {
        return UNSAFE.compareAndSwapLong(this, STATE, expect, update);
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
    public Condition newCondition() {
        return null;
    }
}
