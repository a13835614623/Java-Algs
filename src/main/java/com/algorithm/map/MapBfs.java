package com.algorithm.map;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 图的广度优先遍历
 *
 * @Author: zzk
 * @Date: 2020-03-12 09:57
 */
public class MapBfs {
    public static void bfs(int[][] map, int size) {
        //队列
        LinkedList<Integer> list = new LinkedList<>();
        // 已访问过的节点集合
        Set<Integer> accessSet = new HashSet<>();
        list.add(0);
        while (accessSet.size()!=size) {
            // 队头
            Integer head = list.pop();
            // 如果队头未访问过，则访问
            if (!accessSet.contains(head)) {
                accessSet.add(head);
                System.out.println(head);
            }
            // 入队队头元素所有未访问过的邻接点
            for (int i = 0; i < map[head].length; i++) {
                if (map[head][i] != 0&&!accessSet.contains(i))
                    list.add(i);
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
        bfs(map, 4);
    }
}
