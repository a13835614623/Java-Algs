package com.algorithm.array;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * <p>
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * @Description:三角形最小路径和
 * @Author: zzk
 * @Date: 2019-04-30 17:09
 */
public class MinimumTotal_120 {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        List<Integer> cur, last = triangle.get(len - 1);
        if (len == 1) return last.get(0);
        int result = 0;
        for (int i = len - 2; i >= 0; i--) {
            cur = triangle.get(i);
            for (int lenI = cur.size(), j = 0, left = last.get(0); j < lenI; j++) {
                cur.set(j, result = cur.get(j) + Math.min(left, left = last.get(j + 1)));
            }
            last = cur;
        }
        return result;
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        int len = triangle.size();
        int arr[][] = new int[len+1][len+1];
        for (int i = len-1; i >=0; i--) {
            for (int j = 0; j < i+1; j++) {
                arr[i][j]=triangle.get(i).get(j)+Math.min(arr[i+1][j],arr[i+1][j+1]);
            }
        }
        return arr[0][0];
    }
    public static int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // 只需要记录每一层的最小值即可
        int[] dp = new int[triangle.size()+1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> curTr = triangle.get(i);
            for (int j = 0; j < curTr.size(); j++) {
                //这里的dp[j] 使用的时候默认是上一层的，赋值之后变成当前层
                dp[j] = Math.min(dp[j],dp[j+1]) + curTr.get(j);
            }
        }
        return dp[0];
    }
    public static int fun(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size() - 1) return triangle.get(row).get(col);
        return triangle.get(row).get(col) + Math.min(fun(triangle, row + 1, col), fun(triangle, row + 1, col + 1));
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal2(triangle));
    }
}
