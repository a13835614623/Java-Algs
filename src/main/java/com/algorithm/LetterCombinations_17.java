package com.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 示例:
 * 
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * @author zzk
 *
 */
public class LetterCombinations_17 {
	static Map<Integer, char[]> map = new HashMap<>();
	static {
		char ch = 'a';
		// 初始化map
		for (int i = 2; i <= 9; i++) {
			if (i == 7 || i == 9) {
				map.put(i, new char[] { (char) (ch++), (char) (ch++), (char) (ch++), (char) (ch++) });
			} else {
				map.put(i, new char[] { (char) (ch++), (char) (ch++), (char) (ch++) });
			}
		}
	}

	public static List<String> letterCombinations(String digits) {
		List<String> list = new ArrayList<String>();
		if (digits == null || digits.length() < 1 || digits.contains("1")) {
			return list;
		}
		Integer num = new Integer("" + digits.charAt(0));
		char[] words = map.get(num);
		for (char c : words) {
			list.add("" + c);
		}
		if (digits.length() > 1) {
			List<String> list2 = letterCombinations(digits.substring(1));
			for (int i = 0, len = list.size(); i < len; i++) {
				for (int j = 0; list2 != null && j < list2.size(); j++) {
					list.add(list.get(i) + list2.get(j));
				}
			}
			if (list.size() > 9) {
				for (int i = 0; i < words.length; i++) {
					list.remove(0);
				}
			}
		}
		// System.out.println(list);
		return list;
	}

	public static void main(String[] args) {
		System.out.println(letterCombinations("2").size());
	}

}
