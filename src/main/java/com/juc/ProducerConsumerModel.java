package com.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者消费者模型
 * @author 张子宽
 * @date 2022/07/07
 */
public class ProducerConsumerModel {


    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);
        new Thread(() -> {
            try {
                blockingQueue.put("1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "生产者").start();

        new Thread(() -> {
            try {
                String take = blockingQueue.take();
                // 消费数据
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "消费者").start();
    }

}
