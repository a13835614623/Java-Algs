package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.util.Arrays;

/**
 * @description CountBits_338
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *  
 *
 * 提示：
 *
 * 0 <= n <= 105
 *  
 *
 * 进阶：
 *
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/27
 */
public class CountBits_338 {

    static class Solution {
        public int[] countBits(int n) {
            int dp[] = new int[n + 1];
            dp[0] = 0;
            if (n == 0) {
                return dp;
            }
            dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                if (i % 2 == 0) {
                    dp[i] = dp[i >> 1];
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            return dp;
        }

        public int[] countBits2(int n) {
            int dp[] = new int[n + 1];
            dp[0] = 0;
            if (n == 0) {
                return dp;
            }
            dp[1] = 1;
            for (int i = 2; i < n + 1; i++) {
                if (i % 2 == 0) {
                    dp[i] = dp[i >> 1];
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            return dp;
        }
    }

    public static void main(String[] args) {
        ArrayUtil.printArray(new Solution().countBits(5));
    }
}
