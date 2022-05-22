package com.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description TopKFrequent_347
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * @author 张子宽
 * @date 2022/05/15
 */
public class TopKFrequent_347 {

    public int[] topKFrequent(int[] nums, int k) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>(length);
        int maxCount = 1;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
            maxCount = Math.max(maxCount, count);
        }
        List[] countNumMap = new List[maxCount + 1];
        map.forEach((num, count) -> {
            if (countNumMap[count] == null) {
                countNumMap[count] = new ArrayList<>(length);
            }
            countNumMap[count].add(num);
        });
        int[] res = new int[k];
        int i = 0;
        for (int j = countNumMap.length - 1; j >= 0; j--) {
            List<Integer> list = countNumMap[j];
            if (list != null) {
                for (Integer num : list) {
                    res[i++] = num;
                    if (i == k) {
                        return res;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
