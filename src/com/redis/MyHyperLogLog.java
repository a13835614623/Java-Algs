package com.redis;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * redis HyperLogLog算法
 *
 * @Author: zzk
 * @Date: 2020-03-13 09:38
 */
public class MyHyperLogLog {
    // 随机数的数量
    private int numCount;
    // 桶的数量
    private int bucketCount;
    private Bucket[] buckets;


    public MyHyperLogLog(int numCount,int bucketCount) {
        this.numCount = numCount;
        this.bucketCount=bucketCount;
        buckets=new Bucket[bucketCount];
        for (int i = 0; i <bucketCount; i++) {
            buckets[i]=new Bucket(32);
        }
    }
    /**
     * 将numCount个随机值散列到各个桶中
     */
    public void work() {
        for (int i = 0; i < this.numCount; i++) {
            long m = ThreadLocalRandom.current().nextLong(1L << 32);
            Bucket bucket = buckets[(int) (((m & 0xfff0000) >> 16) % bucketCount)];
            bucket.lowZero(m);
        }
    }

    /**
     * 使用调和平均计算低位连续0的最大数量的平均值
     * @return
     */
    public double caculate(){
        double totalBit = 0.0;
        for (int i=0; i<bucketCount; i++){
            totalBit += 1.0 / (double)this.buckets[i].getMaxZero();
        }
        double averageBit = (double)bucketCount / totalBit;

        return Math.pow(2, averageBit) * bucketCount;
    }
    public static void main(String[] args) {
        for (int i = 100000; i < 1000000; i += 100000) {
            MyHyperLogLog myHyperLogLog =new MyHyperLogLog(i, 1024);
            myHyperLogLog.work();
            System.out.println("实际数量:" + i + ",统计数量：" +  myHyperLogLog.caculate());
        }
    }
    //桶
    class Bucket {
        private int maxZero;
        private int bit;

        public Bucket(int bit) {
            this.bit = bit;
        }

        /**
         * 计算低位连续0的最大数量
         */
        public void lowZero(long value) {
            int i = 1;
            for (; i < bit; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            maxZero = Math.max(maxZero, i - 1);
        }

        public int getMaxZero() {
            return maxZero;
        }
    }
}
