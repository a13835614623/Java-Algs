package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board = [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
 * <p>
 * 给定 word = "ABCCED", 返回 true. 给定 word = "SEE", 返回 true. 给定 word = "ABCB", 返回
 * false.
 *
 * @author zzk
 * @description: 单词搜索
 * @className Exist_79.java
 * @date 2019年4月21日 上午10:01:59
 */
public class Exist_79 {
    public static void printArray2(boolean[][] arr) {
        System.out.println("-----------------------------");
        for (int i=1;i<arr.length-1;i++){
            for (int j=1;j<arr[0].length-1;j++){
                System.out.printf((arr[i][j] ? "√" : "X" )+ " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
    public static boolean exist(char[][] board, String word) {
        if (board == null || "".equals(word) || board.length == 0 || board[0].length == 0)
            return false;
        boolean isVisited[][] = new boolean[board.length][board[0].length];
        char[] chs = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (chs[0] == board[i][j]) {
                    if (find(board, i, j, isVisited, chs, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean find(char[][] board, int i, int j, boolean isVisited[][], char chs[], int index) {
        if (!isVisited[i][j] && chs[index] == board[i][j]) {
            int lenRow = board.length, lenCol = board[0].length;
            isVisited[i][j] = true;
            if (index == chs.length - 1) {
                return true;
            }
            if (j>0&&find(board, i, j - 1, isVisited, chs, index + 1)) {//左
                return true;
            }
            if (j<lenCol-1&&find(board, i, j + 1, isVisited, chs, index + 1)) {//右
                return true;
            }
            if (i>0&&find(board, i - 1, j, isVisited, chs, index + 1)) {//上
                return true;
            }
            if (i<lenRow-1&&find(board, i + 1, j, isVisited, chs, index + 1)) {//下
                return true;
            }
            isVisited[i][j] = false;
        }
        return false;
    }

	public static void main(String[] args) {
		char[][] board = { { 'C', 'A', 'A' }, { 'A', 'A', 'A' }, { 'B', 'C', 'D' } };
        char[][] board2 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'E', 'S' }, { 'A', 'D', 'E', 'E' } };
        char[][] board3 = { { 'a', 'a', 'a', 'a' }, { 'a', 'a', 'a', 'a' }, { 'a', 'a', 'a', 'a' } };
//        System.out.println(exist(board3, "aaaaaaaaaaaa"));
        System.out.println(exist(board2, "ABCESEEED"));
	}

}
