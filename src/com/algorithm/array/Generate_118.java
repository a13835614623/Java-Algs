package com.algorithm.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * @Description:杨辉三角
 * @Author: zzk
 * @Date: 2019-04-29 19:07
 */
public class Generate_118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = null;
        List<Integer> list = null;
        if(numRows<2){
            result = new ArrayList<>();
            if (numRows == 0) return result;
            list = new ArrayList<>();
            list.add(1);
            result.add(list);
            return result;
        }else {
            result = generate(numRows - 1);
            list = result.get(result.size()-1);
            List<Integer> list3 = new ArrayList<Integer>(list.size()+1);
            list3.add(1);
            for (int i = 1; i < list.size(); i++) {
                list3.add(list.get(i-1)+list.get(i));
            }
            list3.add(1);
            result.add(list3);
            return result;
        }
    }
    public static void printList2(List<List<Integer>> lists){
        for(List<Integer> list: lists){
            for(Integer integer:list){
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printList2(generate(1));
    }
}
