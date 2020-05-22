package com.algorithm.tree;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preOrder = [3,9,20,15,7]
 * 中序遍历 inOrder = [9,3,15,20,7]
 * <p>
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @Description:从前序与中序遍历序列构造二叉树
 * @Author: zzk
 * @Date: 2019-04-27 10:08
 */
public class BuildTree_105 {
    /**
     * 从前序与中序遍历序列构造二叉树
     *
     * @param preOrder 前序序列
     * @param inOrder  中序序列
     * @return
     */
    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null) return null;
        if (preOrder.length == 0 || inOrder.length == 0) return null;
        TreeNode treeNode = build(preOrder, inOrder, 0,0,inOrder.length);
        return treeNode;
    }

    /**
     * @description: 构造二叉树
     * @param: [preOrder, inOrder, preStart, preEnd, inStart, inEnd]
     * @return: com.algorithm.tree.TreeNode
     * @author: zzk
     * @date: 2019-04-27 10:24
     */
    public static TreeNode build(int[] preOrder, int[] inOrder, int preStart, int inStart, int length) {
        if (length==0) return null;
        int root = preOrder[preStart];
        TreeNode treeNode = new TreeNode(root);
        if (length== 1) return treeNode;
        for (int i = length-1; i >=0; i--) {
            if (root == inOrder[inStart+i]) {
                treeNode.left = build(preOrder, inOrder, preStart+1,inStart,i);
                treeNode.right = build(preOrder, inOrder, preStart+1+i,inStart+i+1,length-i-1);
                return treeNode;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        System.out.println(buildTree(preOrder, inOrder));
    }
}
