package com.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @description Combine_77
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/13
 */
public class Combine_77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();

        Deque<Integer> path = new ArrayDeque<>(k);
        int max = n - k + 1;
        for (int i = 1; i <= max; i++) {
            path.push(i);
            dfs(path, lists, n, k, i + 1);
            path.pop();
        }
        return lists;

    }

    private void dfs(Deque<Integer> path, List<List<Integer>> lists, int n, int k, int start) {
        if (path.size() == k) {
            lists.add(new ArrayList<>(path));
            return;
        }
        if (path.size() + n - start + 1 < k) {
            return;
        }
        for (int i = start; i <= n; i++) {
            path.push(i);
            dfs(path, lists, n, k, i + 1);
            path.pop();
        }
    }

    public static void main(String[] args) {
        Combine_77 combine_77 = new Combine_77();
        System.out.println(combine_77.combine(4, 2));
    }
}
