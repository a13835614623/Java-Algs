package com.algorithm;

import java.util.*;

/**
 * @description GroupAnagrams_49
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/13
 */
public class GroupAnagrams_49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = new String(chs);
            List<String> ls = map.get(key);
            if (ls == null) {
                ArrayList<String> list2 = new ArrayList<>();
                list2.add(str);
                map.put(key, list2);
                lists.add(list2);
            } else {
                ls.add(str);
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        GroupAnagrams_49 groupAnagrams_49 = new GroupAnagrams_49();
        System.out.println(groupAnagrams_49.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
