package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 *
 * @Description:合并两个有序数组
 * @Author: zzk
 * @Date: 2019-04-24 14:27
 */
public class Merge_88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i=m;i<m+n;i++){
            nums1[i]=nums2[i-m];
        }
        Arrays.sort(nums1);
    }
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }
    public static void main(String[] args) {
        int[] nums1 = {4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        merge(nums1, 3, nums2, 3);
        ArrayUtil.printArray(nums1);
    }
}
