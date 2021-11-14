package com.algorithm;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @Description:乘积最大子序列
 * @Author: zzk
 * @Date: 2019-05-03 20:02
 */
public class MaxProduct_152 {
    public static int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1; //一个保存最大的，一个保存最小的。
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            } //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }

    public static int maxProduct2(int[] nums) {
        int product = 1,max = nums[0],len=nums.length;//乘积
        for (int i = 0; i <len; i++) {//左边
            product = product * nums[i];
            if (max < product) max = product;
            if (nums[i] == 0) product = 1;
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {//右边
            product = product * nums[i];
            if (max < product) max = product;
            if (nums[i] == 0) product = 1;
        }
        return max;
    }

    public static void main(String[] args) {
        int nums[] = {2, 3, -2, 4, -5};
        int nums2[] = {-2, 0, -1};
        System.out.println(maxProduct2(nums));
    }
}
