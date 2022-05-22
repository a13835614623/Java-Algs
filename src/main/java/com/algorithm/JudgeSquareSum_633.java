package com.algorithm;

/**
 * @description JudgeSquareSum_633
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 *
 * 输入：c = 3
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= c <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/06
 */
public class JudgeSquareSum_633 {

    public static boolean judgeSquareSum(int c) {
        if (c == 0) {
            return true;
        }
        long a = 0;
        long b = (int) Math.sqrt(c);
        long sum = (long) c * c;
        while (a <= b && sum != c) {
            sum = a * a + b * b;
            if (sum < c) {
                a++;
            } else {
                b--;
            }
        }
        return sum == c;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(1000000));
    }
}
