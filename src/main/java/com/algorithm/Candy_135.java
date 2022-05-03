package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.util.Arrays;

/**
 * @description Candy_135
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *  
 *
 * 提示：
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/01
 */
public class Candy_135 {
    static
    class Solution {
        public int candy(int[] ratings) {
            int len = ratings.length;
            int[] res = new int[len];
            // 每个首先分配一个
            for (int i = 0; i < len; i++) {
                res[i] = 1;
            }
            for (int i = len - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    res[i] = res[i + 1] + 1;
                }
            }
//            ArrayUtil.printArray(res);
            for (int i = 1; i < len; i++) {
                if (ratings[i] > ratings[i - 1] && res[i] <= res[i - 1]) {
                    res[i] = res[i - 1] + 1;
                }
            }
//            ArrayUtil.printArray(res);
            int result = 0;
            for (int re : res) {
                result += re;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.candy(new int[]{1, 0, 2}));
//        System.out.println(solution.candy(new int[]{1, 2, 2}));
//        System.out.println(solution.candy(new int[]{1, 3, 2, 2, 1}));
//        System.out.println(solution.candy(new int[]{1, 2, 87, 87, 87, 2, 1}));
        System.out.println(solution.candy(new int[]{1, 6, 10, 8, 7, 3, 2}));

    }
}
