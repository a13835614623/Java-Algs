package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 *     所有数字（包括目标数）都是正整数。
 *     解集不能包含重复的组合。
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * @author zzk
 *
 */
public class CombinationSum2_40 {
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> listAll = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		// 排序
		Arrays.sort(candidates);
		find(listAll, list, candidates, target, 0);
		return listAll;
	}

	/**
	 * 从candidates的索引为num的元素开始查找和为target的元素
	 * 
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
		// if(candidates[num]==candidates[num])return;
		// 遍历candidates
		for (int i = num; i < candidates.length && candidates[i] <= target; i++) {
			if (num != i && candidates[i - 1] == candidates[i])
				continue;
			// 深拷贝tmp
			List<Integer> list = new ArrayList<>(tmp);
			list.add(candidates[i]);
			// 递归运算，将i传递至下一次运算是为了避免结果重复。
			find(listAll, list, candidates, target - candidates[i], i + 1);
		}
	}

	public static void main(String[] args) {
		int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
		int[] candidates2 = { 2,5,2,1,2 };
		int[] candidates3 = { 1,1 };
		List<List<Integer>> a = combinationSum2(candidates, 8);
		List<List<Integer>> b = combinationSum2(candidates2, 5);
		List<List<Integer>> c = combinationSum2(candidates3, 1);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
}
