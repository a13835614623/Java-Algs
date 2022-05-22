package com.algorithm;

/**
 * @description FindMin_154
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须尽可能减少整个过程的操作步骤。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/10
 */
public class FindMin_154 {

    public static int findMin(int[] nums) {
        int l = 0, r = nums.length;
        int startNum = nums[0];
        int min = startNum;
        while (l < r) {
            int mid = (l + r) >> 1;
            // 说明左边是增序
            int midNum = nums[mid];
            if (midNum > startNum) {
                l = mid + 1;
            } else if (midNum < startNum){
                // 说明右边是增序
                r = mid;
                min = Math.min(min, midNum);
            }else {
                min = Math.min(min, nums[l]);
                l++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4,5,6,7,0,1,4}));
        System.out.println(findMin(new int[]{4,3}));
        System.out.println(findMin(new int[]{3,1,3,3}));
        System.out.println(findMin(new int[]{2,2,2,0,1}));
    }
}
