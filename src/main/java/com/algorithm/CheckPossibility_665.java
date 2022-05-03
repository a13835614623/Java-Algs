package com.algorithm;

/**
 * @description CheckPossibility_665
 * 给你一个长度为 n 的整数数组 nums ，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 104
 * -105 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/03
 */
public class CheckPossibility_665 {

    public static
    class Solution {
        public boolean checkPossibility(int[] nums) {
            int length = nums.length;
            if (length <= 2) {
                return true;
            }
            int reverseCount = 0;
            for (int i = 1; i < length; i++) {
                if (nums[i] < nums[i - 1]) {
                    // i-1之前必为升序,i之后必须为升序
                    // 修改i-1
                    if (i == 1 || nums[i - 2] <= nums[i]) {
                        reverseCount++;
                    } else if (i + 1 == length || nums[i - 1] <= nums[i + 1]) {
                        // 修改i
                        reverseCount++;
                    } else {
                        return false;
                    }
                    if (reverseCount > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkPossibility(new int[]{4, 2, 3}));
        System.out.println(solution.checkPossibility(new int[]{3, 4, 2, 3}));
    }
}
