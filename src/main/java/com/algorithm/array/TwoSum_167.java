package com.algorithm.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 *     返回的下标值（index1 和 index2）不是从零开始的。
 *     你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * @description: 两数之和 II - 输入有序数组
 * @className: TwoSum_167
 * @author: zzk
 * @date: 2019-05-06 20:35
 */
public class TwoSum_167 {
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int cmp = nums[left] + nums[right] - target;
            if (cmp > 0) {
                right--;
            } else if (cmp == 0) {
                return new int[]{left + 1, right + 1};
            } else {
                left++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 6, 7};
        int[] a = twoSum(arr, 10);
        System.out.println(a[0] + " " + a[1]);
    }
}
