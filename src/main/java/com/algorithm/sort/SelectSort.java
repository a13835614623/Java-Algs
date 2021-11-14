package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 选择排序
 * 时间复杂度：O(n^2)
 * 空间复杂度: O(1)
 * @Author: zzk
 * @Date: 2020-03-05 17:42
 */
public class SelectSort {
    /**
     * 第一次遍历最小值在最左边
     * @param nums
     */
    public static void selectSort(int nums[]) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                // 如果前面的大于后面的，则交换
                if (nums[i] > nums[j]) {
                    ArrayUtil.swap(nums, j, i);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtil.randomArray(10);
        ArrayUtil.printArray(arr);
        selectSort(arr);
        ArrayUtil.printArray(arr);
    }
}
