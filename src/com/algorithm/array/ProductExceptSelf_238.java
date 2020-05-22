package com.algorithm.array;

public class ProductExceptSelf_238 {
	/*
	 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除
	 * nums[i] 之外其余各元素的乘积。
	 * 
	 * 示例:
	 * 
	 * 输入: [1,2,3,4] 输出: [24,12,8,6]
	 * 
	 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
	 * 
	 * 
	 */
	public static int[] productExceptSelf(int[] arr) {
		int[] res = new int[arr.length];
		int[] sums = new int[arr.length];
		int sum = 1;
		for (int i = 0, len = arr.length; i < len; i++) {
			sum *= arr[i];
			sums[i] = sum;
		}
		sum = 1;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (i != 0)
				res[i] = sum * sums[i - 1];
			else
				res[i] = sum;
			sum *= arr[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };
		System.out.println(productExceptSelf(arr));
	}
}
