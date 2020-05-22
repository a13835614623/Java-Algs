package com.algorithm.array;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例:
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * <p>
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * @Description: 长度最小的子数组
 * @Author: zzk
 * @Date: 2019-05-09 22:04
 */
public class MinSubArrayLen_209 {
    public static int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE, sum = 0, n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > s) return 1;
            sum += nums[i];
            n++;
            if (sum >= s) {
                int j = i - n + 1;
                if (sum - nums[j] >= s) {
                    do{
                        sum -= nums[j++];
                        min = Math.min(min, --n);
                    }while (sum - nums[j] >= s);
                } else {
                    min = Math.min(min, n);
                    sum -= nums[j] + nums[j+1];
                    n -= 2;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        int nums[] = {5, 1, 3, 5, 10, 7, 4, 9, 2, 8};
        System.out.println(minSubArrayLen(15, nums));
    }
}
