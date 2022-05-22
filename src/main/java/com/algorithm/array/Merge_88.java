package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
/**
 * @description Merge_88
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * 示例 3：
 *
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *  
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/merge-sorted-array">https://leetcode.cn/problems/merge-sorted-array</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/04
 */
public class Merge_88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int len = m + n;
        while (i < len && j < n) {
            int cmp = nums1[i] - nums2[j];
            if (cmp >= 0) {
                // 第一个数组的元素大于第二个数组的元素
                // 把nums2[j]插入到i位置
                insert(nums1, len, i, nums2[j]);
                j++;
            } else {
                // 第一个数组的元素小于第二个数组的元素
                // 在第一个数组找到第一个大于第二个数组元素的索引k,后移k以及之后的元素
                boolean f = false;
                for (; i < m + j; i++) {
                    if (nums1[i] > nums2[j]) {
                        f = true;
                        // i 及其之后的元素 后移
                        insert(nums1, len, i, nums2[j]);
                        j++;
                        break;
                    }
                }
                if (!f) {
                    break;
                }
            }
        }
        while (j < n) {
            nums1[i++] = nums2[j++];
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int pos = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[pos] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0) {
            nums1[pos--] = nums2[n--];
        }
    }

    public static void insert(int[] nums, int len, int index, int val) {
        for (int k = len - 1; k >= index + 1; k--) {
            nums[k] = nums[k - 1];
        }
        nums[index] = val;
    }


    public static void main(String[] args) {
        int[] nums1 = {4, 0, 0, 0, 0, 0};
        int[] nums2 = {1, 2, 3, 5, 6};
        merge(nums1, 1, nums2, 5);
        ArrayUtil.printArray(nums1);
    }
}
