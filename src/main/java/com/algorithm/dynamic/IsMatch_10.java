package com.algorithm.dynamic;

import com.algorithm.array.util.ArrayUtil;

/**
 * @description IsMatch_10
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/06/05
 */
public class IsMatch_10 {

    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        // aa a*
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= sLen; i++) {
            int lastI = i - 1;
            char sc = s.charAt(lastI);
            for (int j = 1; j <= pLen; j++) {
                int lastJ = j - 1;
                char pc = p.charAt(lastJ);
                if (sc == pc) {
                    if (j >= 2 && p.charAt(lastJ - 1) == '*' && dp[lastI][lastJ - 2]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[lastI][lastJ];
                    }
                } else if (pc == '.') {
                    dp[i][j] = dp[lastI][lastJ];
                } else if (pc == '*') {
                    boolean last2Eq = (p.charAt(j - 2) == sc || p.charAt(j - 2) == '.');
                    // aa aa*
                    // a ac*
                    // a .*
                    dp[i][j] = dp[i][lastJ] // ca ca*
                            || dp[lastI][j] && last2Eq // a ac*
                            || j >= 2 && dp[i][j - 2] //  a  aa*
                            || dp[lastI][lastJ] && last2Eq;
                } else {
                    dp[i][j] = false;
                }
//                System.out.println(String.format("%s %s = %s ", s.substring(0, i), p.substring(0, j), dp[i][j]));
            }
        }
        return dp[sLen][pLen];
    }

    public static void main(String[] args) {
        IsMatch_10 x = new IsMatch_10();
        System.out.println(x.isMatch("aaaa", "a*"));
//        System.out.println(x.isMatch("abccd", "a*bc.d"));
//        System.out.println(x.isMatch("ab",".*"));
//        System.out.println(x.isMatch("aab", "c*a*b"));
//        System.out.println(x.isMatch("missi", "mis*"));
    }
}
