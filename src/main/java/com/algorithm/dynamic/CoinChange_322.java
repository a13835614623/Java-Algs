package com.algorithm.dynamic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @description CoinChange_322
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/06/05
 */
public class CoinChange_322 {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int length = coins.length;
        int dp[][] = new int[length + 1][amount + 1];
        for (int i = 0; i <= length; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 前i个元素组成j的最小数量
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == coins[i - 1]) {
                    dp[i][j] = 1;
                    continue;
                } else if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j > coins[i - 1]) {
                    if (dp[i][j - coins[i - 1]] != -1 ) {
                        if (dp[i][j] != -1) {
                            dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
                        } else {
                            dp[i][j] = dp[i][j - coins[i - 1]] + 1;
                        }
                    }
                }
            }
        }
        return dp[length][amount];
    }

    public static void main(String[] args) {
        CoinChange_322 coinChange_322 = new CoinChange_322();
//        System.out.println(coinChange_322.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange_322.coinChange(new int[]{2}, 3));
    }
}
