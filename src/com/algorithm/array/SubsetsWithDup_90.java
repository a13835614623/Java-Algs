package com.algorithm.array;

import com.algorithm.LengthOfLongestSubstring_3;
import sun.security.provider.Sun;

import java.util.*;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 *
 * @Description:子集 II
 * @Author: zzk
 * @Date: 2019-04-25 18:37
 */
public class SubsetsWithDup_90 {
    public static List<List<Integer>> subListsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> tmpList = null;
        int len = nums.length;
        resultList.add(new ArrayList<>());
        tmpList = new ArrayList<>();
        tmpList.add(nums[len - 1]);
        resultList.add(tmpList);
        int newLen = 1;
        for (int i = len - 2; i >= 0; i--) {
            int size = resultList.size();
            if (nums[i] != nums[i + 1]) {
                newLen = size;
            }
            int j = size - newLen;
            newLen = 0;
            for (; j < size; j++) {
                tmpList = new ArrayList<>(resultList.get(j));
                tmpList.add(nums[i]);
                resultList.add(tmpList);
                newLen++;
            }
        }
        return resultList;
    }

    public static List<List<Integer>> sub(int[] nums, int start) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (start == nums.length - 1) {
            // 添加自身
            List<Integer> list = new ArrayList<>();
            list.add(nums[start]);
            resultList.add(list);
            list = new ArrayList<>();//空集
            resultList.add(list);
            return resultList;
        } else {
            List<List<Integer>> list2 = sub(nums, start + 1);
            resultList.addAll(list2);
            List<Integer> tmpList = null;
            if (nums[start + 1] != nums[start]) {
                for (List<Integer> integers : list2) {
                    tmpList = new ArrayList<>(integers);
                    tmpList.add(nums[start]);
                    resultList.add(tmpList);
                }
            } else {
                int maxZeroCount = 0;
                for (int i = start + 1; i < nums.length; i++) {
                    if (nums[i] == nums[start]) maxZeroCount++;
                }
                for (List<Integer> integers : list2) {
                    int count = 0;
                    for (Integer integer : integers) {
                        if (integer == nums[start]) count++;
                    }
                    if (count == maxZeroCount) {
                        tmpList = new ArrayList<>(integers);
                        tmpList.add(nums[start]);
                        resultList.add(tmpList);
                    }
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 5, 5};
        List<List<Integer>> list = subListsWithDup(nums);
        for (List<Integer> list2 : list) {
            System.out.println(list2);
        }
    }
}
