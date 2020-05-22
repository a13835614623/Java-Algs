package com.algorithm;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 
 * 示例 1:
 * 
 * 输入: dividend = 10, divisor = 3 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: dividend = 7, divisor = -3 输出: -2
 * 
 * 说明:
 * 
 * 被除数和除数均为 32 位有符号整数。 除数不为 0。 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 −
 * 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 */
public class Divide_29 {
	public static int divide(int dividend, int divisor) {
		long dividendL = dividend;
		long divisorL = divisor;
		boolean isNegetive1 = dividend < 0, isNegetive2 = divisorL < 0;
		if (isNegetive1) {
			dividendL = -dividendL;
		}
		if (isNegetive2) {
			divisorL = -divisorL;
		}
		boolean isNegetive = isNegetive1 ^ isNegetive2;
		if (isNegetive && dividendL < divisorL) {
			return 0;
		}
		long result = 0;// 商
		long mod = 0;// 余数
		int n = 0;
		char chs[]= new char[32];
		if (dividendL == 2147483648L) {
			chs[0] = '1';
			for (int i = 1; i <= 31; i++) {
				chs[i] = '0';
			}
		} else {
			chs = Integer.toBinaryString((int) dividendL).toCharArray();
		}
		while (n < chs.length) {
			mod <<= 1;
			mod += chs[n++] - '0';
			if (mod >= divisorL) {
				result <<= 1;
				result += 1;
				mod -= divisorL;
			} else {
				result <<= 1;
			}
		}
		if (isNegetive)
			result = -result;
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			result = Integer.MAX_VALUE;
		}
		return (int) result;
	}

	public static void main(String[] args) {
		System.out.println(-2147483648 / -1109186033);
		System.out.println(divide(-2147483648, 1));
	}
}
