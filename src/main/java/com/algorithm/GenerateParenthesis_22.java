package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 
 * 例如，给出 n = 3，生成结果为：
 * 
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 *
 */
public class GenerateParenthesis_22 {
	public static List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		list.add("(");
		func(list, n * 2, n * 2);
		return list;
	}

	public static void func(List<String> list, int n, final int length) {
		if (n == 2) {
			for (int i = 0; i < list.size(); i++) {
				String s = list.get(i) + ")";
				if (isPromise(s, length)) {
					list.set(i, s);
				}
			}
			return;
		} else {
			for (int i = 0, len = list.size(); i < len; i++) {
				String s = list.get(i);
				String s1 = s + "(";
				String s2 = s + ")";
				if (isPromise(s1, length)) {
					list.set(i, s1);
				} else {
					list.remove(i);
					i--;
					len--;
				}
				if (isPromise(s2, length)) {
					list.add(s2);
				}
			}
		}
		func(list, n - 1, length);
	}

	/**
	 * 该字符串是否有希望
	 */
	public static boolean isPromise(String s, final int length) {
		char[] chs = s.toCharArray();
		int len = chs.length;
		int leftLen = 0;
		int midLenth = length >> 1;
		for (char c : chs) {
			if (c == '(')
				leftLen++;
			if (leftLen > midLenth) {// 如果'('的个数大于一半
				return false;
			}
		}
		// 如果')'的个数大于'('的个数,或者')'的个数大于一半，则没有希望
		if (len - leftLen > leftLen) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<String> list = generateParenthesis(8);
		int i = 0;
		for (String sb : list) {
			System.out.println(i++ + ":" + sb);
		}
		long end = System.currentTimeMillis();
		System.out.println("执行了" + (end - start) + "ms");
	}
}
