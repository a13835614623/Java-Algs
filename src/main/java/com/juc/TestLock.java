package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    private static final Lock lock = new MyLock();
    private static final Lock loc2k = new ReentrantLock();

    public static int val = 0;

    public static void main(String[] args) throws InterruptedException {
//         test();
//        lock.lock();
//        try {
//            for (int i = 0; i < 1000000; i++) {
//                val++;
//            }
//        } finally {
//            lock.unlock();
//        }
    }

    private static void test() throws InterruptedException {
        Thread add = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 1000000; i++) {
                    val++;
                }
            } finally {
                lock.unlock();
            }
        }, "add");
        Thread reduce = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 1000000; i++) {
                    val--;
                }
            } finally {
                lock.unlock();
            }
        }, "reduce");
        add.start();
        reduce.start();
        add.join();
        reduce.join();
        System.out.println(val);
    }
}
