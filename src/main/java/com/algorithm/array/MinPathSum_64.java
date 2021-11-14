package com.algorithm.array;

import java.util.List;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 示例:
 * 
 * 输入: [ [1,3,1], [1,5,1], [4,2,1] ] 输出: 7 解释: 因为路径 1→3→1→1→1 的总和最小。
 * 
 * @description: 最小路径和
 * @author zzk
 * @className MinPathSum_64.java
 * @date 2019年4月15日 下午6:29:25
 */
public class MinPathSum_64 {
	public static int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int len = grid.length, len0 = grid[0].length;
		int minPaths[][] = new int[len][len0];
		minPaths[0][0] = grid[0][0];
		for (int i = 1; i < len; i++) {
			minPaths[i][0] = minPaths[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < len0; i++) {
			minPaths[0][i] = minPaths[0][i - 1] + grid[0][i];
		}
		for (int i = 1; i < len; i++) {
			for (int j = 1; j < len0; j++) {
				minPaths[i][j] = grid[i][j] + Math.min(minPaths[i - 1][j], minPaths[i][j - 1]);
			}
		}
		return minPaths[len - 1][len0 - 1];
	}
	public static void main(String[] args) {
		int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(minPathSum(grid));

	}
}
