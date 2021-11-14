package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 
 * 示例 1:
 * 
 * 输入: [ [1,1,1], [1,0,1], [1,1,1] ] 输出: [ [1,0,1], [0,0,0], [1,0,1] ]
 * 
 * 示例 2:
 * 
 * 输入: [ [0,1,2,0], [3,4,5,2], [1,3,1,5] ] 输出: [ [0,0,0,0], [0,4,5,0], [0,3,1,0]
 * ]
 * 
 * 进阶:
 * 
 * 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 一个简单的改进方案是使用 O(m + n)
 * 的额外空间，但这仍然不是最好的解决方案。 你能想出一个常数空间的解决方案吗？
 * 
 * 
 * @description: 矩阵置零
 * @author zzk
 * @className SetZeroes_73.java
 * @date 2019年4月16日 下午9:53:24
 */
public class SetZeroes_73 {
	public static void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		int len = matrix.length, len0 = matrix[0].length;
		boolean[] emptyRows = new boolean[len];
		boolean[] emptyCols = new boolean[len0];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len0; j++) {
				if (matrix[i][j] == 0) {
					emptyRows[i] = emptyCols[j] = true;
				}
			}
		}
		for (int i = 0; i < len0; i++) {
			if(emptyCols[i]) {
				for (int j = 0; j < len; j++) {
					matrix[j][i] = 0;
				}
			}
		}
		for (int i = 0; i < len; i++) {
			if(emptyRows[i]) {
				for (int j = 0; j < len0; j++) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
		ArrayUtil.printArray2(matrix);
		System.out.println("---------------------------------------");
		setZeroes(matrix);
		ArrayUtil.printArray2(matrix);
	}
}
