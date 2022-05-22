package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @description PacificAtlantic_417
 *
 * @author 张子宽
 * @date 2022/05/21
 */
public class PacificAtlantic_417 {
    private static final int[][] pacificDirection = {
            {-1, 0}, {0, -1},
    };

    private static final int[][] atlanticDirection = {
            {1, 0}, {0, 1}
    };
    // 上,左,下,右
    private static final int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        int ILen = heights.length;
        int JLen = heights[0].length;
        int[][][] dp = new int[ILen][JLen][5];
        for (int i = 0; i < ILen; i++) {
            for (int j = 0; j < JLen; j++) {
                if ((dfs(heights, dp, 0, i, j, ILen, JLen) == 1
                        || dfs(heights, dp, 1, i, j, ILen, JLen) == 1)
                        && (dfs(heights, dp, 2, i, j, ILen, JLen) == 1
                        || dfs(heights, dp, 3, i, j, ILen, JLen) == 1)) {
                    lists.add(Arrays.asList(i, j));
                }
            }
        }
        return lists;
    }

    public static List<List<Integer>> pacificAtlantic2(int[][] heights) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        int ILen = heights.length;
        int JLen = heights[0].length;
        boolean[][][] dp = new boolean[ILen][JLen][4];
        // 上 下
        for (int j = 0; j < JLen; j++) {
            dfs2(heights, dp, 0, 0, j, ILen, JLen);
            dfs2(heights, dp, 1, ILen - 1, j, ILen, JLen);
        }
        // 左 右
        for (int i = 0; i < ILen; i++) {
            dfs2(heights, dp, 2, i, 0, ILen, JLen);
            dfs2(heights, dp, 3, i, JLen - 1, ILen, JLen);
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                boolean[] arr = dp[i][j];
                if ((arr[0] || arr[2]) && (arr[1] || arr[3])) {
                    lists.add(Arrays.asList(i, j));
                }
            }
        }
        return lists;
    }

    private static void dfs2(int[][] heights, boolean[][][] dp, int di, int i, int j, int ILen, int JLen) {
        if (dp[i][j][di]) {
            return;
        }
        dp[i][j][di] = true;
        int height = heights[i][j];
        for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
            int newI = i + directions[dirIndex][0];
            int newJ = j + directions[dirIndex][1];
            // 处理边界
            if (newI < 0 || newI >= ILen
                    || newJ < 0 || newJ >= JLen) {
                continue;
            }
            if (heights[newI][newJ] >= height) {
                dfs2(heights, dp, di, newI, newJ, ILen, JLen);
            }
        }
    }

    private static int dfs(int[][] heights, int[][][] dp, int di, int i, int j, int ILen, int JLen) {
        if (dp[i][j][di] != 0) {
            return dp[i][j][di];
        }
        if (dp[i][j][4] == 1) {
            return -1;
        }
        int[] direction = directions[di];
        int height = heights[i][j];
        int newI = i + direction[0];
        int newJ = j + direction[1];
        // 处理边界
        if (newI < 0 || newI >= ILen
                || newJ < 0 || newJ >= JLen) {
            dp[i][j][4] = -1;
            return dp[i][j][di] = 1;
        }
        dp[i][j][4] = 1;
        for (int k = 0; k < 4; k++) {
            int[] dir = directions[k];
            newI = i + dir[0];
            newJ = j + dir[1];
            // 处理边界
            if (newI < 0 || newI >= ILen
                    || newJ < 0 || newJ >= JLen) {
                continue;
            }
            if (height >= heights[newI][newJ] && dfs(heights, dp, di, newI, newJ, ILen, JLen) == 1) {
                dp[i][j][4] = -1;
                return dp[i][j][di] = 1;
            }
        }
        dp[i][j][4] = -1;
        return dp[i][j][di] = -1;
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5},
                            {3, 2, 3, 4, 4},
                            {2, 4, 5, 3, 1},
                            {6, 7, 1, 4, 5},
                            {5, 1, 1, 2, 4}};
        List<List<Integer>> lists = pacificAtlantic2(heights);
        System.out.println(lists);

        int[][] heights2 = {{1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}};
        List<List<Integer>> lists2 = pacificAtlantic2(heights2);
        System.out.println(lists2);

    }
}
