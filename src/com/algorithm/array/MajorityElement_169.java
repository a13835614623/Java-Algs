package com.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * @Description:求众数
 * @Author: zzk
 * @Date: 2019-05-07 08:06
 */
public class MajorityElement_169 {
    /**
     * @description: 摩尔计数法
     * @param: [nums]
     * @return: int
     * @author: zzk
     * @date: 2019-05-07 8:56
     */
    public static int majorityElement(int[] nums) {
        int majority = 0,count=0;
        for (int i = 0,len=nums.length; i <len; i++) {
            if(count==0){
                majority=nums[i];
                count=1;
            }else if(nums[i]==majority){
                count++;
            }else {
                count--;
            }
        }
        return majority;
    }
    public static void main(String[] args) {

    }
}
