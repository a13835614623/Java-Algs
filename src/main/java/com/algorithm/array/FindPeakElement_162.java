package com.algorithm.array;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 *
 * @Description:寻找峰值
 * @Author: zzk
 * @Date: 2019-05-05 15:24
 */
public class FindPeakElement_162 {
    /**
     * 暴力法
     *
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums[0] > nums[1]) return 0;
        int len = nums.length;
        for (int i = 1; i < len - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        if (nums[len - 1] > nums[len - 2]) return len - 1;
        return 0;
    }

    public static int findPeakElement2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums[0] > nums[1]) return 0;
        int right = nums.length - 1, mid = right >> 1, left = 0;
        if (nums[right] > nums[right - 1]) return right;
        while (left <= right) {
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
            if (nums[mid] < nums[left]) {//左半部分有波峰
                right = mid - 1;
            } else if (nums[mid] < nums[right]) {//右半部分有波峰
                left = mid + 1;
            }else{
                left++;
            }
            mid = (left + right) >> 1;
        }
        return 0;
    }
    public static int findPeakElement3(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int right = nums.length-1, mid = right >> 1, left = 0;
        while (left < right) {
            mid = (left+right) >> 1;
            if (nums[mid] > nums[mid+1]) {//左半部分有波峰
                right = mid;
            } else{//右半部分有波峰
                left = mid + 1;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement3(nums));
    }
}
