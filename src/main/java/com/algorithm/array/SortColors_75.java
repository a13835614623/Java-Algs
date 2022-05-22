package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意: 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0] 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *
 * @description: 颜色分类
 * @author zzk
 * @className SortColors_75.java
 * @date 2019年4月19日 上午10:01:10
 */
public class SortColors_75 {
    public static void sortColors2(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                swap(nums,i,ptr++);
            }
        }
        ArrayUtil.printArray(nums);
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                swap(nums,i,ptr++);
            }
        }
    }

    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int len = nums.length, next0 = 0, next2 = len - 1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0 && next0 < i) {
                swap(nums, i--, next0++);
            } else if (nums[i] == 2 && next2 > i) {
                swap(nums, i--, next2--);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
//        System.out.println("交换" + i + "和" + j);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 0, 2, 1, 1, 0};
        int[] nums2 = {2, 0, 1};
        sortColors2(nums);
        ArrayUtil.printArray(nums);
    }
}
