package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.util.*;

/**
 * @description Permute_46
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/12
 */
public class Permute_47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length == 1) {
            lists.add(new ArrayList<>(Collections.singleton(nums[0])));
            return lists;
        }
        int[] helpArr = new int[nums.length - 1];
        Set<Integer> hasSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0, k = 0; j < nums.length; ) {
                if (j == i) {
                    j++;
                } else {
                    helpArr[k++] = nums[j++];
                }
            }
            if (hasSet.contains(nums[i])) {
                continue;
            }
            hasSet.add(nums[i]);
            List<List<Integer>> permute = permuteUnique(helpArr);
            for (List<Integer> integers : permute) {
                integers.add(nums[i]);
            }
            lists.addAll(permute);
        }
        return lists;
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        int length = nums.length;
        if (length == 1) {
            lists.add(new ArrayList<>(Collections.singleton(nums[0])));
            return lists;
        }
        Arrays.sort(nums);
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
        for (int i = 0; i < length; i++) {
            if (used[i] || i >0 && nums[i] == nums[i - 1] && !used[i - 1]) {
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
        Permute_47 x = new Permute_47();
        System.out.println(x.permute(new int[]{1, 1, 2}));
    }
}
