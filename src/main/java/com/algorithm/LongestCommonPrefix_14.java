package com.algorithm;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"] 输出: "fl"
 * 
 * 示例 2:
 * 
 * 输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
 * 
 * 说明:
 * 
 * 所有输入只包含小写字母 a-z 。
 * 
 * @author zzk
 *
 */
public class LongestCommonPrefix_14 {
	public static String longestCommonPrefix(String[] strs) {
		int len;
		if (strs == null || (len = strs.length) == 0)
			return "";
		if (len == 1)
			return strs[0];
		int result = 0;
		String startStr = strs[0];
		for (int i = 0; i < startStr.length(); i++) {// 字符索引
			char ch = startStr.charAt(i);
			for (int j = 1; j < len; j++) {// 要比较的字符串索引
				if (strs[j].length() <= i || ch != strs[j].charAt(i)) {
					return startStr.substring(0, result);
				}
			}
			result++;
		}
		return startStr.substring(0, result);
	}

	public static void main(String[] args) {
		String[] ss = { "aa", "aa", "a" };
		System.out.println(longestCommonPrefix(ss));
	}
}
