package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 快速排序
 *
 * @Author: zzk
 * @Date: 2020-03-01 21:00
 */
public class QuickSort {

    public static void quickSort(int[] nums, int low, int high) {
        if (low>=high)return;
        // 哨兵
        int v = nums[low], left = low, right = high;
        while (left < right) {
            // 从右边找第一个比哨兵小的
            while (right > left && nums[right] > v) {
                right--;
            }
            if (left < right)
                nums[left++] = nums[right];
            // 从左边找第一个比哨兵大的
            while (left < right && nums[left] < v) {
                left++;
            }
            if (left < right)
                nums[right--] = nums[left];
        }
        nums[left] = v;
        quickSort(nums, low, left - 1);
        quickSort(nums, left + 1, high);
    }

    public static void main(String[] args) {
        int[] nums = {4, 7, 5, 5, 6, 2, 9, 3, 6, 3};
        ArrayUtil.printArray(nums);
        quickSort(nums, 0, nums.length - 1);
        ArrayUtil.printArray(nums);
    }
}
