package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *  
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/03/11
 */
public class Multiply_43 {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }
        if (num1.length() == 1 && num2.length() == 1) {
            return ((num1.charAt(0) - '0') * (num2.charAt(0) - '0')) + "";
        }
        String num3 = num1;
        if (num1.length() < num2.length()) {
            num1 = num2;
            num2 = num3;
        }
        if (num2.length() == 1) {
            int a = num2.charAt(0) - 48;
            StringBuilder sb = new StringBuilder(num1.length() + 1);
            int last = 0;
            char[] chars = num1.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
                int b = chars[i] - 48;
                int ab = a * b + last;
                sb.append(ab % 10);
                last = ab / 10;
            }
            if (last > 0) {
                sb.append(last);
            }
            return sb.reverse().toString();
        }
        List<String> addNumList = new ArrayList<>();
        char[] chars2 = num2.toCharArray();
        for (int i = chars2.length - 1; i >= 0; i--) {
            addNumList.add(multiply(num1, chars2[i] + ""));
        }
        String baseNum = addNumList.get(0);
        for (int i = 1; i < addNumList.size(); i++) {
            StringBuilder curAddNum = new StringBuilder(addNumList.get(i));
            for (int j = 0; j < i; j++) {
                curAddNum.append("0");
            }
            baseNum = addNum(baseNum, curAddNum.toString());
        }
        return baseNum;
    }

    private static String addNum(String a, String b) {
        String c = a;
        if (a.length() < b.length()) {
            a = b;
            b = c;
        }
        StringBuilder sb = new StringBuilder();
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        int last = 0;
        int i = arr1.length - 1, j = arr2.length - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            int sum = arr1[i] + arr2[j] - 96 + last;
            sb.append(sum % 10);
            last = sum >= 10 ? 1 : 0;
        }
        while (i >= 0) {
            int sum = arr1[i--] - 48 + last;
            sb.append(sum % 10);
            last = sum >= 10 ? 1 : 0;
        }
        while (j >= 0) {
            int sum = arr2[j--] - 48 + last;
            sb.append(sum % 10);
            last = sum >= 10 ? 1 : 0;
        }
        if (last != 0) {
            sb.append(last);
        }
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        Multiply_43 x = new Multiply_43();
        System.out.println(x.multiply("123", "9"));
        System.out.println(123 * 9);

        System.out.println(addNum("23", "999"));
    }


}
