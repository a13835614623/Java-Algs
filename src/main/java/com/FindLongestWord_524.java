package com;

import java.util.List;

/**
 * @description FindLongestWord_524
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 *
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 *
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/08
 */
public class FindLongestWord_524 {

    public String findLongestWord(String s, List<String> dictionary) {
        int iLen = s.length();
        int[] index = new int[128];
        char[] chs = s.toCharArray();
        for (char aChar : chs) {
            index[aChar]++;
        }
        String res = null;
        int resLen = 0;
        for (String jStr : dictionary) {
            int jLen = jStr.length();
            char[] chs2 = jStr.toCharArray();
            int i = 0;
            int j = 0;
            while (i < iLen && j < jLen) {
                if (chs2[j] != chs[i]) {
                    i++;
                } else {
                    i++;
                    j++;
                }
            }
            if (j == jLen) {
                if (res == null) {
                    res = jStr;
                    resLen = jLen;
                } else {
                    if (jLen > resLen || jLen == resLen && jStr.compareTo(res) < 0) {
                        res = jStr;
                        resLen = jLen;
                    }
                }
            }
        }
        return res == null ? "" : res;
    }

    public static void main(String[] args) {

    }
}
