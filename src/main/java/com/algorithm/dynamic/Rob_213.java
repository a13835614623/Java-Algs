package com.algorithm.dynamic;

import java.util.Arrays;
import java.util.Collections;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * @Author: zzk
 * @Date: 2020-03-16 09:52
 */
public class Rob_213 {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0], nums[1]);
        // 偷窃第一个,不偷窃最后一个
        int cur1 = Math.max(nums[0], nums[1]), last1 = nums[0], i = 2;
        //不偷窃第一个，偷窃最后一个
        int cur2 = nums[1], last2 = 0;
        while (i < len - 1) {
            int tmp = last1 + nums[i];
            cur1 = Math.max(last1 = cur1, tmp);
            i++;
        }
        i = 2;
        while (i < len) {
            int tmp = last2 + nums[i];
            cur2 = Math.max(last2 = cur2, tmp);
            i++;
        }
        return Math.max(cur1, cur2);
    }

    public static int rob2(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = nums[0];
        if (len == 1) {
            return dp[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);
        if (len % 2 == 0) {
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }else {
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 3, 2}));
    }
}
