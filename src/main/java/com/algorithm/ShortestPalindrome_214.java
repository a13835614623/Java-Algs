package com.algorithm;

/*
给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

示例 1:

输入: "aacecaaa"
输出: "aaacecaaa"

示例 2:

输入: "abcd"
输出: "dcbabcd"
 */
public class ShortestPalindrome_214 {
	public static String shortestPalindrome(String s) {
		StringBuilder result = new StringBuilder("");
		if (s == null || s.length() == 0) {
			return "";
		} else if (isPalindrome(s)) {
			return s;
		}
		for (int len = s.length(), i = len - 1; i >= 0; i--) {
			if (isPalindrome(s.substring(0, i + 1))) {
				for (int j = len - 1; j > i; j--) {
					result.append(s.charAt(j));
				}
				result.append(s);
				break;
			}
		}
		return result.toString();
	}

	/**
	 * 判断字符串是否是回文
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String ss) {
		if (ss == null) {
			return false;
		} else if (ss.length() == 0 || ss.length() == 1) {
			return true;
		}
		StringBuilder sb = new StringBuilder(ss);
		sb.reverse();
		return sb.toString().equals(ss);
	}

	public static String shortestPalindrome2(String s) {
		if (s.length() == 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s);
		sb.append('#');// 加一个标识防止s+reverse(s)得到的最长公共前后缀长度超过原串
		sb.append(new StringBuilder(s).reverse().toString());
		sb.append('!');// 在最后任意加一个字符(遵循kmp的next数组的语义,求目标最长公共前后缀),不与标志字符‘#’相同即可。
		return new StringBuilder(s.substring(getNext(sb.toString())[sb.length() - 1])).reverse().append(s).toString();
	}

	/**
	 * kmp算法.通过模式串得失配next数组--‘next[i],在i位置发生失配’,pattern[0,i-1]的最长公共前后缀'长度',又下一个字符的索引值==长度
	 * next[i],pattern[0,i-1]的最长公共前后缀的长度。notice：length((前缀|后缀))<=length(str)-1,即前缀后缀的概念不含字符串本身
	 */
	private static int[] getNext(String pattern) {
		int[] next = new int[pattern.length()];
		next[0] = -1;
		int pre;// 待验证字符索引,关键变量
		for (int i = 1; i < pattern.length(); i++) {
			pre = next[i - 1];
			while (pre != -1 && pattern.charAt(i - 1) != pattern.charAt(pre)) {// 比较pattern[i-1]与pattern[pre]
				pre = next[pre];// kmp核心
			}
			if (pre == -1) {
				next[i] = 0;
			} else {
				next[i] = pre + 1;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String ss[] = { "abc", "abac", "a", "", "aaa", "abcda" };
		long start = System.currentTimeMillis();
		for (String s : ss) {
			 System.out.println(isPalindrome(s));
		}
		long end = System.currentTimeMillis();
		System.out.println("所耗费时间为:" + (end - start) + "ms");
	}
}
