package com.algorithm.map;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历
 * @Author: zzk
 * @Date: 2020-03-12 09:39
 */
public class MapDfs {
    /**
     * 图的深度优先遍历
     *
     * @param map  图
     * @param size 节点数量
     */
    public static void dfs(int[][] map, int size) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> accessSet = new HashSet<>();
        stack.push(0);
        while (accessSet.size() != size) {
            Integer top = stack.pop();
            // 如果栈顶元素没被访问过，则访问
            if (!accessSet.contains(top)) {
                accessSet.add(top);
                System.out.println(top);
            }
            // 获取栈顶元素的邻接点
            int[] nodes = map[top];
            // 查找第一个没被访问过的邻接点,入栈
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != 0&&!accessSet.contains(i)){
                    stack.push(i);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int map[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 1, 1, 0}
        };
        dfs(map,4);
    }
}
