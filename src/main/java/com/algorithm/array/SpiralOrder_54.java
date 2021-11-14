package com.algorithm.array;

import java.util.LinkedList;
import java.util.List;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * 示例 1:
 * 
 * 输入: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] 输出: [1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2:
 * 
 * 输入: [ [1, 2, 3, 4], [5, 6, 7, 8], [9,10,11,12] ] 输出:
 * [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 
 * @author zzk
 *
 */
public class SpiralOrder_54 {
	public static List<Integer> spiralOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return null;
		int lenRow = matrix.length, lenCol = matrix[0].length;
		int dRow = 1, dCol = 1, start = 0;
		List<Integer> list = new LinkedList<Integer>();
		while (start <= matrix[0].length >> 1) {
			int bottom = start + lenRow, right = start + lenCol;
			for (int row = start, col = start; row < bottom && col < right;) {
				if (col == right - 1) {// 右边
					if (row == start) {// 右上角
						dRow = 1;
						dCol = 0;
						if (lenRow == 1) {
							list.add(matrix[row][col]);
							break;
						}
					} else if (row == bottom - 1) {// 右下角
						dRow = 0;
						dCol = -1;
						if (lenCol == 1) {
							list.add(matrix[row][col]);
							break;
						}
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
				System.out.print("[" + row + "," + col + "]:");
				System.out.println(matrix[row][col]);
				list.add(matrix[row][col]);
				if (lenCol!=1&&row == start + 1 && col == start) {
					System.out.println();
					break;
				}
				row += dRow;
				col += dCol;
			}
			lenRow -= 2;
			lenCol -= 2;
			start++;
		}
		return list;
	}

	public static void main(String[] args) {
		int matrix[][] = { { 1},{ 2},{ 3},{ 4 }};
		ArrayUtil.printArray2(matrix);
		List<Integer> list = spiralOrder(matrix);
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
	}
}
