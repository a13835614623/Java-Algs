package com.algorithm.array;

import java.util.Arrays;

/**
 * 给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。相反，如果是负数 (-k)，则向后移动 k 个索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。
 * <p>
 * 确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 > 1。此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[-1,2]
 * 输出：false
 * 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * <p>
 * 示例 3:
 * <p>
 * 输入：[-2,1,-1,-2,-2]
 * 输出：false
 * 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * -1000 ≤ nums[i] ≤ 1000
 * nums[i] ≠ 0
 * 0 ≤ nums.length ≤ 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/circular-array-loop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zzk
 * @Date: 2020-04-17 20:46
 */
public class CircularArrayLoop_457 {
    public static boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            nums[i] = nums[i] % len;
        }
        boolean[] caculs = new boolean[len];
        Arrays.fill(caculs, false);
        for (int i = 0; i < nums.length; i++) {
            if (caculs[i]) continue;
            boolean[] visited = new boolean[len];
            Arrays.fill(visited, false);
            int num = nums[i];
            int index = i;
            boolean isUp = num > 0;
            int forNum = 0;
            while (!visited[index]) {
                visited[index] = true;
                // 违反方向
                if (nums[index] < 0 && isUp || nums[index] > 0 && !isUp) break;
                if (index + nums[index] < 0) {
                    index += (len + nums[index]);
                } else {
                    index += nums[index] % len;
                }
                index %= len;
                forNum++;
            }
            // 出现循环
            if (index == i) {
                if (forNum > 1)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2};
        System.out.println(circularArrayLoop(nums));
    }
}
