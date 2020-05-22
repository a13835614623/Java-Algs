package com.algorithm.array;

import com.algorithm.array.util.ArrayUtil;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [1,1,1,2,2,3],
 * <p>
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * <p>
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 * @Description:删除排序数组中的重复项 II
 * @Author: zzk
 * @Date: 2019-04-22 19:58
 */
public class RemoveDuplicates_80 {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length, result = len;
        for (int i = 0; i < len; i++) {
            int temp = i;
            while (temp > 1&&temp<len&& nums[temp - 2] == nums[temp - 1] && nums[temp - 1] == nums[temp]) {
                temp++;
            }
            if (temp == i) continue;
            int j=temp;
            for (; j < result; j++) {
                nums[j - temp + i] = nums[j];
            }
            if(nums[j - temp + i]<nums[0])break;
            // 将j-temp+i及其之后的全赋值为最小值-1;
            for (temp=j=j - temp + i; j <result; j++) {
                nums[j]=nums[0]-1;
            }
            result=temp;
        }
        return result;
    }
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
        ArrayUtil.printArray(nums);
    }
}
