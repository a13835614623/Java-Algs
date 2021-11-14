package com.algorithm;

/**
 * 
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 
 * 示例 1:
 * 
 * 输入: 123 输出: 321
 * 
 * 示例 2:
 * 
 * 输入: -123 输出: -321
 * 
 * 示例 3:
 * 
 * 输入: 120 输出: 21
 * 
 * 注意:
 * 
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * 
 */
public class Reverse_7 {
	public static int reverse(int x) {
		try {
			int xx = Integer.parseInt(new StringBuilder(String.valueOf(Math.abs(x))).reverse().toString());
			if (xx > Integer.MAX_VALUE || xx < Integer.MIN_VALUE) {
				return 0;
			}
			return x > 0 ? xx : -xx;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static void main(String[] args) {
		System.out.println(reverse(1534236469));
	}
}
