package com.algorithm;

/**
 * @description IsInterleave_97
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *  
 *
 * 提示：
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 *  
 *
 * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/24
 */
public class IsInterleave_97 {
    static class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            int len1 = s1.length();
            int len2 = s2.length();
            int len3 = s3.length();
            if (len2 + len1 != len3) {
                return false;
            }
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            char[] chs3 = s3.toCharArray();
            return dfs(chs1, chs2, chs3, len1, len2, len3, 0, 0, 0);
        }

        public boolean dfs(char[] chs1, char[] chs2, char[] chs3, int len1, int len2, int len3, int i1, int i2, int i3) {
            if (i3 == len3) {
                return i1 == len1 && i2 == len2;
            }
            char c3 = chs3[i3];
            return i1 < len1 && c3 == chs1[i1] && dfs(chs1, chs2, chs3, len1, len2, len3, i1 + 1, i2, i3 + 1)
                    || i2 < len2 && c3 == chs2[i2] && dfs(chs1, chs2, chs3, len1, len2, len3, i1, i2 + 1, i3 + 1);
        }
    }

    static class Solution2 {
        public boolean isInterleave(String s1, String s2, String s3) {
            int n = s1.length(), m = s2.length(), t = s3.length();

            if (n + m != t) {
                return false;
            }

            boolean[][] f = new boolean[n + 1][m + 1];
            // abcd efg aebfgcd
            // f[0][1] = false
            // f[1][0] = true
            // f[1][1] = true
            // f[i][j] = true 代表s3的前i+j个元素可以由s1的前i个和s2的前j个元素交错得到
            // f[i][j] = f[i-1][j] && s1[i-1]==s3[i+j-1]
            f[0][0] = true;
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= m; ++j) {
                    int p = i + j - 1;
                    if (i > 0) {
                        f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                    }
                    if (j > 0) {
                        f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }

            return f[n][m];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
