package com.algorithm.array;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 
 * 将图像顺时针旋转 90 度。
 * 
 * 说明：
 * 
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 
 * 示例 1:
 * 
 * 给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
 * 
 * 原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ]
 * 
 * 示例 2:
 * 
 * 给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
 * 
 * 原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
 * 
 * 
 * @author zzk
 *
 */
public class Rotate_48 {
	public static void rotate(int[][] matrix) {
		int n = matrix.length - 1;
		int start = 0;
		for (int i = start; i < matrix.length >> 1; i++) {
			for (int j = start; j < n; j++) {
				swap(matrix, i, j, j, n);
				swap(matrix, i, j, n, n - (j - start));
				swap(matrix, i, j, n - (j - start), i);
			}
			n--;
			start++;
			// ArrayUtil.printArray2(matrix);
			// System.out.println("------------------------");
		}
	}

	public static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
		int a = matrix[x1][y1];
		matrix[x1][y1] = matrix[x2][y2];
		matrix[x2][y2] = a;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 5, 1, 9, 11, 17 }, { 2, 4, 8, 10, 18 }, { 13, 3, 6, 7, 19 }, { 15, 14, 12, 16, 20 },
				{21,22,23,24,25}};
		for (int[] is : matrix) {
			for (int i : is) {
				System.out.printf("%4d", i);
			}
			System.out.println();
		}
		System.out.println("_----------------------------");
		rotate(matrix);
		for (int[] is : matrix) {
			for (int i : is) {
				System.out.printf("%4d", i);
			}
			System.out.println();
		}
	}

}
