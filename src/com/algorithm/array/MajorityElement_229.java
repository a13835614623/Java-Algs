package com.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: [3]
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:求众数 II
 * @Author: zzk
 * @Date: 2019-06-20 22:18
 */
public class MajorityElement_229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int x = 0, y = 0, cx = 0, cy = 0;
        for (int i = 0; i < nums.length; i++) {
            if (x == nums[i]) cx++;
            else if (y == nums[i]) cy++;
            else if (cx == 0) {
                x = nums[i];
                cx = 1;
            } else if (cy == 0) {
                y = nums[i];
                cy = 1;
            } else {
                cx--;
                cy--;
            }
        }
        cx = 0;
        cy = 0;
        for (int i = 0; i < nums.length; i++) {
            if(x==nums[i])cx++;
            else if(y==nums[i])cy++;
        }
        if(cx>nums.length/3)list.add(x);
        if(cy>nums.length/3)list.add(y);
        return list;
    }

    public static void main(String[] args) {

    }
}
