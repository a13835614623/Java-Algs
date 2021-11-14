package com.algorithm.tree;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:
 * @Author: zzk
 * @Date: 2019-11-26 20:42
 */
public class NumTrees_96 {
    public static int numTrees(int n) {
        int nums[]=new int[n+1];
        return num(n,nums);
    }
    public static int num(int n,int[] nums) {
        if(n==0)return 1;
        if (n < 3) return nums[n]=n;
        if(nums[n]!=0)return nums[n];
        int result = 0,left=0,right=0;
        for (int i = 1; i <= n; i++) {
            left=num(i-1,nums);
            right=num(n-i,nums);
            result+=left*right;
        }
        return nums[n]=result;
    }
    public static void main(String[] args) {
        System.out.println(numTrees(5));
    }
}
