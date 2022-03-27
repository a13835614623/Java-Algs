package com.algorithm;

/**
 * @description AddBinary_67
 *
 * @author 张子宽
 * @date 2022/03/13
 */
public class AddBinary_67 {

    public String addBinary(String a, String b) {
        if ("0".equals(a)) {
            return b;
        }
        if ("0".equals(b)) {
            return a;
        }
        char[] chs = a.toCharArray();
        char[] chs2 = b.toCharArray();
        int last = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = chs.length - 1, j = chs2.length - 1; i >= 0 || j >= 0; i--, j--) {
            int m = i >= 0 ? chs[i] - '0' : 0;
            int n = j >= 0 ? chs2[j] - '0' : 0;
            int sum = m + n + last;
            sb.append(sum % 2);
            last = sum / 2;
        }
        if (last != 0) {
            sb.append(last);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        AddBinary_67 addBinary_67 = new AddBinary_67();
        System.out.println(addBinary_67.addBinary("111", "111"));
    }
}
