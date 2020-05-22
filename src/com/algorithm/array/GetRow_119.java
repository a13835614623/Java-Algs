package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * @Description:杨辉三角II
 * @Author: zzk
 * @Date: 2019-04-29 19:07
 */
public class GetRow_119 {
    public static List<Integer> getRow(int rowIndex) {
        return Arrays.asList(getRowArray(rowIndex));
    }
    public static Integer[] getRowArray(int rowIndex) {
        if (rowIndex == 0) return new Integer[]{1};
        Integer[] result = new Integer[rowIndex+1];
        Integer[] list = getRowArray(rowIndex - 1);
        result[0] = result[rowIndex]=1;
        for (int i = 1; i < rowIndex; i++) {
            result[i]=list[i - 1] + list[i];
        }
        return result;
    }
    public static void printList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

    public static void main(String[] args) {
        printList(getRow(5));
    }
}
