package com.algorithm.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 */
public class FourSum_18 {
	/**
	 * 思路类似三数之和
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> lists = new LinkedList<>();
		List<Integer> list = null;
		int i = 0, j = 0, len = nums.length;
		while (i < len) {
			j = i + 1;
			while (j < len) {
				int sumij = nums[i] + nums[j];
				int a = len - 1;
				int b = j + 1;
				int sum = 0;
				while (a > b) {
					sum = nums[a] + nums[b] + sumij;
					if (sum == target) {
						list = new LinkedList<>();
						list.add(nums[i]);
						list.add(nums[a]);
						list.add(nums[b]);
						list.add(nums[j]);
						lists.add(list);
						while (a > b && nums[a] == nums[a - 1]) {
							a--;
						}
						while (b < a && nums[b] == nums[b + 1]) {
							b++;
						}
						while (i < j && nums[i] == nums[i + 1]) {
							i++;
						}
						while (i < j && nums[j] == nums[j + 1]) {
							j++;
						}
						a--;
						b++;
					} else {
						if (sum < target) {
							b++;
						} else {
							a--;
						}
					}
				}
				j++;
			}
			i++;
		}
		return lists;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		System.out.println(fourSum(nums, 0));
	}
}
