package com.algorithm.dynamic;

import com.algorithm.array.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description CanPartition_416
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/06/04
 */
public class CanPartition_416 {
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[length + 1][target + 1];
        // 代表选择前i个元素是否存在元素和恰好等于j
        for (int i = 0; i <= length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j] || j >= nums[i - 1] && dp[i - 1][j - nums[i - 1]];
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[length][target];
    }

    public boolean canPartition2(int[] nums) {
        int length = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = target; j >=0; j--) {
                dp[j] = dp[j] || j >= nums[i - 1] && dp[j - nums[i - 1]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        CanPartition_416 canPartition_416 = new CanPartition_416();
        System.out.println(canPartition_416.canPartition2(new int[]{
                1,2,5
        }));
        System.out.println("===================");
        System.out.println(canPartition_416.canPartition(new int[]{
                1,2,5
        }));

    }
}
