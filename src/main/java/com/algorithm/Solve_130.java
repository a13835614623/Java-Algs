package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

/**
 * @description Solve_130
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 *
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *  
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/22
 */
public class Solve_130 {

    private static final int[][] directions = {
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };
    private static final int[][] upLeft = {
            {-1, 0}, {0, -1},
    };
    private static final int[][] downRight = {
            {1, 0}, {0, 1}
    };

    public static void solve(char[][] board) {
        int iLen = board.length;
        int jLen = board[0].length;
        int[][] upLeftAccess = new int[iLen][jLen];
        int[][] rightDownAccess = new int[iLen][jLen];
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (board[i][j] == 'O') {
                    dfs(board, upLeftAccess, upLeft, i, j, iLen, jLen);
                }
            }
        }
        for (int i = iLen - 1; i >= 0; i--) {
            for (int j = jLen - 1; j >= 0; j--) {
                if (upLeftAccess[i][j] == 1) {
                    dfs(board, rightDownAccess, downRight, i, j, iLen, jLen);
                }
            }
        }
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                if (rightDownAccess[i][j] == 1) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static boolean dfs(char[][] board, int[][] access,int[][] directions, int i, int j, int iLen, int jLen) {
        // 此点在边界
        if (i == 0 || i == iLen - 1 || j == 0 || j == jLen - 1) {
            access[i][j] = -1;
            return false;
        }
        if (access[i][j] != 0) {
            return access[i][j] == 1;
        }
        access[i][j] = 3;
        // 访问当前节点
        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (access[newI][newJ] == -1) {
                access[i][j] = -1;
                return false;
            }
        }
        for (int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (board[newI][newJ] == 'X' || access[newI][newJ] == 3) {
                continue;
            }
            if (!dfs(board, access, directions, newI, newJ, iLen, jLen)) {
                access[i][j] = -1;
                return false;
            }
        }
        access[i][j] = 1;
        return true;
    }


    public static void main(String[] args) {
        char[][] chars = {
                {'X', 'X', 'X', 'X', 'O', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'O', 'O'},
                {'X', 'O', 'O', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'X'}
        };
        solve(chars);
        ArrayUtil.printArray2(chars);
    }
}
