package com.algorithm.array;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 
 * 说明：m 和 n 的值均不超过 100。
 * 
 * 示例 1:
 * 
 * 输入: [ [0,0,0], [0,1,0], [0,0,0] ] 输出: 2 解释: 3x3 网格的正中间有一个障碍物。 从左上角到右下角一共有 2
 * 条不同的路径： 1. 向右 -> 向右 -> 向下 -> 向下 2. 向下 -> 向下 -> 向右 -> 向右
 * 
 * @description: 不同路径 II
 * @author zzk
 * @className UniquePathsWithObstacles_63.java
 * @date 2019年4月14日 上午9:45:41
 */
public class UniquePathsWithObstacles_63 {
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int paths[][] = new int[m][n];
		for (int i = 0; i < paths[0].length; i++) {
			if (obstacleGrid[0][i] == 1) {
				while (i < paths[0].length) {
					paths[0][i++] = 0;
				}
				break;
			} else {
				paths[0][i] = 1;
			}
		}
		for (int i = 0; i < paths.length; i++) {
			if (obstacleGrid[i][0] == 1) {
				while (i < paths.length) {
					paths[i++][0] = 0;
				}
				break;
			} else {
				paths[i][0] = 1;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					paths[i][j] = 0;
				} else {
					paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
				}
			}
		}
		return paths[m - 1][n - 1];
	}

	public static int calc(int[][] paths, int[][] grid, int m, int n) {
		if (paths[m][n] != 0 || m == 0 || n == 0)
			return paths[m][n];
		if (grid[m][n] == 1)
			return paths[m][n] = 0;
		return paths[m][n] = calc(paths, grid, m - 1, n) + calc(paths, grid, m, n - 1);
	}

	public static void main(String[] args) {
		int obstacleGrid[][] = { { 0, 0, 0, 0, 0, 0, 0 } };
		System.out.println(uniquePathsWithObstacles(obstacleGrid));
	}

}
