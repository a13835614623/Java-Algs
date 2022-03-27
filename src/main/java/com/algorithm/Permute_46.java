package com.algorithm;

import java.util.*;

/**
 * @description Permute_46
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/12
 */
public class Permute_46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        int length = nums.length;
        if (length == 1) {
            lists.add(new ArrayList<>(Collections.singleton(nums[0])));
            return lists;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[length];
        dfs(nums, length, lists, path, used);
        return lists;
    }

    private void dfs(int[] nums, int length, List<List<Integer>> lists, Deque<Integer> path, boolean[] used) {
        if (path.size() == length) {
            lists.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.push(nums[i]);
            dfs(nums, length, lists, path, used);
            used[i] = false;
            path.pop();
        }
    }

    public static void main(String[] args) {
        Permute_46 x = new Permute_46();
        System.out.println(x.permute(new int[]{1, 2, 3}));
    }
}
