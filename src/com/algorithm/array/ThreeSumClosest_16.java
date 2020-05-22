package com.algorithm.array;

import java.util.Arrays;

/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
/*
 * list1:	0 	0 	0
 * list2:	0 	0 	0
 * 	i      j       k 
 */
public class ThreeSumClosest_16 {
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[nums.length - 1] - target; // 初始值
		for (int i = 0; i < nums.length; i++) {// -3 0 1 2 目的1
			int k = nums.length - 1;// 第三个数
			int j = i + 1;// 第二个数的索引
			while (j < k) {// 寻找第二个数
				int sum = nums[i] + nums[k] + nums[j];// 第一个和第三个数之和
				if (Math.abs(sum - target) < Math.abs(result)) {// 找到
					result = sum - target;// 赋值
				}
				if (result == 0) {
					return target;
				} else if (sum - target > 0) {
					k--;
				} else {
					j++;
				}
			}
		}
		return result + target;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 4, 8, 16, 32, 64, 128 };// -3
		System.out.println(new ThreeSumClosest_16().threeSumClosest(nums, 82));
	}
}
