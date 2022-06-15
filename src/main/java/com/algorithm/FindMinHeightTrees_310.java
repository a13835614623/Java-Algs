package com.algorithm;

import java.util.*;

/**
 * @description FindMinHeightTrees_310
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 *
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 *
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 *
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 *
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 * 示例 2：
 *
 *
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-height-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/29
 */
public class FindMinHeightTrees_310 {

    public static class Node {
        int node;
        int count;

        public Node(int node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 2) {
            return Arrays.asList(0, 1);
        }
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> neighborMap = new HashMap<>(n);
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            neighborMap.put(i, new ArrayList<>());
            nodes[i] = new Node(i, 0);
        }
        for (int[] edge : edges) {
            List<Integer> ls = neighborMap.get(edge[0]);
            ls.add(edge[1]);
            nodes[edge[0]].count++;
            List<Integer> ls2 = neighborMap.get(edge[1]);
            ls2.add(edge[0]);
            nodes[edge[1]].count++;
        }
        Arrays.sort(nodes, (o1, o2) -> o2.count - o1.count);
        Queue<Integer> queue = new ArrayDeque<>();
        int minHeight = n;
        boolean[] access;
        for (Node node : nodes) {
            if (node.count != 1) {
                continue;
            }
            int nodeIndex = node.node;
            access = new boolean[n];
            queue.add(nodeIndex);
            access[nodeIndex] = true;
            int heightTemp = 0;
            boolean over = false;
            while (!queue.isEmpty()) {
                heightTemp++;
                if (heightTemp > minHeight) {
                    over = true;
                    break;
                }
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    Integer head = queue.poll();
                    List<Integer> integers = neighborMap.get(head);
                    for (Integer neighbor : integers) {
                        if (!access[neighbor]) {
                            queue.add(neighbor);
                            access[neighbor] = true;
                        }
                    }
                }
            }
            if (over) {
                queue.clear();
                continue;
            }
            if (heightTemp == minHeight) {
                list.add(nodeIndex);
            } else if (heightTemp < minHeight) {
                minHeight = heightTemp;
                list.clear();
                list.add(nodeIndex);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> minHeightTrees = findMinHeightTrees(4, new int[][]{
                {1, 0}, {1, 2}, {1, 3}
        });
        System.out.println(minHeightTrees);
    }
}
