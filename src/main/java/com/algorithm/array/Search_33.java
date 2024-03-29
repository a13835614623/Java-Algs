package com.algorithm.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 
 * 你可以假设数组中不存在重复的元素。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 示例 1:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4
 * 
 * 示例 2:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
 * 
 * 
 * @author zzk
 *
 */
public class Search_33 {
	public static int search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;
		int left = 0, right = nums.length - 1;
		while (left <= right) {
			int mid = left + ((right - left) >> 2);
			if (target == nums[mid])
				return mid;
			if (nums[mid] > nums[right]) {// 中间大于右边说明左边有序
				if (target < nums[mid] && target >= nums[left])// 如果在左边范围内
					right = mid - 1;
				else {
					left = mid + 1;
				}
			} else {// 右边有序
				if (target > nums[mid] && target <= nums[right])// 如果在右边范围内
					left = mid + 1;
				else{
					right = mid - 1;
				}
			}

		}
		return -1;
	}

	public static void main(String[] args) {
		int a[] = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(search(a, 6));
	}
}
