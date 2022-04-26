package com.algorithm;

/**
 * @description IsSubsequence_392
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/27
 */
public class IsSubsequence_392 {

    static class Solution {
        public boolean isSubsequence(String s, String t) {
            int len1 = s.length();
            int len2 = t.length();
            if (len1 > len2) {
                return false;
            }
            int i = 0, j = 0;

            while (j < len2) {
                if (i < len1 && s.charAt(i) == t.charAt(j)) {
                    i++;
                }
                j++;
            }
            return i == len1;
        }

        public boolean isSubsequence2(String s, String t) {
            int len1 = s.length();
            int len2 = t.length();
            if (len1 > len2) {
                return false;
            }
            // dp[i][j] 代表字符串t从位置i开始 字母j第一次出现的位置
            int[][] dp = new int[len2 + 1][26];
            for (int i = 0; i < 26; i++) {
                dp[len2][i] = len2;
            }
            for (int i = len2 - 1; i >= 0; i--) {
                int c = t.charAt(i) - 'a';
                for (int j = 1; j < 26; j++) {
                    if (c != j) {
                        dp[i][j] = dp[i + 1][j];
                    } else {
                        dp[i][c] = i;
                    }
                }
            }

            int i = 0, j = 0;
            while (i < len1) {
                int index = dp[j][s.charAt(i) - 'a'];
                if (index == len2) {
                    return false;
                } else {
                    j = index+1;
                    i++;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution x = new Solution();
        System.out.println(x.isSubsequence2("abc", "ahbgdc"));
    }
}
