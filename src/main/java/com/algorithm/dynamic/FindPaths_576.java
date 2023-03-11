package com.algorithm.dynamic;

/**
 * @description
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 *
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/out-of-boundary-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2021/08/15
 * @copyright 
 */
public class FindPaths_576 {
    /**
     * @description findPaths
     * @param m 行数
     * @param n 列数
     * @param maxMove 最大移动次数
     * @param startRow 行
     * @param startColumn
     * @return int
     * @author 张子宽
     * @date 2021/08/15
     */
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if(maxMove==0){
            return 0;
        }
        long[][][] dp = new long[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) dp[i][j][1]++;
                if (i == m - 1) dp[i][j][1]++;
                if (j == 0) dp[i][j][1]++;
                if (j == n - 1) dp[i][j][1]++;
            }
        }
        return (int) (findPaths(m, n, maxMove, startRow, startColumn, dp) % (1000000007L));
    }

    private static long findPaths(int m, int n, int maxMove, int startRow, int startColumn, long[][][] dp) {
        if (maxMove == 0||dp[startRow][startColumn][maxMove] > 0) {
            return dp[startRow][startColumn][maxMove];
        }
        int move = maxMove - 1;
        long leftCount = startColumn == 0 ? 1 : findPaths(m, n, move, startRow, startColumn - 1, dp);
        long rightCount = startColumn == n - 1 ? 1 : findPaths(m, n, move, startRow, startColumn + 1, dp);
        long topCount = startRow == 0 ? 1 : findPaths(m, n, move, startRow - 1, startColumn, dp);
        long bottomCount = startRow == m - 1 ? 1 : findPaths(m, n, move, startRow + 1, startColumn, dp);
        dp[startRow][startColumn][maxMove] =(leftCount + rightCount + topCount + bottomCount)%1000000007L;
        return dp[startRow][startColumn][maxMove];
    }


    /**
     * @description main
     * 45
     * 35
     * 47
     * 20
     * 31
     * @param args
     * @return void
     * @author 张子宽
     * @date 2021/08/15
     */
    public static void main(String[] args) {
        System.out.println(findPaths(45, 35, 47, 20, 31));
    }
}
