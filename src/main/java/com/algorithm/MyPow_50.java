package com.algorithm;

/**
 * @description MyPow_50
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/13
 */
public class MyPow_50 {

    public double myPow(double x, int n) {
        if (x == 1 || x == 0) {
            return x;
        }
        if (n == 1) {
            return x;
        }
        if (n == Integer.MIN_VALUE) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        int n1 = n >> 1;
        double v = myPow(x, n1);
        if (n % 2 == 0) {
            return v * v;
        }
        return v * v * x;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.MIN_VALUE);
        System.out.println(new MyPow_50().myPow(1, -2147483648));

        System.out.println(-(-2147483648));
    }
}
