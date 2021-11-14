package com.algorithm.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 *
 * 进阶:
 *
 *     这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 *     这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * @Description:搜索旋转排序数组 II
 * @Author: zzk
 * @Date: 2019-04-23 18:25
 */
public class Search_81 {
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right)>>1;
            if (target == nums[mid])
                return true;
            if (nums[mid] > nums[right]) {// 中间大于右边说明左边有序
                if (target < nums[mid] && target >= nums[left])// 如果在左边范围内
                    right = mid - 1;
                else {
                    left = mid + 1;
                }
            } else if(nums[mid]<nums[right]){// 右边有序
                if (target > nums[mid] && target <= nums[right])// 如果在右边范围内
                    left = mid + 1;
                else{
                    right = mid - 1;
                }
            }else {
                boolean isLeftOrder =false;//左边是否有序
                int i = mid+1;
                for (; i < right; i++) {
                    if(nums[i]==target)return true;
                    if(nums[i]!=nums[mid]){
                        isLeftOrder=true;
                        break;
                    }
                }
                if(isLeftOrder)left=i;
                else right=mid-1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int nums[] ={3,1,1};
        // 3 1 1
        // 1 1 3 1
        // 1 1 1 1 1 1 1 3 1 1
        System.out.println(search(nums,3));
    }
}
