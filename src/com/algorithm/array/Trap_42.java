package com.algorithm.array;

import java.util.Iterator;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢
 * Marcos 贡献此图。
 * 
 * 示例: https://leetcode-cn.com/problems/trapping-rain-water/ 输入:
 * [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6
 * 
 * 
 * @description: 接雨水
 * @author zzk
 * @className Trap_42.java
 * @date 2019年4月6日 下午7:01:07
 */
public class Trap_42 {
	/**
	 * 
	 * @description: 接雨水
	 * @author zzk
	 * @date 2019年4月7日 上午10:05:30
	 * @param height
	 * @return
	 */
	public static int trap(int[] height) {
		if (height == null)
			return 0;
		int len = height.length;
		if (len >= 0 && len <= 2)
			return 0;
		int max = 0;
		// 寻找最高值的索引
		for (int i = 0; i < len; i++) {
			if (height[i] > height[max])
				max = i;
		}
		int result = 0;
		// maxL为最高点左边的最高点索引
		// maxR为右边最高点的索引
		int maxL = max - 1, maxR = max;
		while (maxL >= 0) {
			// 求maxL
			maxL = maxR - 1;
			for (int i = maxL; i >= 0; i--) {
				if (height[i] > height[maxL]) {
					maxL = i;
				}
			}
			if (maxR - maxL >= 2)
				result += height[maxL] * (maxR - maxL - 1) - area(height, maxL, maxR);
			maxR = maxL;
		}
		maxL = max;
		maxR = maxL + 1;
		while (maxR <= len - 1) {
			// 求maxR
			maxR = maxL + 1;
			for (int i = maxR; i < len; i++) {
				if (height[i] > height[maxR]) {
					maxR = i;
				}
			}
			if (maxR - maxL >= 2)
				result += height[maxR] * (maxR - maxL - 1) - area(height, maxL, maxR);
			maxL = maxR;
		}
		return result;
	}

	public static int area(int[] height, int left, int right) {
		int area = 0;
		for (int i = left + 1; i < right; i++) {
			area += height[i];
		}
		return area;
	}

	public static void main(String[] args) {
		int height[] = { 4, 3, 2, 0, 4 };
		int height2[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		int height3[] = { 5, 4, 1, 2 };
		System.out.println(trap(height3));
	}
}
