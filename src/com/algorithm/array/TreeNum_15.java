package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
public class TreeNum_15 {
	public List<List<Integer>> threeSum2(int[] nums) {
		Set<List<Integer>> set = new HashSet<>();
		List<Integer> list = null;
		Arrays.sort(nums);
		List<Integer> plusNums = new ArrayList<>();// 正数索引
		List<Integer> minusNums = new ArrayList<>();// 负数索引
		List<Integer> zeroNums = new ArrayList<>();// 0索引
		for (int i = 0, len = nums.length; i < len; i++) {
			int num = nums[i];
			if (num == 0) {
				zeroNums.add(num);
			} else if (num > 0) {
				plusNums.add(num);
			} else {
				minusNums.add(num);
			}
		}
		int lenP = plusNums.size();
		int lenM = minusNums.size();
		int lenZ = zeroNums.size();
		if (lenZ >= 3) {
			list = new LinkedList<>();
			list.add(0);
			list.add(0);
			list.add(0);
			set.add(list);
		}
		for (int i = 0; i < lenP - 1; i++) {// 两正一负
			for (int j = i + 1; j < lenP; j++) {
				int ip = plusNums.get(i);
				int jp = plusNums.get(j);
				if (minusNums.contains(-(ip + jp))) {
					list = new LinkedList<>();
					list.add(-(ip + jp));
					list.add(ip);
					list.add(jp);
					set.add(list);
				}
			}
		}
		for (int i = 0; i < lenM - 1; i++) {// 两负一正
			for (int j = i + 1; j < lenM; j++) {
				int im = minusNums.get(i);
				int jm = minusNums.get(j);
				if (plusNums.indexOf(-(im + jm)) != -1) {// 存在
					list = new LinkedList<>();
					list.add(im);
					list.add(jm);
					list.add(-(im + jm));
					set.add(list);

				}
			}
		}
		if (lenZ > 0) {// 一负一正一0
			for (int i = 0; i < lenP; i++) {
				int ip = plusNums.get(i);
				if (minusNums.indexOf(-ip) != -1) {// 存在
					list = new LinkedList<>();
					list.add(-ip);
					list.add(0);
					list.add(ip);
					set.add(list);
				}
			}

		}
		List<List<Integer>> result = new LinkedList<>();
		result.addAll(set);
		return result;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new LinkedList<>();
		List<Integer> list = null;
		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < nums.length - 2; i++) {
			for (int j = i + 1; j < nums.length - 1; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					if (nums[i] + nums[j] == -nums[k]) {
						list = new LinkedList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[k]);
						set.add(list);
					}
				}
			}
		}
		result.addAll(set);
		return result;
	}

	public List<List<Integer>> threeSum3(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new LinkedList<>();// 结果集
		List<Integer> list = null;
		for (int i = 0; i < nums.length; i++) {
			int k = nums.length - 1;// 第三个数
			int j = i + 1;// 第二个数的索引
			while (j < k) {// 寻找第二个数
				int sum = nums[i] + nums[k];// 第一个和第三个数之和
				if (nums[j] == -sum) {// 找到，添加
					list = new LinkedList<>();
					list.add(nums[i]);
					list.add(nums[j]);
					list.add(nums[k]);
					result.add(list);
					while (j < k && nums[j] == nums[j + 1]) {
						j++;
					}
					while (i < j && nums[i] == nums[i + 1]) {
						i++;
					}
					while (j < k && nums[k] == nums[k - 1]) {
						k--;
					}
					j++;
					k--;
				} else {
					if (nums[j] + sum < 0) {
						j++;
					} else {
						k--;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { -2, -1, 2, -4, -1, -1, -1, -5, -4, 3, -2, 0 };// -4 -1 -1
																		// 0 1 2
																		// 2
		System.out.println(new TreeNum_15().threeSum3(nums));
	}
}
