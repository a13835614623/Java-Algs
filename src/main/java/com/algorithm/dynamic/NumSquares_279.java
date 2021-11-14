package com.algorithm.dynamic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * <p>
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * @Author: zzk
 * @Date: 2020-03-17 11:12
 */
public class NumSquares_279 {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 动态转移方程
            }
        }
        return dp[n];
    }

    /**
     * 广度优先遍历
     *
     * @param n
     * @return
     */
    public static int numSquares2(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(n);
        visited.add(n);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int len = queue.size();
            int a = 0;
            while (a < len) {
                int top = queue.poll();
                int i = 1, tmp;
                while ((tmp = top - i * i) >= 0) {
                    if (tmp==0)return level;
                    if (!visited.contains(tmp)) {
                        queue.offer(tmp);
                        visited.add(tmp);
                    }
                    i++;
                }
                a++;
            }
        }
        return level;
    }

    public static void main(String[] args) {
        System.out.println(numSquares2(12));
    }
}
