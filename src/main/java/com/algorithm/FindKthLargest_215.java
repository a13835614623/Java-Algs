package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * @description FindKthLargest_215
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/15
 */
public class FindKthLargest_215 {
    private static final Random random = new Random();

    public static int findKthLargest2(int[] nums, int k) {
        int length = nums.length;

        return quickSort(nums, length - k, 0, length - 1);
    }

    /**
     * @description 返回排序完成后枢纽的索引
     * @param nums
     * @param pivot 枢纽值
     * @param l
     * @param r
     * @return int 排序完成后枢纽的索引
     * @author 张子宽
     * @date 2022/05/15
     */
    private static int quickSort(int[] nums, int index, int l, int r) {
        int pivotIndex = l + random.nextInt(r - l + 1);
        int pivot = nums[pivotIndex];
        nums[pivotIndex] = nums[l];
        int min = l;
        int max = r;
        while (l < r) {
            // 从右边边找找比枢纽小的
            while (l < r && nums[r] >= pivot) {
                r--;
            }
            if (l < r) {
                // 放到枢纽的左边
                nums[l++] = nums[r];
            }
            // 从左边找找比枢纽大的
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            if (l < r) {
                // 放到枢纽的右边
                nums[r--] = nums[l];
            }
        }
        // 重要
        nums[l] = pivot;
        if (l == index) {
            return nums[l];
        } else if (l > index) {
            // 枢纽在右边
            return quickSort(nums, index, min, l - 1);
        } else {
            // 枢纽在左边
            return quickSort(nums, index, l + 1, max);
        }
    }

    private static int quickSort3(int[] nums, int k, int left, int right) {
        int index = random.nextInt(right - left + 1) + left;
        int flag = nums[index];
        nums[index] = nums[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] <= flag) j--;
            nums[i] = nums[j];
            while (i < j && nums[i] >= flag) i++;
            nums[j] = nums[i];
        }
        nums[i] = flag;
        if (i == k - 1) return nums[i];
        else if (i < k - 1) return quickSort3(nums, k, i + 1, right);
        else return quickSort3(nums, k, left, i - 1);
    }

    public static void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
//        System.out.println(findKthLargest2(new int[]{3, 2, 1, 5, 6, 4}, 2));
        for (int i = 0; i < 10; i++) {
            System.out.println(findKthLargest2(new int[]{5, 4, 3, 2, 1}, 2));
        }
    }
}
