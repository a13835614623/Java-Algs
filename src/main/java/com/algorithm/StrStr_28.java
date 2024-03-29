package com.algorithm;

/**
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回 -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll" 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba" 输出: -1
 * 
 * 说明:
 * 
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 
 * @author zzk
 *
 */
public class StrStr_28 {
	public static int strStr(String haystack, String needle) {
		if (needle == null || needle.length() == 0 || "".equals(needle)) {
			return 0;
		}
		int needleLen = needle.length();
		for (int i = 0, len = haystack.length() - needleLen + 1; i < len; i++) {
			if (haystack.substring(i, i + needleLen).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(strStr("a", "a"));
		long end = System.currentTimeMillis();
		System.out.println("耗费时间为:" + (end - start));
	}
}
