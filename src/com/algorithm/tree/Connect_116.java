package com.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * <p>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:填充每个节点的下一个右侧节点指针
 * @Author: zzk
 * @Date: 2019-12-08 13:42
 */
public class Connect_116 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static Node connect(Node root) {
        if (root == null || root.left == null) return root;
        Node left = root.left, right = root.right;
        connect(left);
        connect(right);
        while (left!=null){
            left.next=right;
            left=left.right;
            right=right.left;
        }
        return root;
    }

    public static Node connect2(Node root) {
        if (root == null) return null;
        func(Arrays.asList(root));
        return root;
    }

    public static void func(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0) return;
        List<Node> childNodes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (node.left == null) continue;
            childNodes.add(node.left);
            childNodes.add(node.right);
        }
        for (int i = 0, len = childNodes.size(); i < len - 1; i++) {
            childNodes.get(i).next = childNodes.get(i + 1);
        }
        func(childNodes);
    }


    public static void main(String[] args) {
        Node node = new Node(5);
        node.left = new Node(1);
        node.left.left = new Node(2);
        node.left.right = new Node(3);
        node.right = new Node(6);
        node.right.left = new Node(4);
        node.right.right = new Node(7);
    }
}
