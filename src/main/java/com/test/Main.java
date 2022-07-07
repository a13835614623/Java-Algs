package com.test;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {


    public static int get(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        if (len == 1) {
            return 1;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        dp[1] = chs[0] == 0 ? 1 : 2;
        if (len == 2) {
            return dp[1];
        }
        dp[2] = ((chs[0] - '0') * 4 + (chs[1] - '0') * 2 + (chs[2] - '0') + 2) >> 1;
        if (len == 3) {
            return dp[2];
        }
        for (int i = 3; i < len; i++) {
            dp[i] = dp[i - 1];
            if (chs[i - 1] == '1') {
                dp[i] += dp[i - 2];
            }
            if (chs[i - 2] == '1') {
                dp[i] += dp[i - 3];
            }
        }
        return dp[len - 1];
    }

    public static void main(String[] args) throws IOException {

        Stream.of("10001","110011","10100101").forEach(s->{
            System.out.println(get(s));
            System.out.println(get2(s));
        });
    }

    private static int get2(String br)  {
        char[] str = br.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;         // 最后一个字符肯定只能是一种翻译
        // 从后往前遍历字符
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];     // 单字符码的情况
            if (str[i] == '1') {      // 对于"1"，还有双字符码和三字符码的情况
                if (i + 2 <= n) dp[i] += dp[i + 2];
                if (i + 3 <= n) dp[i] += dp[i + 3];
            }
        }
        return dp[0];
    }
}