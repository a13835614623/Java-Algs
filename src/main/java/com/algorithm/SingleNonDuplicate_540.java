package com.algorithm;

/**
 * @description SingleNonDuplicate_540
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/10
 */
public class SingleNonDuplicate_540 {

    public static int singleNonDuplicate(int[] nums) {
        int length = nums.length;
        int l = 0, r = length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            // 如果[mid,r]有偶数个
            if ((r - mid + 1) % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    // 目标数在左边
                    r = mid - 1;
                } else {
                    // 目标数在右边
                    l = mid + 1;
                }
            } else {
                // 如果[mid,r]为奇数个
                if (mid + 1 < length) {
                    if (nums[mid] == nums[mid + 1]) {
                        // 目标数在右边
                        l = mid + 2;
                    } else {
                        // 目标数在左边
                        r = mid;
                    }
                } else {
                    return nums[mid];
                }
            }
        }
        return nums[r];
    }

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
        System.out.println(singleNonDuplicate(new int[]{1, 2, 2}));
        System.out.println(singleNonDuplicate(new int[]{2, 2, 1}));
        System.out.println(singleNonDuplicate(new int[]{2, 2, 1, 3, 3}));
    }
}
