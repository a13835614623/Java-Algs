package com.algorithm.dynamic;

import com.sun.javafx.image.IntPixelGetter;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * <p>
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * <p>
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * @Author: zzk
 * @Date: 2020-03-18 09:41
 */
public class LengthOfLIS_300 {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length, maxRes = 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            maxRes = Math.max(maxRes, dp[i]);
        }
        return maxRes;
    }

    public static void main(String[] args) {
        int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int nums2[] = {4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS(nums2));
    }
}
