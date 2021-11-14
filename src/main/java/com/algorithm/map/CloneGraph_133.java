package com.algorithm.map;

import java.util.*;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * <p>
 * 测试用例格式：
 * <p>
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1，第二个节点值为 2，以此类推。该图在测试用例中使用邻接列表表示。
 * <p>
 * 邻接列表是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * <p>
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 * 提示：
 * <p>
 * 节点数介于 1 到 100 之间。
 * 每个节点值都是唯一的。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * https://leetcode-cn.com/problems/clone-graph/
 *
 * @Author: zzk
 * @Date: 2020-03-19 09:18
 */
public class CloneGraph_133 {
    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        Node res = new Node(node.val);
        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> visited = new HashMap<>();
        stack.add(node);
        visited.put(node, res);
        Node cur;
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            cur = visited.get(top);
            List<Node> neighbors = top.neighbors;
            for (Node neighbor : neighbors) {
                Node copyNeighbor = visited.get(neighbor);
                if (copyNeighbor == null) {
                    stack.push(neighbor);
                    Node tmp = new Node(neighbor.val);
                    cur.neighbors.add(tmp);
                    visited.put(neighbor, tmp);
                } else {
                    cur.neighbors.add(copyNeighbor);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}