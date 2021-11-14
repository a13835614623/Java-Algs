package com.test;

import com.algorithm.array.util.ArrayUtil;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
//        int[][] os = {
//                {2, 4, 10}, {0, 3, 15}, {3, 4, 21}
//        };
//        ArrayUtil.printArray(func1(os, 6));
        int[] bs = {1, 2, 3, 100};
        int[] nbs = {20,100};
        int res = func3(bs, nbs);
        System.out.println(res);
    }

    public static int[] func1(int[][] order_list, int n) {
        int[] res = new int[n];
        Arrays.fill(res, 0);
        for (int[] os : order_list) {
            int start = os[0];
            int end = os[1];
            for (int i = start; i <= end; i++) {
                res[i] += os[2];
            }
        }
        return res;
    }

    /**
     * @param a_times int整型一维数组 到达时间
     * @param p_times int整型一维数组 买饭时间
     * @return int整型
     */
    public int func2(int[] a_times, int[] p_times) {
        // write code here
        int res = 0;

        return res;
    }

    public static int func3(int[] block_list, int[] unblock_list) {
        int res = 0,len1=block_list.length,len2=unblock_list.length;
        for (int i = 0, j = 0; i < len1;) {
            //封禁时间段，开始和结束时间
            int start=block_list[i],end=block_list[i]+60;
            //获取当前封禁时间段中还存在的封禁，合并封禁时间段
            while (i<len1&&block_list[i]<end){
                end=block_list[i]+60;
                i++;
            }
            //解封时间，默认为当前时间段end
            int stop=end;
            if (j<len2){
                //如果解封时间点在当前封禁时间段内，则设置解封时间stop
                if (unblock_list[j]>=start&&unblock_list[j]<end){
                    stop=unblock_list[j];
                }
                while (j<len2&&unblock_list[j]<end){
                    j++;
                }
                end=stop;
            }
            res+=end-start;
        }
        return res;
    }
}
