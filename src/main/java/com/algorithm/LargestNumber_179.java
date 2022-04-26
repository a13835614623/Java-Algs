package com.algorithm;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @description LargestNumber_179
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/28
 */
public class LargestNumber_179 {

    public static class Solution {
        public String largestNumber(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (compare(nums[i], nums[j]) < 0) {
                        swap(nums, i, j);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < len-1 && nums[i] == 0) {
                i++;
            }
            for (; i < len; i++) {
                sb.append(nums[i]);
            }
            return sb.toString();
        }

        public static int compare(int i, int j) {
            String a = Integer.toString(i) + j, b = j + Integer.toString(i);
            return a.compareTo(b);
        }

        public static void swap(int[] nums, int a, int b) {
            int c = nums[a];
            nums[a] = nums[b];
            nums[b] = c;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(solution.largestNumber(new int[]{10, 3, 2, 5}));
        System.out.println(solution.largestNumber(new int[]{67, 6, 7}));
        System.out.println(solution.largestNumber(new int[]{34323, 3432}));
        System.out.println(solution.largestNumber(new int[]{3432, 34323}));
        System.out.println(solution.largestNumber(new int[]{0, 0}));
    }
}
