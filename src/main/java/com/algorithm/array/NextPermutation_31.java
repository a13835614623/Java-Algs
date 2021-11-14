package com.algorithm.array;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 必须原地修改，只允许使用额外常数空间。
 * 
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 1,2,3 → 1,3,2 3,2,1 → 1,2,3 1,1,5 → 1,5,1
 * 
 * @author zzk
 *
 */
public class NextPermutation_31 {
	public static void nextPermutation(int[] nums) {
		int len = nums.length;
		int i = len - 1;
		while (i > 0) {// 往前扫描
			if (nums[i] > nums[i - 1]) {// 遇到逆序
				int j = i;
				while (j < len) {// 往后扫描
					if (nums[j] > nums[i - 1]) {
						boolean isShouldSwap = j < len - 1 && nums[j + 1] <= nums[i - 1];//
						boolean isLast = j == len - 1;// 到最后一个元素
						if (isShouldSwap || isLast) {
							while (nums[j] == nums[i - 1]) {// 如果交换双方相等
								System.out.println(121212);
								j--;
							}
							System.out.print("交换"+nums[j]+"和"+nums[i-1]+":");
							swap(nums, j, i-1);//交换
							j = i;
							int num = (len - 1 + i);// 首尾索引和
							while (j <= num / 2) {//逆序j及其以后的数
								swap(nums, j, num-j);
								j++;
							}
							return;
						}
					}
					j++;
				}
				break;
			}
			i--;
		}
		if (i == 0) {
			System.out.print("不交换:");
			for (i = 0; i < len / 2; i++) {
				swap(nums, i, len - 1 - i);

			}
		}
	}
	public static void swap(int[] nums,int i,int j){
		int temp = nums[j];
		nums[j] = nums[i];
		nums[i] = temp;
	}
	public static void main(String[] args) {
		int numss[][]={
				{ 1, 2, 3, 4, 5 },
				{ 5, 4, 3, 2, 1 },
				{ 5, 7, 6, 8, 7, 5, 1 },
				{ 5, 7, 6, 8, 5, 1 },
				{ 1, 3, 2 },
				{ 1, 2, 3 },
				{ 1, 1, 5 },
				{ 1, 5, 1 },
				{1,6,5,1}
		};
		for (int[] is : numss) {
			for (int i : is) {
				System.out.print(i + " ");
			}
			nextPermutation(is);
			for (int i : is) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
