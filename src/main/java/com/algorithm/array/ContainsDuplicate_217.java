package com.algorithm.array;

import java.util.*;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 *
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * @Description:存在重复元素
 * @Author: zzk
 * @Date: 2019-05-28 20:00
 */
public class ContainsDuplicate_217 {
    public static boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 1; i < nums.length; i++) {
            if (map.get(i) != null) return true;
            map.put(i, nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
