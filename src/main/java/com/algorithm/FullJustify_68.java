package com.algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 注意:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *  
 *
 * 示例 1:
 *
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *  
 *
 * 提示:
 *
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] 由小写英文字母和符号组成
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/07/06
 */
public class FullJustify_68 {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> list = new ArrayList<>();
        int lineWidth = 0;
        int totalWidth = 0;
        int wordTotalCount = words.length;
        List<String> lineWords = new ArrayList<>();
        for (int i = 0; i < wordTotalCount; i++) {
            String word = words[i];
            int wordLen = word.length();
            if (lineWidth + wordLen <= maxWidth) {
                lineWords.add(word);
                totalWidth += wordLen;
                lineWidth += wordLen;
                if (lineWidth < maxWidth) {
                    // 加个空格
                    lineWidth++;
                }
            } else {
                // 上一个
                list.add(format(lineWords, totalWidth, maxWidth));
                lineWidth = wordLen;
                totalWidth = wordLen;
                lineWords.clear();
                lineWords.add(word);
                if (lineWidth < maxWidth) {
                    lineWidth++;
                }
            }
            if (i == wordTotalCount - 1) {
                list.add(lastLine(maxWidth, lineWidth, lineWords).toString());
            }
        }
        return list;
    }

    private static StringBuilder lastLine(int maxWidth, int lineWidth, List<String> lineWords) {
        StringBuilder line = new StringBuilder();
        int size = lineWords.size();
        for (int j = 0; j < size; j++) {
            line.append(lineWords.get(j));
            if (line.length() < maxWidth) {
                line.append(" ");
            }
            if (j == size - 1) {
                spaces(line, maxWidth - lineWidth);
            }
        }
        return line;
    }

    public static String format(List<String> words, int totalWidth, int maxWidth) {
        int size = words.size();
        StringBuilder s = new StringBuilder();
        if (size == 1) {
            s.append(words.get(0));
            spaces(s, maxWidth - totalWidth);
            return s.toString();
        }
        int avg = (maxWidth - totalWidth) / (size - 1);
        int mod = (maxWidth - totalWidth) % (size - 1);
        for (int i = 0; i < size; i++) {
            s.append(words.get(i));
            if (i < size - 1) {
                if (mod > 0) {
                    spaces(s, avg + 1);
                    mod--;
                } else {
                    spaces(s, avg);
                }
            }
        }
        return s.toString();
    }

    public static String spaces(StringBuilder s, int count) {
        for (int i = 0; i < count; i++) {
            s.append(" ");
        }
        return s.toString();
    }


    public static void main(String[] args) {
        List<String> x = fullJustify(Arrays.asList("Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do")
                .toArray(new String[]{}), 20);
        for (String s : x) {
            System.out.println(s.length());
        }
        System.out.println(x);
    }
}
