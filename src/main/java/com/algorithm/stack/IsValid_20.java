package com.algorithm.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。
 * 
 * 注意空字符串可被认为是有效字符串。
 * 
 * 示例 1:
 * 
 * 输入: "()" 输出: true
 * 
 * 示例 2:
 * 
 * 输入: "()[]{}" 输出: true
 * 
 * 示例 3:
 * 
 * 输入: "(]" 输出: false
 * 
 * 示例 4:
 * 
 * 输入: "([)]" 输出: false
 * 
 * 示例 5:
 * 
 * 输入: "{[]}" 输出: true
 *
 */
public class IsValid_20 {
	public static boolean isValid(String s) {
		if (s != null && s.equals("")) {
			return true;
		}
		Map<Character, Character> map = new HashMap<>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		char[] chs = s.toCharArray();
		int len = chs.length;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			if (chs[i] == '(' || chs[i] == '[' || chs[i] == '{') {
				stack.push(chs[i]);
			} else if (!stack.isEmpty() && new Character(chs[i]).equals(map.get(stack.peek()))) {
				stack.pop();
			} else {
				stack.push(chs[i]);
			}
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
	}
}
