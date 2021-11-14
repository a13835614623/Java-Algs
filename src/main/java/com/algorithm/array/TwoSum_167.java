package com.algorithm.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 *     返回的下标值（index1 和 index2）不是从零开始的。
 *     你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * @description: 两数之和 II - 输入有序数组
 * @className: TwoSum_167
 * @author: zzk
 * @date: 2019-05-06 20:35
 */
public class TwoSum_167 {
	public static int[] twoSum(int[] nums, int target) {
		int[] results = new int[2];
		int sum=0;
		for (int i = 0,j=nums.length-1; i<j;) {
			sum=nums[i]+nums[j];
			if(sum==target){
				nums[0]=i+1;
				nums[1]=j+1;
				return nums;
			}
			if(sum>target){
				j--;
			}else {
				i++;
			}
		}
		return results;
	}
	public static void main(String[] args) {
		int[] arr = { 1,2, 7};
		int[] a = twoSum(arr, 10);
		System.out.println(a[0] + " " + a[1]);
	}
}
