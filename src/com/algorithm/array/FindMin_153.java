package com.algorithm.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 * @Description:寻找旋转排序数组中的最小值
 * @Author: zzk
 * @Date: 2019-05-04 16:30
 */
public class FindMin_153 {
    public static int findMin(int[] nums) {
        int len = nums.length;
        int min = nums[0];
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] < min) {
                return nums[i];
            }
            if (nums[i - 1] > nums[i]) break;
        }
        return min;
    }

    public static int findMin2(int[] nums) {
        return min(nums, 0, nums.length);
    }

    public static int min(int[] nums,int start,int end) {
        if(start==end-1)return nums[start];
        int mid=((start+end-1)>>1);
        if(nums[mid]>nums[end-1]){//左边有序
            return Math.min(nums[0],min(nums,mid+1,end));
        }else{//右边有序
            return min(nums,start,mid+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(findMin2(new int[]{3,1,2}));
    }
}
