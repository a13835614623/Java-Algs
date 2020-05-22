package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 插入排序
 *
 * @Author: zzk
 * @Date: 2020-03-05 19:29
 */
public class InsertSort {
    /**
     * 插入排序
     * @param nums
     */
    public static void insertSort(int nums[]) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    ArrayUtil.swap(nums, j - 1, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = ArrayUtil.randomArray(10);
        ArrayUtil.printArray(nums);
        insertSort(nums);
        ArrayUtil.printArray(nums);
    }

}
