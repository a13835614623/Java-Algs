package com.algorithm.array;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,0] 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: [3,4,-1,1] 输出: 2
 * 
 * 示例 3:
 * 
 * 输入: [7,8,9,11,12] 输出: 1
 * 
 * 说明:
 * 
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间
 * 
 * @description: 缺失的第一个正数
 * @author zzk
 * @className FirstMissingPositive_41.java
 * @date 2019年4月6日 上午10:23:03
 */
public class FirstMissingPositive_41 {
	/**
	 * @description: 暴力法
	 * @author zzk
	 * @date 2019年4月6日 上午11:11:33
	 * @param nums
	 * @return 确实的第一个正数
	 */
	public static int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;
		int len = nums.length;
		for (int i = 1; i <= len; i++) {
			boolean isSearch = false;
			for (int j = 0; j < len; j++) {
				if (i == nums[j]) {
					isSearch = true;
					break;
				}
			}
			if (!isSearch)
				return i;
		}
		return 1;
	}
	/**
	 * 交换法
	 * @description: 将大小小于等于长度的元素交换到对应值减一的索引
	 * @author zzk
	 * @date 2019年4月6日 上午11:49:32
	 * @param nums
	 * @return
	 */
	public static int firstMissingPositive2(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;
		int len = nums.length;
		int tmp = 0;
		for (int i = 1; i < len;) {
			int num = nums[i];
			// 如果num在1-len之间，则与num[值-1]交换
			if (num > 0 && num <= len && num != nums[num - 1]) {
				tmp = num;
				nums[i] = nums[num - 1];
				nums[num - 1] = tmp;
			} else
				i++;
		}
		for (int i = 0; i < len; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return len + 1;
	}

	public static void main(String[] args) {
		int nums[] = { 1, 2, 0 };
		int nums2[] = { 3, 4, -1, 1 };
		int nums3[] = { 7, 8, 9, 11, 12 };
		int nums4[] = { 6, 5, 3, 2, 1 };
		System.out.println(firstMissingPositive2(nums));
	}
}
