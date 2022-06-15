package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description SolveSudoku_37
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 *
 *  
 *
 * 提示：
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/22
 */
public class SolveSudoku_37 {

    static class Node {
        int row;
        int col;
        /**
         * 格子
         */
        int grid;
        List<Integer> list;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            grid = grid(row, col);
        }

        public Node(int row, int col, List<Integer> list) {
            this(row, col);
            this.list = list;
        }

        public static Node copy(Node node) {
            return new Node(node.row, node.col, new ArrayList<>(node.list));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return row == node.row && col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    ", grid=" + grid +
                    ", list=" + list +
                    '}';
        }
    }

    private static int grid(int row, int col) {
        return row / 3 * 3 + col / 3;
    }

    static boolean[][] rowSelect = new boolean[9][9];
    static boolean[][] colSelect = new boolean[9][9];
    static boolean[][][] gridSelect = new boolean[3][3][9];

    public static void solveSudoku2(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    rowSelect[i][num] = true;
                    colSelect[j][num] = true;
                    gridSelect[i / 3][j / 3][num] = true;
                }
            }
        }
        dfs(board, 0, 0);
    }

    private static boolean dfs(char[][] board, int i, int j) {
        if (j >= 9) {
            return dfs(board, i + 1, 0);
        }
        if (i >= 9) {
            return true;
        }
        char c = board[i][j];
        if (c != '.') {
            return dfs(board, i, j + 1);
        }
        for (int num = 0; num < 9; num++) {
            // 行 列 格子 都没有被选择
            if (!rowSelect[i][num] && !colSelect[j][num] && !gridSelect[i / 3][j / 3][num]) {
                // 选择这个值
                rowSelect[i][num] = true;
                colSelect[j][num] = true;
                gridSelect[i / 3][j / 3][num] = true;
                board[i][j] = (char) ('1' + num);
                if (dfs(board, i, j + 1)) {
                    return true;
                }
                // 回退
                rowSelect[i][num] = false;
                colSelect[j][num] = false;
                gridSelect[i / 3][j / 3][num] = false;
                board[i][j] = '.';
            }
        }
        return false;
    }

    public static void solveSudoku(char[][] board) {
        int rowLen = board.length;
        int colLen = board[0].length;
        // 存储每个节点可选的数字
        Map<Node, Node> map = new HashMap<>();
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                // 如果是数字,则更新可能会影响的行列和子宫格的可选值
                char c = board[row][col];
                if (c != '.') {
                    int num = c - '0';
                    // 遍历所在行
                    for (int j = 0; j < colLen; j++) {
                        if (board[row][j] == '.') {
                            put(map, num, row, j);
                        }
                    }
                    // 遍历所在列
                    for (int i = 0; i < colLen; i++) {
                        if (board[i][col] == '.') {
                            put(map, num, i, col);
                        }
                    }
                    // 遍历所在宫格
                    int baseRow = (row / 3) * 3;
                    int baseCol = (col / 3) * 3;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (board[baseRow + i][baseCol + j] == '.') {
                                put(map, num, baseRow + i, baseCol + j);
                            }
                        }
                    }
                }
            }
        }
        Set<Node> keySet = map.keySet();
        for (Node key : keySet) {
            map.computeIfPresent(key, (k, vNode) -> {
                List<Integer> newList = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    if (!vNode.list.contains(i)) {
                        newList.add(i);
                    }
                }
                vNode.list = newList;
                return vNode;
            });
        }
        if (dfs(new ArrayList<>(keySet), board, 0)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    private static void put(Map<Node, Node> map, int num, int row, int col) {
        Node node = new Node(row, col, new ArrayList<>());
        Node valNode = map.get(node);
        if (valNode == null) {
            map.put(node, node);
        } else {
            valNode.list.add(num);
        }
    }


    private static boolean dfs(List<Node> nodes, char[][] board, int index) {
        if (index == nodes.size()) {
            return true;
        }
        Node node = nodes.get(index);
        int grid = node.grid;
        int row = node.row;
        int col = node.col;
        List<Integer> list = node.list;
        char oldChar = board[row][col];
        // 遍历当前位置的可选值
        for (Integer val : list) {
            // 选中当前值
            board[row][col] = (char) ('0' + val);
            List<Node> oldNodes = nodes.stream().map(Node::copy).collect(Collectors.toList());
            // 更新其他格的可选值,移除当前所选值
            for (Node otherNode : oldNodes) {
                if (otherNode.equals(node)) {
                    continue;
                }
                // 如果在同一行/同一列/同一个宫格
                if (otherNode.row == row || otherNode.col == col || otherNode.grid == grid) {
                    otherNode.list.remove(val);
                }
            }
            // 深度遍历
            if (dfs(oldNodes, board, index + 1)) {
                return true;
            }
            // 回退状态
            board[row][col] = oldChar;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku2(board);
        ArrayUtil.printArray2(board);
    }
}
