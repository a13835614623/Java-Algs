package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description PartitionLabels_763
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 *
 *  
 *
 * 示例：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/03
 */
public class PartitionLabels_763 {


    public static
    class Solution {
        public List<Integer> partitionLabels(String s) {
            List<Integer> list = new ArrayList<>();
            int[] index = new int[27];
            int length = s.length();
            char[] chars = s.toCharArray();
            for (int i = 0; i < length; i++) {
                index[chars[i] - 'a'] = i;
            }
            int i = 0;
            while (i < length) {
                int max = index[chars[i] - 'a'];
                for (int j = i + 1; j < max; j++) {
                    max = Math.max(index[chars[j] - 'a'], max);
                }
                list.add(max - i + 1);
                i = max + 1;
            }
            return list;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
