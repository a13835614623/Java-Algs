package com.algorithm.array;

import java.util.Arrays;
import java.util.List;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4] 输出: 2 解释: 跳到最后一个位置的最小跳跃数是 2。 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3
 * 步到达数组的最后一个位置。
 *
 * @description: 跳跃游戏 II
 * @author zzk
 * @className Jump_45.java
 * @date 2019年4月9日 下午4:59:40
 */
public class Jump_45 {
    /**
     * 动态规划
     *
     * @author zzk
     * @date 2019年4月11日 下午7:29:49
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int len = 0;
        if (nums == null || (len = nums.length) == 1)
            return 0;
        if (nums[0] >= len - 1)
            return 1;
        int[] minJumps = new int[nums.length];
        minJumps[1] = 1;
        int result = calcJump(nums, minJumps, len - 1);
        return result;
    }

    public static int calcJump(int[] nums, int[] minJumps, int end) {
        if (nums[0] >= end)
            return 1;
        int minJumpCount = end;
        boolean isCanJumpLast = false;
        for (int i = end - 1; i > 0; i--) {
            if (i + nums[i] >= end) {// 当前索引可以到达末尾
                isCanJumpLast = true;
                if (minJumps[i] == 0) {
                    minJumps[i] = calcJump(nums, minJumps, i);
                }
                if (minJumps[i] + 1 < minJumpCount) {
                    minJumpCount = minJumps[i] + 1;
                    if (minJumpCount == 1)
                        return minJumpCount;
                }
            }
        }
        if (!isCanJumpLast)
            return nums.length;
        return minJumpCount;
    }

    /**
     * 解题思路：
     *
     * 这题是之前那道Jump Game 跳跃游戏
     * 的延伸，那题是问能不能到达最后一个数字，而此题只让我们求到达最后一个位置的最少跳跃数，貌似是默认一定能到达最后位置的?
     * 此题的核心方法是利用贪婪算法Greedy的思想来解，想想为什么呢？
     * 为了较快的跳到末尾，我们想知道每一步能跳的范围，这里贪婪并不是要在能跳的范围中选跳力最远的那个位置，
     * 因为这样选下来不一定是最优解，这么一说感觉又有点不像贪婪算法了。我们这里贪的是一个能到达的最远范围，
     * 我们遍历当前跳跃能到的所有位置，然后根据该位置上的跳力来预测下一步能跳到的最远距离，
     * 贪出一个最远的范围，一旦当这个范围到达末尾时，当前所用的步数一定是最小步数
     *
     * @author zzk
     * @date 2019年4月11日 下午7:44:48
     * @param nums
     * @return
     */
    public static int jump2(int[] nums) {
        if (nums.length == 1)
            return 0;
        int reach = 0;
        int maxReach = nums[0];// 当前能到达的最远的索引
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            maxReach = Math.max(i + nums[i], maxReach);
            System.out.println("reach:" + reach + ",maxReach:" + maxReach);
            if (maxReach >= nums.length - 1)
                return (step + 1);
            if (i == reach) {
                step++;
                reach = maxReach;
            }
        }
        return step;
    }


    public static int jump3(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] + j >= i) {
                    if (dp[i] == 0 || dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }
        return dp[len - 1];
    }


    public static int jump4(int[] nums) {
        int len = nums.length;
        int count = 0;
        if(len ==1){
            return 0;
        }
        for (int i = 0; i < len; ) {
            int jump = nums[i] + i;
            if (jump >= len-1) {
                return count+1;
            }
            int maxJump = jump;
            for (int j = i + 1; j <= jump; j++) {
                if (nums[j] + j > maxJump) {
                    maxJump = nums[j] + j;
                    i = j;
                }
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int nums[] = {3, 1, 1, 1, 4, 5, 6};
        System.out.println(jump2(nums));
        System.out.println(jump3(nums));
    }
}
