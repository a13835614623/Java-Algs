package com.algorithm;

/*
	给定一个字符串，找出不含有重复字符的最长子串的长度。
	
	示例 1:
	
	输入: "abcabcbb"
	输出: 3 
	解释: 无重复字符的最长子串是 "abc"，其长度为 3。
	
	示例 2:
	
	输入: "bbbbb"
	输出: 1
	解释: 无重复字符的最长子串是 "b"，其长度为 1。
	
	示例 3:
	
	输入: "pwwkew"
	输出: 3
	解释: 无重复字符的最长子串是 "wke"，其长度为 3。
 */
public class LengthOfLongestSubstring_3 {
	public static int lengthOfLongestSubstring(String s) {
		char[] chs = null;
		if (s != null && s.length() > 0) {
			chs = s.toCharArray();
		} else {
			return 0;
		}
		StringBuilder sb = new StringBuilder("");
		int count = 0;
		for (int i = 0, len = chs.length; i < len; i++) {
			char c = chs[i];
			int index = sb.indexOf(c + "");
			if (index == -1) {// 字符c不存在，则添加
				sb.append(c);// 添加c
				if (sb.length() > count)// 如果sb的长度大于count，则
					count = sb.length();// 更新count
			} else {// 字符存在,则清空sb
				sb.append(c);
				sb.delete(0, index + 1);
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String[] ss = { "pwwkew", "abcabcbb", "bbbbb", "dvdf" };
		for (String s : ss) {
			System.out.println(lengthOfLongestSubstring(s));
		}
	}
}
