package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.util.Arrays;

/**
 * @description NumberOfArithmeticSlices_413
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/arithmetic-slices
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/06/04
 */
public class NumberOfArithmeticSlices_413 {
    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return 0;
        }
        int[] deltas = new int[length];
        deltas[0] = 0;
        deltas[1] = 1;
        int lastDelta = nums[1] - nums[0];
        for (int i = 2; i < length; i++) {
            int delta = nums[i] - nums[i - 1];
            if (delta == lastDelta) {
                deltas[i] = deltas[i - 1] + 1;
            } else {
                deltas[i] = 0;
                if (i + 1 < length) {
                    deltas[i] = 1;
                    if (deltas[i - 1] < 2) {
                        deltas[i - 1] = 0;
                    }
                }
            }
            lastDelta = delta;
        }
        int[] dp = new int[length];
        for (int i = 2; i < length; i++) {
            if (deltas[i] >= 2) {
                dp[i] = dp[i - 1] + deltas[i] - 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        NumberOfArithmeticSlices_413 x = new NumberOfArithmeticSlices_413();
        System.out.println(x.numberOfArithmeticSlices(new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 17, 25, 26, 27
        }));
    }
}
