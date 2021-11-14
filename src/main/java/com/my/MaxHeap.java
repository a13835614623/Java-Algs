package com.my;

import java.util.ArrayList;
import java.util.List;

/**
 * 大顶堆实现
 *
 * @Author: zzk
 * @Date: 2020-04-20 22:14
 */
public class MaxHeap<T extends Comparable> {
    /**
     * 左子树
     */
    private MaxHeap<T> left;
    /**
     * 右子树
     */
    private MaxHeap<T> right;
    /**
     * 值
     */
    private T val;

    public MaxHeap() throws Exception {
    }


    public MaxHeap(T[] ts) throws Exception {
        createHeap(ts);
    }

    public MaxHeap(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    /**
     * 建堆
     *
     * @param ts
     */
    private void createHeap(T[] ts) throws Exception {
        if (ts == null || ts.length == 0) throw new Exception("ts不能为空");
        // 1. 按照元素顺序建立完全二叉树
        createPerfectBinTree(ts);
        // 2. 从最后一个非叶子节点开始调整堆
        int lastNoLeafNode = ts.length / 2 - 1;
        for (int i = lastNoLeafNode; i >= 0; i--) {
            adjust(ts, i);
        }
    }

    /**
     * 在左右节点都为堆的前提下,调整
     */
    private void adjust(T[] ts, int index) {
        int maxChildIndex = getMaxChildIndex(ts, index);
        if (ts[index].compareTo(ts[maxChildIndex]) < 0) {
            swap(ts, index, maxChildIndex);
            adjust(ts, maxChildIndex);
        }
    }

    private void swap(T[] ts, int a, int b) {
        T temp = ts[a];
        ts[a] = ts[b];
        ts[b] = temp;
    }

    private int getMaxChildIndex(T[] ts, int i) {
        int leftIndex = getLeftChildIndex(ts, i),
                rightIndex = getRightChildIndex(ts, i);
        if (leftIndex < 0) return -1;
        if (rightIndex < 0) return leftIndex;
        return ts[leftIndex].compareTo(ts[rightIndex]) > 0 ? leftIndex : rightIndex;
    }


    private int getLeftChildIndex(T[] ts, int i) {
        int leftIndex = 2 * i + 1;
        if (leftIndex >= ts.length) return -1;
        return leftIndex;
    }

    private int getRightChildIndex(T[] ts, int i) {
        int rightIndex = 2 * i + 2;
        if (rightIndex >= ts.length) return -1;
        return rightIndex;
    }

    /**
     * 建立完全二叉树
     *
     * @param ts
     */
    private void createPerfectBinTree(T[] ts) throws Exception {
        val = ts[0];
        int length = ts.length;
        List<MaxHeap<T>> list = new ArrayList<>();
        List<MaxHeap<T>> tmpList;
        list.add(this);
        int i = 1;
        while (i < length) {
            tmpList = new ArrayList<>(2 * list.size());
            for (MaxHeap<T> heap : list) {
                heap.left = new MaxHeap<T>(ts[i++]);
                tmpList.add(heap.left);
                if (i < length) {
                    heap.right = new MaxHeap<T>(ts[i++]);
                    tmpList.add(heap.right);
                }
            }
            list = tmpList;
        }
    }

    public void setLeft(MaxHeap<T> left) {
        this.left = left;
    }

    public MaxHeap<T> getLeft() {
        return left;
    }

    public MaxHeap<T> getRight() {
        return right;
    }

    public void setRight(MaxHeap<T> right) {
        this.right = right;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public static void main(String[] args) {

    }
}
