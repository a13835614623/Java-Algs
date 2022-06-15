package com.algorithm.dynamic;

import com.algorithm.array.util.ArrayUtil;

/**
 * @description FindMaxForm_474
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/06/05
 */
public class FindMaxForm_474 {

    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][] counts = new int[length][2];
        for (int i = 0; i < length; i++) {
            String str = strs[i];
            for (int j = str.length() - 1; j >= 0; j--) {
                counts[i][str.charAt(j) - '0']++;
            }
        }
         ArrayUtil.printArray2(counts);
        // 前i个字符串最多有 j 个 0 和 k 个 1 的最大子集个数
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        dp[0][0][0] = 0;
        // 每个字符串的0和1的个数
        for (int i = 1; i <= length; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j == 0 && k == 0) {
                        dp[i][j][k] = 0;
                        System.out.println(String.format("前 %s 个字符串最多有 %s 个 0 和 %s 个 1 的最大子集个数为 %s ", i, j, k, dp[i][j][k]));
                        continue;
                    }
                    int max = Math.max(Math.max(dp[i - 1][j][k], j - 1 >= 0 ? dp[i][j - 1][k] : 0), k - 1 >= 0 ? dp[i][j][k - 1] : 0);
                    int[] count = counts[i - 1];
                    if (count[0] > j || count[1] > k) {
                        //0的个数大于j 或者 1的个数大于 k,不选
                        dp[i][j][k] = max;
                    } else {
                        dp[i][j][k] = Math.max(max, dp[i - 1][j - count[0]][k - count[1]] + 1);
                    }
                    System.out.println(String.format("前 %s 个字符串最多有 %s 个 0 和 %s 个 1 的最大子集个数为 %s ", i, j, k, dp[i][j][k]));
                }
            }
            System.out.println("=====================i = " + i);
            ArrayUtil.printArray2(dp[i]);
        }
        return dp[length][m][n];
    }

    public static void main(String[] args) {
        FindMaxForm_474 findMaxForm_474 = new FindMaxForm_474();
//        System.out.println(findMaxForm_474.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 3, 5));
        System.out.println(findMaxForm_474.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }
}
