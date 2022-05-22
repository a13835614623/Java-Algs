package com.algorithm;

import java.util.*;

/**
 * @description MinWindow_76
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *  
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *  
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *  
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/minimum-window-substring">https://leetcode.cn/problems/minimum-window-substring</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/04
 */
public class MinWindow_76 {

    public static String minWindow(String s, String t) {
        int tLen = t.length();
        int sLen = s.length();
        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            char key = t.charAt(i);
            Integer count = countMap.getOrDefault(key, 0);
            countMap.put(key, count + 1);
        }
        Map<Character, Integer> map = new HashMap<>();
        String res = null;
        while (l < sLen) {
            if (countMap.containsKey(chars[l])) {
                map.put(chars[l], 1);
                break;
            } else {
                l++;
            }
        }
        if (l == sLen) {
            return "";
        }
        boolean match = match(map, countMap);
        r = l + 1;
        while (r < sLen + 1) {
            if (!match) {
                while (r < sLen) {
                    if (countMap.containsKey(chars[r])) {
                        map.put(chars[r], map.getOrDefault(chars[r], 0) + 1);
                        if (match(map, countMap)) {
                            r++;
                            match = true;
                            break;
                        }
                    }
                    r++;
                }
            }
            if (match) {
                if (res == null || res.length() > r - l) {
                    res = s.substring(l, r);
                }
                int value = map.get(chars[l]);
                if (value == 1) {
                    map.remove(chars[l]);
                } else {
                    map.put(chars[l], value - 1);
                }
                l++;
                while (l < sLen && !countMap.containsKey(chars[l])) {
                    l++;
                }
                match = match(map, countMap);
            } else {
                break;
            }
        }
        return res == null ? "" : res;
    }

    private static boolean match(Map<Character, Integer> map, Map<Character, Integer> countMap) {
        Set<Map.Entry<Character, Integer>> entries = countMap.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            Integer count = map.get(entry.getKey());
            if (count == null || count.compareTo(entry.getValue()) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("aa", "aa"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("a", "aa"));
    }
}
