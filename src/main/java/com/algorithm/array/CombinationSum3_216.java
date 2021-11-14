package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * <p>
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @Description:组合总和 III
 * @Author: zzk
 * @Date: 2019-05-13 21:01
 */
public class CombinationSum3_216 {
    /**
     * 出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        find(result, list, n,1,k);
        return result;
    }
    public static void find(List<List<Integer>> listAll, List<Integer> tmp, int target, int num,int k) {
        // 递归的终点
        if(k==0){
            if (target == 0) {
                listAll.add(tmp);
                return;
            }
            if (target < 0)return;
        }
        // 遍历
        for (int i = num; i < 10 && i <= target; i++) {
            // 深拷贝tmp
            List<Integer> list = new LinkedList<>(tmp);
            list.add(i);
            // 递归运算，将i传递至下一次运算是为了避免结果重复。
            find(listAll, list, target - i, i+1,k-1);
        }
    }
    public static void main(String[] args) {
        System.out.println(combinationSum3(3,7));
    }
}
