package com.test;

import java.util.Scanner;

public class Main {
    public String add(String a, String b) {
        char[] chs = a.toCharArray();
        char[] chs2 = b.toCharArray();
        int len = chs.length;
        int len2 = chs2.length;
        int i = len - 1;
        int j = len2 - 1;
        int over = 0;//进位
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || over > 0) {
            int num1 = i >= 0 ? (chs[i--] - '0') : 0;
            int num2 = j >= 0 ? (chs2[j--] - '0') : 0;
            int sum = num1 + num2 + over;
            int cur = 0;
            if (sum < 2) {
                cur = sum;
                over = 0;
            } else if (sum == 2) {
                cur = 0;
                over = 1;
            } else {
                cur = 1;
                over = 1;
            }
            res.append(cur);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            //注意while处理多个case  int a = in.nextInt();
            String b = in.nextLine();
            String[] input = b.split(" ");
            System.out.println(main.add(input[0],input[1]));
        }
    }

}