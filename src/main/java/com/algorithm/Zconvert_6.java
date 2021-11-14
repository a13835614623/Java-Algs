package com.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Z 字形变换 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * 
 * L C I R E T O E S I I G E D H N
 * 
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 
 * 请你实现这个将字符串进行指定行数变换的函数：
 * 
 * string convert(string s, int numRows);
 * 
 * 示例 1:
 * 
 * 输入: s = "LEETCODEISHIRING", numRows = 3 输出: "LCIRETOESIIGEDHN"
 * 
 * 示例 2:
 * 
 * 输入: s = "LEETCODEISHIRING", numRows = 4 输出: "LDREOEIIECIHNTSG" 解释:
 * 
 * L D R E O E I I E C I H N T S G
 */
public class Zconvert_6 {
	public static String convert(String s, int numRows) {
		if (s == null || s.length() <= numRows || numRows == 1) {
			return s;
		}
		char[] chs = s.toCharArray();
		List<Character>[] lists = new LinkedList[numRows];
		for (int i = 0; i < numRows; i++) {
			lists[i] = new LinkedList<>();
		}
		// String[][] ss = new String[numRows][];
		int i = 0, // 字符串索引
				row = 0;// 行号
		boolean isCol = false;
		int len = (2 * numRows - 2);// 一次斜向+一次列向的长度
		while (i < chs.length) {
			int yushu = i % len;
			if (yushu == numRows) {// 变为斜向移动
				row--;// 上移
				isCol = false;
			} else if (yushu == 0) {// 变为列移动
				if (isCol) {
					row--;
				}
				isCol = true;
			}
			if (isCol) {// 如果为列移动
				// System.out.println("列移动:" + i + ",row=" + row);
				lists[row].add(chs[i]);
				if (row < numRows - 1) {
					row++;
				}
			} else {// 如果为斜向移动
				// System.out.println("斜向移动:" + i + ",row=" + row);
				lists[row].add(chs[i]);
				row--;// 上移
			}
			i++;// 移动主字符串指针
		}
		StringBuilder result = new StringBuilder();
		for (List<Character> list : lists) {
			for (Character ch : list) {
				result.append(ch);
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(convert("LEETCODEISHIRING", 3));
	}
}
