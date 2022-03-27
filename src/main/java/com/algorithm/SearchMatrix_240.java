package com.algorithm;

/**
 * @description SearchMatrix_240
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/19
 */
public class SearchMatrix_240 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
        };
//        solution.searchMatrix(matrix, 15);
        System.out.println(solution.searchMatrix(matrix, 12));
//        for (int i = 0; i < matrix.length; i++) {
//            for (int matrix1 : matrix[i]) {
//                boolean x = solution.searchMatrix(matrix, matrix1);
//                if (!x) {
//                    System.out.println(matrix1);
//                }
//            }
//        }
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        int row = 0, col = colLen - 1;
        while (row < rowLen && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                --col;
            } else {
                ++row;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int startRow, int endRow, int startCol, int endCol, int target) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int low = 0;
        int high = rowLen - 1;
        int row = 0;
        int col = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[row][mid] <= target) {
                low = mid + 1;
                col = mid;
                if (matrix[row][col] == target) {
                    return true;
                }
                if (row == colLen - 1) {

                }
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    private int searchCol(int[][] matrix, int target, int low, int high, int col) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][col] <= target) {
                low = mid + 1;
                if (matrix[mid][col] == target) {
                    return mid;
                }
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int searchRow(int[][] matrix, int target, int low, int high, int row) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[row][mid] <= target) {
                low = mid + 1;
                if (matrix[row][mid] == target) {
                    return mid;
                }
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
