package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 
 * 示例:
 * 
 * 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
 * 
 * 
 * @description: 螺旋矩阵 II
 * @author zzk
 * @className GenerateMatrix_59.java
 * @date 2019年4月12日 下午4:32:16
 */
public class GenerateMatrix_59 {
	public static int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int lenRow = matrix.length, lenCol = matrix[0].length;
		int dRow = 1, dCol = 1, start = 0;
		int i = 1;
		while (start <= n >> 1) {
			int bottom = start + lenRow, right = start + lenCol;
			for (int row = start, col = start; row < bottom && col < right;) {
				if (col == right - 1) {// 右边
					if (row == start) {// 右上角
						dRow = 1;
						dCol = 0;
					} else if (row == bottom - 1) {// 右下角
						dRow = 0;
						dCol = -1;
					}
				} else if (col == start) {// 左边
					if (row == start) {// 左上角
						dRow = 0;
						dCol = 1;
					} else if (row == bottom - 1) {// 左下角
						dRow = -1;
						dCol = 0;
					}
				}
				matrix[row][col] = i++;
				if (lenCol!=1&&row == start + 1 && col == start) {
					break;
				}
				row += dRow;
				col += dCol;
			}
			lenRow -= 2;
			lenCol -= 2;
			start++;
		}
		return matrix;
	}

	public static void main(String[] args) {
		ArrayUtil.printArray2(generateMatrix(8));
	}
}
