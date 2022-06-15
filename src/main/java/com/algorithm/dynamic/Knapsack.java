package com.algorithm.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 背包问题
 * 有 N 个物品和容量为 W 的背包，每个物品都有
 * 自己的体积 w 和价值 v，求拿哪些物品可以使得背包所装下物品的总价值最大。如果限定每种物
 * 品只能选择 0 个或 1 个，则问题称为 0-1 背包问题；如果不限定每种物品的数量，则问题称为无 界背包问题或完全背包问题
 * @author 张子宽
 * @date 2022/06/04
 */
public class Knapsack {

    public List<Integer> bag(int[] weights, int[] values, int W) {
        int len = weights.length;
        List<Integer> res = new ArrayList<>();
        int dp[][] = new int[len][W];
        dp[0][0] = 0;
        // 只选择前i个且最大容量为j时候的最大价值
        for (int i = 1; i < len; i++) {
            int weight = weights[i];
            int value = values[i];
            for (int j = 1; j < W; j++) {
                // 选择当前物品
                if (j >= weight) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j - weight] + value);
                } else {
                    // 如果不选择当前物品
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
