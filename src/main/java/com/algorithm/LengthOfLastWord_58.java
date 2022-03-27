package com.algorithm;

import java.util.regex.Pattern;

/**
 * @description LengthOfLastWord_58
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 * 示例 2：
 *
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为4。
 * 示例 3：
 *
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为6的“joyboy”。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/13
 */
public class LengthOfLastWord_58 {


    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                len--;
            }else {
                break;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                return len - 1 - i;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        LengthOfLastWord_58 lengthOfLastWord_58 = new LengthOfLastWord_58();
        System.out.println(lengthOfLastWord_58.lengthOfLastWord("s aa   bbb "));
    }
}
