package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 *
 * 示例 1:
 *
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 *
 * 示例 2:
 *
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description:汇总区间
 * @Author: zzk
 * @Date: 2019-06-08 16:21
 */
public class SummaryRanges_228 {
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int start=0,end=0;
        for (int i = 0; i <nums.length; i++) {
            end=i;
            if(i+1<nums.length&&nums[i]+1<nums[i+1]||i+1==nums.length){
                if(nums[end]-nums[start]>0){
                    list.add(nums[start]+"->"+nums[end]);
                }else {
                    list.add(Integer.toString(nums[start]));
                }
                start=i+1;
            }
        }
        return list;
    }
    public static void main(String[] args) {
        int nums[]={0,2,3,4,6,8,9};
        List<String> list = summaryRanges(nums);
        for (String s : list){
            System.out.println(s);
        }
    }
}
