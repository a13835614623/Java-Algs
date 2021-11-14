package com.algorithm.array;

/*
	给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
	
	示例:
	
	输入: [0,1,0,3,12]
	输出: [1,3,12,0,0]
 */
public class MoveZeroes_283 {

	public void moveZeroes(int[] nums) {
		for (int i = 1, len = nums.length; i < len; i++) {
			if (nums[i - 1] == 0 && nums[i] != 0) {
				int temp = nums[i];
				nums[i] = nums[i - 1];
				nums[i - 1] = temp;
				i--;
				if (i - 1 >= 0)
					i--;
			}
			System.out.print(i + 1 + " ");
			for (int j : nums) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] arr = { 0, 2, 0, 8, 9, 0, 0 };
		new MoveZeroes_283().moveZeroes(arr);
	}
}
