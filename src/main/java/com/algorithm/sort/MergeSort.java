package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 归并排序
 * 时间复杂度：O(n*lg(n))
 * 空间复杂度: O(n)
 * @Author: zzk
 * @Date: 2020-03-05 17:44
 */
public class MergeSort {

    public static void mergeSort(int nums[]) {
        int len = nums.length;
        int[] res = new int[len];
        sort(nums, res, 0, len);
    }

    public static void sort(int nums[], int res[], int start, int end) {
        if (end <= start+1) return;
        int mid = start + ((end - start) >> 1);
        sort(nums, res, start, mid);
        sort(nums, res, mid, end);
        merge(nums, res, start, mid, end);
    }

    public static void merge(int nums[], int res[], int left, int mid, int right) {
        int index = 0;
        int i = left, j = mid;
        while (i < mid && j < right) {
            if (nums[i] < nums[j]) {
                res[index] = nums[i++];
            } else {
                res[index] = nums[j++];
            }
            index++;
        }
        while (i < mid) {
            res[index++] = nums[i++];
        }
        while (j < right) {
            res[index++] = nums[j++];
        }
        i = 0;
        while (left < right) {
            nums[left++] = res[i++];
        }
    }

    public static void copy(int[] src, int srcStart, int[] dst, int dstStart, int length) {
        for (int i = 0; i < length; i++) {
            dst[dstStart + i] = src[srcStart + i];
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtil.randomArray(10);
        ArrayUtil.printArray(arr);
        mergeSort(arr);
        ArrayUtil.printArray(arr);
    }
}
