package com.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * <p>
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Description:存在重复元素 II
 * @Author: zzk
 * @Date: 2019-06-06 20:58
 */
public class ContainsNearbyDuplicate_219 {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer ii = map.get(nums[i]);
            if (ii != null && i - ii <= k) return true;
            map.put(nums[i],i);
        }
        return false;
    }
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        int max=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[max]){
                max=i;
            }
            else{
                for(int j=i-1;j>=i-k && j>=0;j--){
                    if(nums[j]==nums[i]){
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsNearbyDuplicate(nums,3));
    }
}
