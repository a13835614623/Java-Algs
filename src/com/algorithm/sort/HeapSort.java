package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 堆排序
 *
 * @Author: zzk
 * @Date: 2020-03-05 20:17
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        ArrayUtil.printArray(arr);
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            ArrayUtil.swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
            ArrayUtil.printArray(arr);
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param p
     * @param length
     */
    public static void adjustHeap(int[] arr, int p, int length) {
        int parent = arr[p];//先取出当前元素i
        for (int child = leftChild(p); child < length; child = leftChild(child)) {//从i结点的左子结点开始，也就是2i+1处开始
            if (child + 1 < length && arr[child] < arr[child + 1]) {//如果左子结点小于右子结点，k指向右子结点
                child++;
            }
            // k指向左右子节点中大的那一个
            if (arr[child] > parent) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[p] = arr[child];
                p = child;
            } else {
                break;
            }
        }
        arr[p] = parent;//将temp值放到最终的位置
    }

    public static int leftChild(int i) {
        return 2 * i + 1;
    }


    public static void main(String[] args) {
        int[] arr = ArrayUtil.randomArray(10);
        ArrayUtil.printArray(arr);
        heapSort(arr);
        ArrayUtil.printArray(arr);
    }
}
