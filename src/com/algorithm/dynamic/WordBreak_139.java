package com.algorithm.dynamic;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 单词拆分
 *
 * @Author: zzk
 * @Date: 2020-03-14 11:08
 */
public class WordBreak_139 {
    public static Boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        Boolean[] dp = new Boolean[len + 1];
        return help(s, dp, 0, len, set);
    }

    public static Boolean help(String s, Boolean[] dp, int start, int end, Set<String> set) {
        if (start == end) return true;
        Boolean flag = dp[start];
        if (flag != null) {
            return flag;
        }
        StringBuilder sLeft = new StringBuilder();
        for (int i = start; i < end; i++) {
            sLeft.append(s.charAt(i));
            if (set.contains(sLeft.toString()) && help(s, dp, i + 1, end, set)) {
                return dp[start] = true;
            }
        }
        return dp[start]=false;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("cats", "cat", "and", "dog");
        boolean res = wordBreak("catsanddog", list);
        System.out.println(res);
    }
}
