package com.algorithm.tree;

/**
 * @Description:
 * @Author: zzk
 * @Date: 2019-12-11 10:34
 */
public class CountNodes_222 {
    /**
     * 暴力解法
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        if (root==null)return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 标准解法
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
    public static void main(String[] args) {

    }
}
