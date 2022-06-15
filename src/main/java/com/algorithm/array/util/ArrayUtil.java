package com.algorithm.array.util;

import java.util.Random;

public class ArrayUtil {
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static <T> void printArray2(T[][] arr) {
        for (T[] is : arr) {
            for (T i : is) {
                System.out.printf("%4s", i);
            }
            System.out.println();
        }
    }
    public static void printArray2(char[][] arr) {
        for (char[] is : arr) {
            for (char i : is) {
                System.out.printf("%4s", i);
            }
            System.out.println();
        }
    }
    public static void printArray2(int[][] arr) {
        for (int[] is : arr) {
            for (int i : is) {
                System.out.printf("%4s", i);
            }
            System.out.println();
        }
    }
    public static int[] randomArray(int length) {
        Random random = new Random();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = random.nextInt(length);
        }
        return nums;
    }

    public static void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void printArray2(boolean[][] arr) {
        System.out.println("-----------------------------");
        for (boolean[] is : arr) {
            for (boolean i : is) {
                System.out.printf((i ? "√" : "X") + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }

}
