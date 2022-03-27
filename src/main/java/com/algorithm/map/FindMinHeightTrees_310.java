package com.algorithm.map;

import java.util.*;

/**
 * 对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。
 * <p>
 * 格式
 * <p>
 * 该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
 * <p>
 * 你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， {0, 1}和 {1, 0} 是相同的，因此不会同时出现在 edges 里。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 4, edges = {{1, 0}, {1, 2}, {1, 3}}
 * <p>
 * 0
 * |
 * 1
 * / \
 * 2   3
 * <p>
 * 输出: {1}
 * <p>
 * 示例 2:
 * <p>
 * 输入: n = 6, edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}
 * <p>
 * 0  1  2
 * \ | /
 * 3
 * |
 * 4
 * |
 * 5
 * <p>
 * 输出: {3, 4}
 * <p>
 * 说明:
 * <p>
 * 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。
 * https://leetcode-cn.com/problems/minimum-height-trees/
 *
 * @Author: zzk
 * @Date: 2020-04-15 14:56
 */
public class FindMinHeightTrees_310 {
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> list = null;
        if (n==1)return Arrays.asList(0);
        // 计算节点的度
        int[] degrees = new int[n];
        Arrays.fill(degrees, 0);
        Map<Integer, List<Integer>> neighborMap = new HashMap<>();
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
            for (int i = 0; i < 2; i++) {
                List<Integer> neighbors = neighborMap.get(edge[i]);
                if (neighbors==null){
                    neighborMap.put(edge[i], Arrays.asList(edge[1-i]));
                }else {
                    neighbors = new ArrayList<>(neighbors);
                    neighbors.add(edge[1-i]);
                    neighborMap.put(edge[i],neighbors);
                }
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0;i < n;i++){
            if (degrees[i] == 1){//度数为1，说明是叶子结点,入队列
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            list=new ArrayList<>();
            int size = queue.size();
            for (int i=0;i<size;i++){
                int cur = queue.poll();
                list.add(cur);
                List<Integer> nexts = neighborMap.get(cur);
                for (Integer next:nexts){
                    degrees[next]--;//删除叶子节点后，跟其相邻的节点的度数要减少
                    if (degrees[next] == 1){
                        queue.offer(next);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        List<Integer> integers = findMinHeightTrees(6, edges);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
