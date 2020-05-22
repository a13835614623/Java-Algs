package com.algorithm;

import com.sun.org.apache.regexp.internal.RE;

/**
 * KMP算法
 *
 * @author zzk
 */
public class KMP {
    /**
     * 暴力匹配
     *
     * @param s  主串
     * @param ss 模式串
     */
    public static int kmp1(String s, String ss) {
        int result = -1;
        int len1 = s.length(), len2 = ss.length();
        for (int i = 0, j = 0; i < len1 && j < len2; i++, j++) {
            if (s.charAt(i) != ss.charAt(j)) {// 如果不同，
                i -= j;// i后退
                j = -1;// j变-1
            } else if (j == len2 - 1) {// 如果j为最后一个
                result = i - j;// 返回结果
                break;
            }
        }
        return result;
    }

    /**
     * 获得模式串的next[] next[j]:当s[j]不匹配时，j指针要移动到的位置
     *
     * @param s
     * @return
     * @version v1.0
     */
    public static int[] getNext(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int next[] = new int[len];
        next[0] = -1;
        int k = -1, j = 0;
        while (j < len - 1) {
            if (k == -1 || chs[j] == chs[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

	/**
	 * 优化后的next数组算法
	 * @param ps
	 * @return
	 */
    public static int[] getNext2(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int r = 0;
        int l = -1;
        while (r < p.length - 1) {
            if (l == -1 || p[r] == p[l]) {
                if (p[++r] == p[++l]) { // 当两个字符相等时要跳过
                    next[r] = next[l];
                } else {
                    next[r] = l;
                }
            } else {
                l = next[l];
            }
        }
        return next;
    }

    public static int[] getMyNext(String ps) {
        char[] p = ps.toCharArray();
        int size = p.length;
        int[] next = new int[size];
        next[0] = -1;
        int l = 0, r = 1;
        while (r < size) {
            if (next[r - 1] == -1) {
                l = 0;
            } else {
                l = next[r - 1];
                if (p[l++] == p[r]) {
					next[r] = l;
                } else {
                    next[r] = -1;
                }
                l=next[r];
            }
            r++;
        }
		return next;
    }

    /**
     * kmp2
     *
     * @param s  主串
     * @param ss 模式串
     */
    public static int kmp2(String s, String ps) {
        char chs1[] = s.toCharArray();
        char chs2[] = ps.toCharArray();
        int len1 = chs1.length, len2 = chs2.length;
        int next[] = getNext2(ps);// 当ss[j]不匹配时，j指针要移动到的位置
        int i = 0, j = 0;
        while (i < len1 && j < len2) {
            if (j == -1 || chs1[i] == chs2[j]) {
                i++;
                j++;
            } else {// 如果chs1[i]和chs2[j]不匹配，
                j = next[j];// j回到指定位置
            }
        }
        if (j == len2) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
//        String s1 = "ABAADSCABACDSDSSD";
//        String ss[] = {"ABA", "DSC", "DSSD", "CCD"};
//        System.out.println(kmp2(s1, ss[2]));

		printArr(getNext("abab"));
		printArr(getNext2("abab"));
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
