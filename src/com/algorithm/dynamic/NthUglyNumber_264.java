package com.algorithm.dynamic;

/**
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * <p>
 * 说明:
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zzk
 * @Date: 2020-03-16 21:19
 */
public class NthUglyNumber_264 {
    static int[] nums;

    static {
        nums = new int[1691];
        int i2, i3, i5, i = 1, ugly;
        i2 = i3 = i5 = 0;
        nums[0] = 1;
        while (i < 1691) {
            ugly = nums[i] = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            if (ugly == nums[i2] * 2) i2++;
            if (ugly == nums[i3] * 3) i3++;
            if (ugly == nums[i5] * 5) i5++;
            i++;
        }
    }
    public static int nthUglyNumber(int n) {
        return nums[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(5));
    }
}
