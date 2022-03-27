package com.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:
 * @Author: zzk
 * @Date: 2019-11-24 19:28
 */
public class GenerateTrees_95 {
    public static List<TreeNode> generateTrees(int n) {
        if (n == 1) return Arrays.asList(new TreeNode(1));
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) return result;
        // 第一个参数代表基数，第二个参数代表个数
        List[][] treeNodeArrays = new List[n + 1][n + 1];
        return buildTrees(0, n, treeNodeArrays);
    }

    public static List<TreeNode> buildTrees(int base, int n, List[][] treeNodeArrays) {
        if (n == 1) {
            return treeNodeArrays[base][n] = Arrays.asList(new TreeNode(base + 1));
        } else if (n == 0) {
            List<TreeNode> nodes = new ArrayList<TreeNode>();
            nodes.add(null);
            return treeNodeArrays[base][n] = nodes;
        } else {
            if (treeNodeArrays[base][n] != null) return treeNodeArrays[base][n];
            List<TreeNode> result = new ArrayList<>();
            for (int num = 1; num <= n; num++) {
                List<TreeNode> leftList = buildTrees(base, num - 1, treeNodeArrays);
                List<TreeNode> rightList = buildTrees(base + num, n - num, treeNodeArrays);
                for (int l = 0; l < leftList.size(); l++) {
                    for (int r = 0; r < rightList.size(); r++) {
                        TreeNode node = new TreeNode(base + num);
                        node.left = leftList.get(l);
                        node.right = rightList.get(r);
                        result.add(node);
                    }
                }
            }
            return treeNodeArrays[base][n] = result;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        List[][] treeNodeArrays = new List[n + 1][n + 1];
        List<TreeNode> nodes = buildTrees(0, n, treeNodeArrays);
        System.out.println(nodes.size());
    }
}
