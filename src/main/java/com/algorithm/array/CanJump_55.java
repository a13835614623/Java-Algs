package com.algorithm.array;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 判断你是否能够到达最后一个位置。
 * 
 * 示例 1:
 * 
 * 输入: [2,3,1,1,4] 输出: true 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 
 * 示例 2:
 * 
 * 输入: [3,2,1,0,4] 输出: false 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ，
 * 所以你永远不可能到达最后一个位置。
 * 
 * 
 * @description: 跳跃游戏
 * @author zzk
 * @className CanJump_55.java
 * @date 2019年4月9日 下午4:11:44
 */
public class CanJump_55 {
	/**
	 * 记录之前能到达的最远距离和当前能到达的最远距离进行比较，更新最远距离，如果
	 * @author zzk
	 * @date 2019年4月9日 下午4:36:56
	 * @param nums
	 * @return
	 */
	public static boolean canJump(int[] nums) {
		int N = nums.length;
        int maxreach = 0; //注意是下标值，而不是元素值
        for(int i = 0; i !=N;++i)
        {
            if(i > maxreach) //注意false的条件，就是maxreach停止了，而i仍然在增加，一直到超过maxreach也没有停止，对应题目中的反例很好理解
                return false;
            maxreach = Math.max(maxreach, i + nums[i]);  //注意更新方法
            if (maxreach >= N-1)  //一个小细节，要写成>=而不是写成==
                return true;
        }
		return false;
	}

	public static void main(String[] args) {
		int nums[]= {3,2,1,0,4};
		canJump(nums);
 	}
}
