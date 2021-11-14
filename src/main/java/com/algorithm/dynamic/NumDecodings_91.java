package com.algorithm.dynamic;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * <p>
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zzk
 * @Date: 2020-03-14 10:40
 */
public class NumDecodings_91 {

    public static int numDecodings(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] dp = new int[len];
        if (chs[0] == '0') return 0;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (chs[i] == '0') {
                if (chs[i - 1] == '1' || chs[i - 1] == '2') {
                    if (i >= 2) {
                        dp[i] = dp[i - 2];
                    } else {
                        dp[i] = 1;
                    }
                } else {
                    return 0;
                }
            } else if (chs[i - 1] == '1' || (chs[i - 1] == '2' && chs[i] <= '6')) {
                if (i < 2) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("2030"));
    }
}
