package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 所有数字（包括 target）都是正整数。 解集不能包含重复的组合。
 * 
 * 示例 1:
 * 
 * 输入: candidates = [2,3,6,7], target = 7, 所求解集为: [ [7], [2,2,3] ]
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,3,5], target = 8, 所求解集为: [ [2,2,2,2], [2,3,3], [3,5] ]
 * 
 * 
 * @author zzk
 */
public class CombinationSum_39 {

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> listAll = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		// 排序
		Arrays.sort(candidates);
		find(listAll, list, candidates, target, 0);
		return listAll;
	}
	/**
	 * 从candidates的索引为num的元素开始查找和为target的元素
	 * @param listAll
	 * @param tmp
	 * @param candidates
	 * @param target
	 * @param num
	 */
	public static void find(List<List<Integer>> listAll, List<Integer> tmp, int[] candidates, int target, int num) {
		// 递归的终点
		if (target == 0) {
			listAll.add(tmp);
			return;
		}
		if (target < candidates[0])
			return;
		// 遍历candidates
		for (int i = num; i < candidates.length && candidates[i] <= target; i++) {
			// 深拷贝tmp
			List<Integer> list = new ArrayList<>(tmp);
			list.add(candidates[i]);
			// 递归运算，将i传递至下一次运算是为了避免结果重复。
			find(listAll, list, candidates, target - candidates[i], i);
		}
	}

	public static boolean contains(List<List<Integer>> lists, List<Integer> list1) {
		for (List<Integer> list : lists) {
			if (listEquals(list1, list)) {
				return true;
			}
		}
		return false;
	}

	public static boolean listEquals(List<Integer> list1, List<Integer> list2) {
		if ((list1 == null || list2 == null) || list1.size() != list2.size())
			return false;
		for (Integer integer : list2) {
			if (!list2.contains(integer)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] candidates = { 1, 2 };
		int[] candidates2 = { 2, 3, 6, 7 };
		int[] candidates3 = { 2, 3, 5 };
		int[] candidates4 = { 8, 7, 4, 3 };
		List<List<Integer>> a = combinationSum(candidates, 4);
		List<List<Integer>> b = combinationSum(candidates2, 7);
		List<List<Integer>> c = combinationSum(candidates3, 8);
		List<List<Integer>> d = combinationSum(candidates4, 11);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
}
