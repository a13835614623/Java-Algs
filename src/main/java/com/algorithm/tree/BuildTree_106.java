package com.algorithm.tree;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * <p>
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @Description:从中序与后序遍历序列构造二叉树
 * @Author: zzk
 * @Date: 2019-04-27 10:08
 */
public class BuildTree_106 {
    /**
     * 从前序与中序遍历序列构造二叉树
     *
     * @param inorder 中序序列
     * @param postorder  后序序列
     * @return
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;
        if (inorder.length == 0 || postorder.length == 0) return null;
        TreeNode treeNode = build(inorder, postorder, 0, postorder.length, postorder.length);
        return treeNode;
    }

    public static TreeNode build(int[] inorder, int[] postorder, int inStart, int postEnd, int length) {
        if (length == 0) return null;
        int root = postorder[postEnd-1];
        TreeNode treeNode = new TreeNode(root);
        if (length == 1) return treeNode;
        for (int i = length-1; i >=0; i--) {
            if (root == inorder[inStart + i]) {
                treeNode.left=build(inorder, postorder,inStart,postEnd-length+i,i);
                treeNode.right=build(inorder, postorder,inStart+i+1,postEnd-1,length-1-i);
                return treeNode;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] inorder  = {1,2};
        int[] postorder  = {2,1};
        System.out.println(buildTree(inorder, postorder));
    }
}
