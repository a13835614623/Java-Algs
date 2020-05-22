package com.algorithm;

import java.util.LinkedList;

/*
	给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
	
	示例 1：
	
	输入: "babad"
	输出: "bab"
	注意: "aba"也是一个有效答案。
	
	示例 2：
	
	输入: "cbbd"
	输出: "bb"
 */
public class LongestPalindrome_5 {
	/**
	 * 未优化的求最长回文子串方法
	 * 
	 * @param ss
	 * @return
	 */
	public static String longestPalindrome(String ss) {
		String s = ss.replace("", "#").substring(1, ss.length() * 2 + 1);
		char[] chs = null;
		if (s == null || s.length() == 0) {
			return "";
		}
		chs = s.toCharArray();// 转换为字符数组
		String result = "";
		LinkedList<String> list = null;
		for (int i = 0, len = chs.length; i < len; i++) {
			// 以chs[i]为中心开始扩展
			int j = 1;
			while (i - j >= 0 && i + j < len && chs[i - j] == chs[i + j]) {// 左右相等
				j++;
			}
			j--;
			if (j >= 0) {// 该回文长度>=3
				int length = 2 * j + 1;
				if (length > result.length()) {// 该回文长度>目前最长的回文长度
					list = new LinkedList<>();
					result = s.substring(i - j, i + j + 1);
					list.add(result);
				} else if (length == result.length()) {// 该回文长度==目前最长的回文长度
					list.add(result);
				}
			}
		}
		return result.replace("#", "");
	}
	public static void main(String[] args) {
		String[] ss = { "babad", "cbbd", "bbbbb", "dvdf", "a", "" };
		for (String s : ss) {
			System.out.println(longestPalindrome(s));
		}
	}
}
