package com.algorithm;

/**
 * @description MySqrt_69
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/18
 */
public class MySqrt_69 {

    // f(x) = y
    // f(4) = 2
    // f(2*4) =
    // f(2*x) = y
    public int mySqrt(int x) {
        int min = 0;
        int max = x;
        int result = -1;
        // min      res   x            max
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (((long) mid * mid <= x)) {
                min = mid + 1;
                result = mid;
            } else{
                max = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MySqrt_69 x = new MySqrt_69();
        System.out.println(x.mySqrt(2147395599));
        System.out.println(x.mySqrt(0));
        System.out.println(x.mySqrt(1));
        System.out.println(x.mySqrt(4));
        System.out.println(x.mySqrt(Integer.MAX_VALUE));
        System.out.println(x.mySqrt(4));
    }

}
