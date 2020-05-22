package com.algorithm.array;

import java.util.Arrays;

import com.algorithm.array.util.ArrayUtil;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 
 * 每行中的整数从左到右按升序排列。 每行的第一个整数大于前一行的最后一个整数。
 * 
 * 示例 1:
 * 
 * 输入: matrix = [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] target = 3
 * 输出: true
 * 
 * 示例 2:
 * 
 * 输入: matrix = [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] target = 13
 * 输出: false
 * 
 * 
 * @description: 搜索二维矩阵
 * @author zzk
 * @className SearchMatrix_74.java
 * @date 2019年4月17日 上午10:35:28
 */
public class SearchMatrix_74 {
	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int lenRow = matrix.length, lenCol = matrix[0].length;
		if (target < matrix[0][0] || target > matrix[lenRow - 1][lenCol - 1]) {
			return false;
		}
		int midRow = lenRow >> 1, left = 0, right = lenRow - 1;
		while (left < right) {
			int mid = matrix[midRow][0];
			if (mid == target) {
				return true;
			} else if (mid < target) {
				left = midRow + 1;
			} else {
				right = midRow - 1;
			}
			midRow = (left + right) >> 1;
		}
		if (midRow > lenRow - 1 || midRow < 0)
			return false;
		if(matrix[midRow][lenCol-1]<target)midRow++;
		else if(matrix[midRow][0]>target)midRow--;
        if (midRow > lenRow - 1 || midRow < 0)
			return false;
        return Arrays.binarySearch(matrix[midRow], target) >= 0;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
		System.out.println(searchMatrix(matrix, 40));
	}
}
