package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description FrequencySort_451
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 *
 * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "tree"
 * 输出: "eert"
 * 解释: 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入: s = "cccaaa"
 * 输出: "cccaaa"
 * 解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入: s = "Aabb"
 * 输出: "bbAa"
 * 解释: 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 5 * 105
 * s 由大小写英文字母和数字组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/17
 */
public class FrequencySort_451 {

    public static String frequencySort(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 索引
        int[] charCount = new int[128];
        int maxCount = 0;
        for (int i = 0; i < length; i++) {
            int count = charCount[chars[i]] + 1;
            charCount[chars[i]] = count;
            maxCount = Math.max(maxCount, count);
        }
        List[] countChar = new List[maxCount + 1];
        for (int i = 0; i < 128; i++) {
            int count = charCount[i];
            if (count > 0) {
                List<Character> list = countChar[count];
                if (list == null) {
                    list = countChar[count] = new ArrayList(count);
                }
                for (int j = 0; j < count; j++) {
                    list.add(((char) i));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = countChar.length - 1; i >= 0; i--) {
            List list = countChar[i];
            if (list != null) {
                for (Object o : list) {
                    sb.append(o);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("caabb"));
        System.out.println(frequencySort("tree"));
    }
}
