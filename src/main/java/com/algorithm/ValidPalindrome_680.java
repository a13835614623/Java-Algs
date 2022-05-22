package com.algorithm;

/**
 * @description ValidPalindrome_680
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "abca"
 * 输出: true
 * 解释: 你可以删除c字符。
 * 示例 3:
 *
 * 输入: s = "abc"
 * 输出: false
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/07
 */
public class ValidPalindrome_680 {
    // abcab
    // aabbbbcaa
    // abcba
    public static boolean validPalindrome(String s) {
        int length = s.length();
        int l = 0;
        int r = length - 1;
        char[] chars = s.toCharArray();
        boolean delete = false;
        while (l < r) {
            if (chars[l] != chars[r]) {
                if (delete) {
                    return false;
                }
                // 删除 l 可以
                boolean leftOk = chars[l + 1] == chars[r];
                boolean rightOk = chars[l] == chars[r - 1];
                if (rightOk && leftOk) {
                    return isValidPalindrome(chars, l + 1, r) || isValidPalindrome(chars, l, r - 1);
                } else if (leftOk) {
                    l++;
                    delete = true;
                } else if (rightOk) {
                    r--;
                    // 删除 r 可以
                    delete = true;
                } else {
                    return false;
                }
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    public static boolean isValidPalindrome(char[] chs, int l, int r) {
        while (l < r) {
            if (chs[l] != chs[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validPalindrome("a"));
        System.out.println(validPalindrome("aa"));
        System.out.println(validPalindrome("aaa"));
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abac"));
        System.out.println(validPalindrome("caba"));
        System.out.println(validPalindrome("abcAba"));
        System.out.println(validPalindrome("abcAaba"));
        System.out.println(validPalindrome("abcsaba"));
        System.out.println(validPalindrome("eeccccbebaeeabebccceea"));
        String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        System.out.println(validPalindrome(s));
    }
}
