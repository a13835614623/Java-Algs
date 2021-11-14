package com.algorithm.array;

import java.util.Arrays;

import com.algorithm.array.util.ArrayUtil;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3] 输出: [1,2,4] 解释: 输入数组表示数字 123。
 * 
 * 示例 2:
 * 
 * 输入: [4,3,2,1] 输出: [4,3,2,2] 解释: 输入数组表示数字 4321。
 * 
 * 
 * @description: 加一
 * @author zzk
 * @className PlusOne_66.java
 * @date 2019年4月8日 下午7:43:48
 */
public class PlusOne_66 {
	public static int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0)
			return null;
		int len = digits.length;
		int i = 1;
		while (len - i >= 0 && digits[len - i] == 9) {
			digits[len - i] = 0;
			i++;
		}
		if (len - i == -1) {
			int result[] = new int[len + 1];
			result[0] = 1;
			System.arraycopy(digits, 0, result, 1, len);
			return result;
		} else {
			digits[len - i]++;
		}
		return digits;
	}

	public static void main(String[] args) {
		int digits[] = { 9,9,9};
		ArrayUtil.printArray(plusOne(digits));
	}
}
