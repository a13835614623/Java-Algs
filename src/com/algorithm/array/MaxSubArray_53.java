package com.algorithm.array;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 示例:
 * 
 * 输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 * 进阶:
 * 
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * 
 * @author zzk
 *
 */
public class MaxSubArray_53 {
	/**
	 * 扫描法——O(N) 当我们加上一个正数时，和会增加；当我们加上一个负数时，和会减少。如果当前得到的和是个负数，
	 * 那么这个和在接下来的累加中应该抛弃并重新清零，不然的话这个负数将会减少接下来的和。
	 * 
	 * @param nums
	 * @return
	 */
	public static int maxSubArray2(int[] nums) {
		int res = nums[0];
		int sum = 0;
		for (int num : nums) {
			if (sum < 0) {
				sum = 0;
			}
			sum += num;
			res = Math.max(sum, res);
		}
		return res;
	}

	/**
	 * 动态规划法——O(N) sum[i] = max(sum[i-1] + a[i], a[i])
	 */
	public static int maxSubArray(int[] nums) {
		int res = nums[0];
		int sum = 0;
		for (int num : nums) {
			if (sum > 0)
				sum += num;
			else
				sum = num;
			res = Math.max(res, sum);
		}
		return res;
	}

	public static void main(String[] args) {
		int nums4[] = { -2, -1 };
		System.out.println(maxSubArray2(nums4));
	}
}
