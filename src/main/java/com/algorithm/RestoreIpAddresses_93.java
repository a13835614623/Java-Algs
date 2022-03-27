package com.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description RestoreIpAddresses_93
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 20
 * s 仅由数字组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/23
 */
public class RestoreIpAddresses_93 {

    static class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> list = new ArrayList<>();

            List<Integer> path = new ArrayList<>();
            int start = 0;
            dfs(path, s.length(), start, s, list);
            return list;
        }

        private void dfs(List<Integer> path, int length, int start, String s, List<String> list) {
            if (path.size() == 4) {
                if (start < length) {
                    return;
                }
                list.add(path.stream().map(Object::toString).collect(Collectors.joining(".")));
                return;
            }
            for (int j = 1; j + start <= length && j <= 3; j++) {
                String num = s.substring(start, start + j);
                if (num.charAt(0) == '0' && j > 1) {
                    continue;
                }
                int e = Integer.parseInt(num);
                if (e > 255) {
                    continue;
                }
                path.add(e);
                dfs(path, length, start + j, s, list);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses("101023"));
    }
}
