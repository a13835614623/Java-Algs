package com.algorithm.map;

import java.util.Arrays;
import java.util.Stack;

/**
 * 迪杰斯特拉算法
 * 单源最短路径算法:用于计算一个节点到另一个节点的最短路径
 *
 * @Author: zzk
 * @Date: 2020-03-12 10:19
 */
public class DijkstraAlg {
    /**
     * 迪杰斯特拉算法
     *
     * @param map 图
     * @param start   起点
     * @param end   终点
     * @return 最短路径
     */
    public static int dijkstra(int[][] map, int start, int end) {
        int size = map.length;
        // min[i]表示start节点到i节点的最短距离
        int[] min = new int[size];
        // 默认到所有节点都是无穷大
        Arrays.fill(min, Integer.MAX_VALUE);
        min[start] = 0;
        int cur = start;
        while (cur != end) {
            // 当前节点的邻接点集合
            int[] neighbors = map[cur];
            int tmpMin = Integer.MAX_VALUE,minI=0;
            // 遍历邻接点集合，选择距离最短的节点作为下一次遍历的起点
            for (int i = 0; i < size; i++) {
                if (neighbors[i] == Integer.MAX_VALUE||i==cur) continue;
                min[i] =Math.min(min[i], min[cur] + neighbors[i]);
                if (neighbors[i] < tmpMin) {
                    tmpMin = neighbors[i];
                    minI = i;
                }
            }
            cur=minI;
        }
        return min[end];
    }

    public static void main(String[] args) {
        int m = Integer.MAX_VALUE;
        int map[][] = {
                {0, 5, 2, 6, m},
                {m, 0, m, m, 1},
                {m, 1, 0, 3, 5},
                {m, m, m, 0, 2},
                {m, m, m, m, m}
        };
        int res=dijkstra(map, 0, 4);
        System.out.println(res);
    }
}
