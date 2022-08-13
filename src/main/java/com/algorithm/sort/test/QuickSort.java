package com.algorithm.sort.test;

import com.algorithm.array.util.ArrayUtil;
import com.algorithm.sort.Sort;

import javax.naming.ldap.SortKey;
import java.util.Arrays;

/**
 * QuickSort
 * @author 张子宽
 * @date 2022/07/07
 */
public class QuickSort implements Sort<int[]> {

    @Override
    public void sort(int[] array) {
        int len = array.length;

    }

    public void quickSort(int[] arr, int LOW, int HIGH) {
        if (LOW >= HIGH) {
            return;
        }
        // 首先选一个枢纽
        int pivot = LOW;
        int left = LOW;
        int right = HIGH;
        int v = arr[pivot];
        while (left < right) {
            // 从右边边找比枢纽小的,放到枢纽左边
            while (left < right && arr[right] >= v) {
                right--;
            }
            if (left < right) {
                arr[left++] = arr[right];
            }
            // 从左边找比枢纽大的,放到枢纽右边
            while (left < right && arr[left] <= v) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        arr[left] = v;
        quickSort(arr, LOW, left);
        quickSort(arr, left + 1, HIGH);
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = ArrayUtil.randomArray(10);
        System.out.println(Arrays.toString(arr));
        quickSort.quickSort(arr, 0, 9);
        System.out.println(Arrays.toString(arr));
    }
}
