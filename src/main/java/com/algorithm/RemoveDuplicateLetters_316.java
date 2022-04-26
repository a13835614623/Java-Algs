package com.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description RemoveDuplicateLetters_316
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/04/10
 */
public class RemoveDuplicateLetters_316 {

    public static String removeDuplicateLetters(String s) {
        int length = s.length();
        // 保存每个 字母最后出现的索引
        int[] lastIndex = new int[26];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[26];
        for (int i = 0; i < length; i++) {
            // 如果栈里已经出现过，则直接丢弃
            char c = s.charAt(i);
            int ci = c - 'a';
            // 如果栈里已经有了,则丢弃
            if (visited[ci]) {
                continue;
            }
            if (stack.isEmpty()) {
                stack.addLast(c);
                visited[ci] = true;
                continue;
            }
            // 如果栈里没有,则移除栈里所有小于当前元素且后面还会出现的元素
            while (!stack.isEmpty() && c < stack.peekLast() && lastIndex[stack.peekLast()-'a'] > i) {
                visited[stack.removeLast()-'a']=false;
            }
            stack.addLast(c);
            visited[ci] = true;
        }
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
}
