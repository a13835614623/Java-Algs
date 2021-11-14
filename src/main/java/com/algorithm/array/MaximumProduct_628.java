package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * <p>
 * 注意:
 * <p>
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * @Author: zzk
 * @Date: 2020-04-20 17:01
 */
public class MaximumProduct_628 {

    public static int maximumProduct(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        if (nums[1] < 0) {
            return nums[len - 1] *Math.max(nums[0] * nums[1],nums[len - 2] * nums[len - 3]);
        }else {
            return nums[len - 1] * nums[len - 2] * nums[len - 3];
        }
    }

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[] {1,2,3,1,6}));
        System.out.println(maximumProduct(new int[] {-1,0,3,1,6}));
        System.out.println(maximumProduct(new int[] {-1,-2,0,1,6}));
    }

}