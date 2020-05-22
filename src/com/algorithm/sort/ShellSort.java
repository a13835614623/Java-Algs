package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 希尔排序
 *
 * @Author: zzk
 * @Date: 2020-03-05 19:08
 */
public class ShellSort {

    public static void shellSort(int[] nums) {
        int gap = 1, len = nums.length;
        while (gap < len / 3) {
            gap = 3 * gap + 1;
        }
        while (gap > 0) {
            for (int i = 0; i < len; i++) {
                for (int j = i; j >= gap; j -= gap) {
                    if (nums[j] < nums[j - gap]) {
                        ArrayUtil.swap(nums, j, j - gap);
                    }
                }
            }
            gap = (int) Math.floor(gap/3);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 7, 5, 5, 6, 2, 9, 3, 6, 3};
        ArrayUtil.printArray(nums);
        shellSort(nums);
        ArrayUtil.printArray(nums);
    }
}
