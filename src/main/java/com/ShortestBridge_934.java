package com;

import com.algorithm.array.util.ArrayUtil;

import java.util.*;

/**
 * @description ShortestBridge_934
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 *
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 *
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1 。）
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 *
 * 输入：A = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 *
 * 输入：A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 2 <= A.length == A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shortest-bridge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/22
 */
public class ShortestBridge_934 {

    private static final int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static int shortestBridge(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        Queue<int[]> list = new LinkedList<>();
        // 找到第一个岛
        for (int i = 0; i < rowLen; i++) {
            boolean find = false;
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    // 将第一个岛屿的值设置为2，与另外一个岛屿做区分
                    dfs(grid, i, j, rowLen, colLen, list);
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }
        int level = 0;
        int[] point;
        int i, j, newI, newJ, size;
        ArrayUtil.printArray2(grid);
        while (!list.isEmpty()) {
            level++;
            size = list.size();
            for (int index = 0; index < size; index++) {
                point = list.poll();
                i = point[0];
                j = point[1];
                for (int[] direction : directions) {
                    newI = i + direction[0];
                    newJ = j + direction[1];
                    if (newI < 0 || newI >= rowLen || newJ < 0 || newJ >= colLen || grid[newI][newJ] == 2) {
                        continue;
                    }
                    // 找到了第二个岛屿
                    if (grid[newI][newJ] == 1) {
                        return level;
                    } else {
                        list.offer(new int[]{newI, newJ});
                        grid[newI][newJ] = 2;
                    }
                }
            }
        }
        return level;
    }

    private static void dfs(int[][] grid, int i, int j, int rowLen, int colLen, Queue<int[]> list) {
        grid[i][j] = 2;
        int newI, newJ;
        for (int[] direction : directions) {
            newI = i + direction[0];
            newJ = j + direction[1];
            if (newI < 0 || newI >= rowLen || newJ < 0 || newJ >= colLen || grid[newI][newJ] == 2) {
                continue;
            }
            if (grid[newI][newJ] == 0) {
                list.add(new int[]{newI, newJ});
                grid[newI][newJ] = 2;
            } else {
                dfs(grid, newI, newJ, rowLen, colLen, list);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(shortestBridge(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        }));
    }
}
