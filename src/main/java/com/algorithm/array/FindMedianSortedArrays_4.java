package com.algorithm.array;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * 则中位数是 2.0
 * 
 * 示例 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * 则中位数是 (2 + 3)/2 = 2.5
 * 
 * 
 * @author zzk
 *
 */
public class FindMedianSortedArrays_4 {
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1 == null ? 0 : nums1.length, len2 = nums2 == null ? 0 : nums2.length;
		int mid = (len1+len2) >> 1;
		int mid1 = 0, mid2 = 0;
		int index = 0;
		int i = 0, j = 0;
		for (; i < len1 && j < len2 && index < mid + 1;) {
			mid1 = mid2;
			mid2 = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
			index++;
		}
		if (index < mid + 1)
			if (i == len1) {
				while (index++ < mid + 1) {
					mid1 = mid2;
					mid2 = nums2[j++];
				}
			} else {
				while (index++ < mid + 1) {
					mid1 = mid2;
					mid2 = nums1[i++];
				}
			}
		return (len1+len2) % 2 == 0 ? (mid1 + mid2) / 2.0 : mid2;
	}

	public static void main(String[] args) {
		int[] nums1 = { 1, 2, 3 };
		int[] nums2 = { 1 };
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
}
