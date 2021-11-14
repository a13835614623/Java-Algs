package com.algorithm.sort;

import com.algorithm.array.util.ArrayUtil;

/**
 * 冒泡排序
 * 时间复杂度：O(n^2)
 * 空间复杂度: O(1)
 * @Author: zzk
 * @Date: 2020-03-05 17:25
 */
public class BubbleSort {
    /**
     * 每次遍历，最大值到最后
     * @param nums
     */
    public static void bubbleSort(int nums[]) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                // 如果前面的大于后面的，则交换
                if (nums[j] > nums[j + 1]) {
                    ArrayUtil.swap(nums, j, j + 1);
                }
            }

        }
    }

    public static void main(String[] args) {
//        int[] arr = ArrayUtil.randomArray(10);
//        ArrayUtil.printArray(arr);
//        bubbleSort(arr);
//        ArrayUtil.printArray(arr);
        Long userId =127L;
        Long authorId =127L;
        System.out.println(userId==authorId);
        userId =128L;
        authorId =128L;
        System.out.println(userId==authorId);
        Long x=new Long(127);
        Long y=new Long(127);
        System.out.println(x.equals(y));
        System.out.println(x==y);
    }
}
