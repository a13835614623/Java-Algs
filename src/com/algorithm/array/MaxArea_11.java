package com.algorithm.array;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为
 * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * @author zzk
 *
 */
public class MaxArea_11 {
	/**
	 * 暴力破解
	 * 
	 * @param height
	 * @return
	 */
	public static int maxArea(int[] height) {
		int area = 0;
		for (int i = 0, len = height.length; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
			}
		}
		return area;
	}

	/**
	 * 双指针
	 * 
	 * @param height
	 * @return
	 */
	public static int maxArea2(int[] height) {
		int area = 0;
		for (int i = 0, len = height.length, j = len - 1; i < j;) {
			area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
			if (height[i] > height[j]) {
				j--;
			} else {
				i++;
			}
		}
		return area;
	}

	public static void main(String[] args) {
		int arr[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(maxArea2(arr));
	}
}
