package com.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: nums = [1,2,3] 输出: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 * 
 * @description: 子集
 * @author zzk
 * @className Subsets_78.java
 * @date 2019年4月20日 上午10:25:06
 */
public class Subsets_78 {
	public List<List<Integer>> subsets(int[] nums) {
		if (nums == null || nums.length == 0)
			return new ArrayList<>();
		return sub(nums, 0);
	}

	public static List<List<Integer>> sub(int[] nums, int start) {
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		// 添加自身
		if (start == nums.length - 1) {
			List<Integer> list = new ArrayList<>();
			list.add(nums[start]);
			resultList.add(list);
			list = new ArrayList<>();
			resultList.add(list);
			return resultList;
		} else {
			List<List<Integer>> list2 = sub(nums, start + 1);
			resultList.addAll(list2);
			List<Integer> tmpList = null;
			for (List<Integer> list3 : list2) {
				tmpList = new ArrayList<>(list3);
				tmpList.add(nums[start]);
				resultList.add(tmpList);
			}
		}
		return resultList;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4 };
		List<List<Integer>> list = sub(nums, 0);
		for (List<Integer> list2 : list) {
			System.out.println(list2);
		}
	}
}
