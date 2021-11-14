package com.algorithm.array;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 
 * 问总共有多少条不同的路径？
 * 
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * 
 * 说明：m 和 n 的值均不超过 100。
 * 
 * @description: 不同路径
 * @author zzk
 * @className UniquePaths_62.java
 * @date 2019年4月14日 上午9:15:22
 */
public class UniquePaths_62 {
	/**
	 *  动态规划法解决
	 * @author zzk
	 * @date 2019年4月14日 上午9:39:45
	 * @param m
	 * @param n
	 * @return
	 */
	public static int uniquePaths(int m, int n) {
		int paths[][] = new int[m][n];
		return calc(paths, m - 1, n - 1);
	}

	public static int calc(int[][] paths, int m, int n) {
		if (paths[m][n] != 0)
			return paths[m][n];
		if (m == 0 || n == 0) {
			return paths[m][n] = 1;
		} else {
			return paths[m][n] = calc(paths, m - 1, n) + calc(paths, m, n - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(uniquePaths(51, 9));
	}
}
