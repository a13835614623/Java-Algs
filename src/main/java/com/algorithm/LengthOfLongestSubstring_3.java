package com.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int left =0;
        for (int i = 0, len = chs.length; i < len; i++) {
            char c = chs[i];
            if (!map.containsKey(c)) {// 字符c不存在，则添加
                map.put(c, i);//添加c
                //如果sb的长度大于count，则更新count
                count = Math.max(count, map.size());
            } else {// 字符存在,则删除c之前的元素
                int newLeft = map.get(c);
                for (int j = left; j < newLeft; j++) {
                    map.remove(chs[j]);
                }
            }
        }
        return count;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int maxLength = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    set.clear();
                    set.add(s.charAt(j));
                } else {
                    set.add(s.charAt(j));
                }
                maxLength = Math.max(maxLength, set.size());
            }
            maxLength = Math.max(maxLength, set.size());
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring3(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> set = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !set.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                set.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] ss = {"pwwkew", "abcabcbb", "bbbbb", "dvdf"};
        for (String s : ss) {
            System.out.println(lengthOfLongestSubstring(s));
        }
    }
}
