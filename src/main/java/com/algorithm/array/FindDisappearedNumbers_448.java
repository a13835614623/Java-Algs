package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 * <p>
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * @Author: zzk
 * @Date: 2020-04-16 12:54
 */
public class FindDisappearedNumbers_448 {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        boolean tmp[] = new boolean[nums.length];
        Arrays.fill(tmp, false);
        for (int i = 0; i < nums.length; i++) {
            tmp[nums[i] - 1] = true;
        }
        for (int i = 0; i < tmp.length; i++) {
            if (!tmp[i]) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            int num = nums[i];
            if (num == i + 1||num==nums[num-1]) {
                i++;
            } else {
                int tmp = num;
                nums[i] = nums[num - 1];
                nums[num - 1] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=i+1){
                res.add(i+1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int nums[] = {4, 3, 2, 7, 8, 2, 3, 1};
        findDisappearedNumbers2(nums).forEach(System.out::println);
    }
}
