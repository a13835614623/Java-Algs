package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4]
 * 
 * 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6 输出: [-1,-1]
 * 
 * 
 * @author zzk
 *
 */
public class SearchRange_34 {
	public static int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };
		if (nums == null || nums.length == 0)
			return result;
		int i = 0;
		for (; i < nums.length; i++) {
			if (nums[i] == target) {
				result[0]=result[1] = i;
				break;
			}
		}
		for (i++; i < nums.length; i++) {
			if (nums[i] == target) {
				result[1] = i;
			}else{
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 3 };
		ArrayUtil.printArray(searchRange(nums, 2));
	}
}
