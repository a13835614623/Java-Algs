package com.algorithm;

import java.util.*;
import java.util.function.Function;

/**
 * @description CloseStrings_1657
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 *
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 *
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "abc", word2 = "bca"
 * 输出：true
 * 解释：2 次操作从 word1 获得 word2 。
 * 执行操作 1："abc" -> "acb"
 * 执行操作 1："acb" -> "bca"
 * 示例 2：
 *
 * 输入：word1 = "a", word2 = "aa"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * 示例 3：
 *
 * 输入：word1 = "cabbba", word2 = "abbccc"
 * 输出：true
 * 解释：3 次操作从 word1 获得 word2 。
 * 执行操作 1："cabbba" -> "caabbb"
 * 执行操作 2："caabbb" -> "baaccc"
 * 执行操作 2："baaccc" -> "abbccc"
 * 示例 4：
 *
 * 输入：word1 = "cabbba", word2 = "aabbss"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 *  
 *
 * 提示：
 *
 * 1 <= word1.length, word2.length <= 105
 * word1 和 word2 仅包含小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/determine-if-two-strings-are-close
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/13
 */
public class CloseStrings_1657 {


    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();
        int[] countMap = new int[128];
        int[] countMap2 = new int[128];
        for (char c : chs1) {
            countMap[c]++;
        }
        for (char c : chs2) {
            countMap2[c]++;
        }
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 127; i >= 0; i--) {
            boolean exist = countMap[i] != 0;
            if (exist && countMap2[i] == 0) {
                return false;
            }
            boolean exist2 = countMap2[i] != 0;
            if (exist2 && countMap[i] == 0) {
                return false;
            }
            if (exist) {
                list.add(countMap[i]);
            }
            if (exist2) {
                list2.add(countMap2[i]);
            }
        }
        Collections.sort(list);
        Collections.sort(list2);
        return list.equals(list2);
    }

    public static void main(String[] args) {
//        System.out.println(new CloseStrings_1657().closeStrings("aaabbbbccddeeeeefffff", "aaaaabbcccdddeeeeffff"));
//        System.out.println(new CloseStrings_1657().closeStrings("aabcc", "ccabb"));
        System.out.println(new CloseStrings_1657().closeStrings("abbzzca", "babzzcz"));
    }
}
