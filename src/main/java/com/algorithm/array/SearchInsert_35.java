package com.algorithm.array;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * 你可以假设数组中无重复元素。
 * 
 * 示例 1:
 * 
 * 输入: [1,3,5,6], 5 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: [1,3,5,6], 2 输出: 1
 * 
 * 示例 3:
 * 
 * 输入: [1,3,5,6], 7 输出: 4
 * 
 * 示例 4:
 * 
 * 输入: [1,3,5,6], 0 输出: 0
 * 
 * 
 * @author zzk
 *
 */
public class SearchInsert_35 {
	public static int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return 0;
		if (target > nums[nums.length - 1])
			return nums.length;
		if (target < nums[0])
			return 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				return i;
			} else if (i > 0 && nums[i] > target && nums[i - 1] < target) {
				return i;
			}
		}
		return target;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5, 6 };
		System.out.println(searchInsert(nums, 1));
	}
}
